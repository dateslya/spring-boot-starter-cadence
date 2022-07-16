package da.teslya.spring.boot.cadence.config;

import com.uber.cadence.activity.ActivityMethod;
import com.uber.cadence.activity.ActivityOptions;
import com.uber.cadence.workflow.Workflow;
import com.uber.cadence.workflow.WorkflowMethod;
import da.teslya.spring.boot.cadence.util.ReflectionUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Order(0)
@Component
@RequiredArgsConstructor
public class ContextLoader implements BeanDefinitionRegistryPostProcessor, ApplicationContextAware, CommandLineRunner {

    private final AnnotationBeanNameGenerator beanNameGenerator = new AnnotationBeanNameGenerator();
    private final GenericApplicationContext context = new AnnotationConfigApplicationContext();

    private final Map<String, Class<?>> activities = new HashMap<>(64);
    private final Map<String, Class<?>> workflows = new HashMap<>(16);

    private ApplicationContext applicationContext;

    @Override
    public void run(String... args) throws Exception {

        context.setParent(applicationContext);
        context.refresh();

        log.info("Cadence Context initialization completed");
    }

    public Object getBean(String beanName) {
        return context.getBean(beanName);
    }

    public Map<String, Class<?>> getActivities() {
        return activities;
    }

    public Map<String, Class<?>> getWorkflows() {
        return workflows;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

        List<String> beanNames = Arrays.stream(registry.getBeanDefinitionNames())
                .collect(Collectors.toList());

        for (String beanName : beanNames) {

            BeanDefinition beanDefinition = registry.getBeanDefinition(beanName);
            String beanClassName = beanDefinition.getBeanClassName();

            if (beanClassName == null) {
                continue;
            }

            Class<?> beanClass = null;
            try {
                beanClass = Class.forName(beanClassName);
            } catch (ClassNotFoundException e) {
                throw new FatalBeanException("Error on getting bean class by name", e);
            }

            Class<?> beanInterface = ReflectionUtils
                    .findInterfaceWithAnnotatedMethod(beanClass, WorkflowMethod.class);
            if (beanInterface != null) {
                replaceWorkflow(beanName, beanDefinition, beanInterface, registry);
                continue;
            }

            beanInterface = ReflectionUtils
                    .findInterfaceWithAnnotatedMethod(beanClass, ActivityMethod.class);
            if (beanInterface != null) {
                replaceActivity(beanName, beanDefinition, beanInterface, registry);
            }
        }
    }

    private void replaceWorkflow(
            String beanName,
            BeanDefinition beanDefinition,
            Class<?> workflowInterface,
            BeanDefinitionRegistry registry) {

        if (!beanDefinition.isPrototype()) {
            throw new FatalBeanException(String.format("Workflow '%s' bean should be prototype", beanName));
        }

        registry.removeBeanDefinition(beanName);

        GenericBeanDefinition stubBeanDefinition = new GenericBeanDefinition();
        stubBeanDefinition.setBeanClass(workflowInterface);
        stubBeanDefinition.setScope(BeanDefinition.SCOPE_PROTOTYPE);
        stubBeanDefinition.setInstanceSupplier(() -> Workflow.newChildWorkflowStub(workflowInterface));

        String stubBeanName = beanNameGenerator.generateBeanName(stubBeanDefinition, registry);

        registry.registerBeanDefinition(stubBeanName, stubBeanDefinition);

        context.registerBeanDefinition(beanName, beanDefinition);
        workflows.put(beanName, workflowInterface);
    }

    private void replaceActivity(
            String beanName,
            BeanDefinition beanDefinition,
            Class<?> activityInterface,
            BeanDefinitionRegistry registry) {

        if (!beanDefinition.isSingleton()) {
            throw new FatalBeanException(String.format("Activity '%s' bean should be singleton", beanName));
        }

        registry.removeBeanDefinition(beanName);

        ActivityOptions options = new ActivityOptions.Builder().setScheduleToCloseTimeout(Duration.ofSeconds(5)).build();
        GenericBeanDefinition stubBeanDefinition = new GenericBeanDefinition();
        stubBeanDefinition.setBeanClass(activityInterface);
        stubBeanDefinition.setScope(BeanDefinition.SCOPE_PROTOTYPE);
        stubBeanDefinition.setInstanceSupplier(() -> Workflow.newActivityStub(activityInterface, options));

        String stubBeanName = beanNameGenerator.generateBeanName(stubBeanDefinition, registry);

        registry.registerBeanDefinition(stubBeanName, stubBeanDefinition);

        context.registerBeanDefinition(beanName, beanDefinition);
        activities.put(beanName, activityInterface);
    }
}
