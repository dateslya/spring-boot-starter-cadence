package da.teslya.spring.boot.cadence.util;

import lombok.experimental.UtilityClass;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@UtilityClass
public class ReflectionUtils {

    public Class<?> findInterfaceWithAnnotatedMethod(Class<?> beanType, Class<? extends Annotation> annotationType) {
        Method method = findInterfaceMethodWithAnnotation(beanType, annotationType);
        return method == null ? null : method.getDeclaringClass();
    }

    public Method findInterfaceMethodWithAnnotation(Class<?> beanType, Class<? extends Annotation> annotationType) {
        for (Class<?> interfaceType : beanType.getInterfaces()) {
            for (Method method : interfaceType.getMethods()) {
                if (method.isAnnotationPresent(annotationType)) {
                    return method;
                }
            }
        }
        return null;
    }
}
