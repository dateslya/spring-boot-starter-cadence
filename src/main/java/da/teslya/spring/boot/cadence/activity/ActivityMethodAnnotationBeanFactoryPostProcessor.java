package da.teslya.spring.boot.cadence.activity;

import com.uber.cadence.activity.ActivityMethod;
import da.teslya.spring.boot.cadence.util.ReflectionUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ActivityMethodAnnotationBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        List<String> singletonNames = Arrays.stream(beanFactory.getBeanDefinitionNames())
                .filter(beanFactory::isSingleton)
                .collect(Collectors.toList());

        for (String singletonName : singletonNames) {

            Class<?> singletonType = beanFactory.getType(singletonName);
            Class<?> activityInterfaceType = ReflectionUtils
                    .findInterfaceWithAnnotatedMethod(singletonType, ActivityMethod.class);

            if (activityInterfaceType != null) {
                ActivityHolder.getInstance().put(singletonName, activityInterfaceType);
            }
        }
    }
}
