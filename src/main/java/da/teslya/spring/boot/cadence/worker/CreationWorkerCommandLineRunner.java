package da.teslya.spring.boot.cadence.worker;

import com.uber.cadence.worker.Worker;
import com.uber.cadence.worker.WorkerOptions;
import com.uber.cadence.worker.WorkflowImplementationOptions;
import com.uber.cadence.workflow.WorkflowMethod;
import da.teslya.spring.boot.cadence.config.CadenceProperties;
import da.teslya.spring.boot.cadence.config.Customizers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Order(10)
@Component
@RequiredArgsConstructor
public class CreationWorkerCommandLineRunner implements CommandLineRunner, ApplicationContextAware {

    private ApplicationContext applicationContext;
    private final CadenceProperties cadenceProperties;
    private final ObjectProvider<WorkerOptionsBuilderCustomizer> builderCustomizers;
    private final Worker.Factory workerFactory;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void run(String... args) throws Exception {

        WorkerProperties props = cadenceProperties.getWorkers().getOrDefault("default", new WorkerProperties());
        TaskListProperties taskListProps = cadenceProperties.getTaskLists().get(props.getTaskList());

        WorkerOptions.Builder builder = new WorkerOptions.Builder();
        new Customizers<>(builderCustomizers).customize(builder, props);

        Worker worker = workerFactory.newWorker(taskListProps.getName(), builder.build());
        findWorkflowInterfaces().forEach(new RegisterWorkflowImplementationFactory(worker, applicationContext));
//        TODO
//        worker.registerActivitiesImplementations();
    }

    @RequiredArgsConstructor
    private static class RegisterWorkflowImplementationFactory<T> implements Consumer<Class<T>> {
        private final Worker worker;
        private final ApplicationContext applicationContext;

        @Override
        public void accept(Class<T> workflowInterface) {
            worker.addWorkflowImplementationFactory(
                    new WorkflowImplementationOptions.Builder().build(),
                    workflowInterface,
                    () -> applicationContext.getBean(workflowInterface));
            log.info("Workflow '{}' successfully registered", workflowInterface);
        }
    }

    private List<Class<?>> findWorkflowInterfaces() {
        return Stream.of(applicationContext.getBeanDefinitionNames())
                .filter(applicationContext::isPrototype)
                .map(applicationContext::getType)
                .filter(Objects::nonNull)
                .map(this::findWorkflowInterface)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private Optional<Class<?>> findWorkflowInterface(Class<?> beanType) {
        return Stream.of(beanType.getInterfaces())
                .flatMap(interfaceType -> Stream.of(ReflectionUtils.getAllDeclaredMethods(interfaceType)))
                .filter(interfaceMethod -> AnnotationUtils.findAnnotation(interfaceMethod, WorkflowMethod.class) != null)
                .findFirst()
                .map(Method::getDeclaringClass);
    }
}
