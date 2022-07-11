package da.teslya.spring.boot.cadence.workflow;

import com.uber.cadence.workflow.WorkflowMethod;
import da.teslya.spring.boot.cadence.util.ReflectionUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class WorkflowMethodAnnotationBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        List<String> prototypeNames = Arrays.stream(beanFactory.getBeanDefinitionNames())
                .filter(beanFactory::isPrototype)
                .collect(Collectors.toList());

        for (String prototypeName : prototypeNames) {

            Class<?> prototypeType = beanFactory.getType(prototypeName);
            Class<?> workflowInterfaceType = ReflectionUtils
                    .findInterfaceWithAnnotatedMethod(prototypeType, WorkflowMethod.class);

            if (workflowInterfaceType != null) {
                WorkflowHolder.getInstance().put(prototypeName, workflowInterfaceType);
            }
        }
    }
}
