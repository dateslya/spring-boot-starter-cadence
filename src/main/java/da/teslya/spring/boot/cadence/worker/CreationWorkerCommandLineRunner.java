package da.teslya.spring.boot.cadence.worker;

import com.uber.cadence.worker.Worker;
import com.uber.cadence.worker.WorkerOptions;
import com.uber.cadence.worker.WorkflowImplementationOptions;
import da.teslya.spring.boot.cadence.activity.ActivityHolder;
import da.teslya.spring.boot.cadence.config.CadenceProperties;
import da.teslya.spring.boot.cadence.config.Customizers;
import da.teslya.spring.boot.cadence.workflow.WorkflowHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.function.BiConsumer;

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
    public void run(String... args) {

        WorkerProperties props = cadenceProperties.getWorker();

        WorkerOptions.Builder builder = new WorkerOptions.Builder();
        new Customizers<>(builderCustomizers).customize(builder, props);

        Worker worker = workerFactory.newWorker(props.getTaskList(), builder.build());
        addWorkflowImplementationFactory(worker);
        registerActivitiesImplementations(worker);
    }

    private void addWorkflowImplementationFactory(Worker worker) {
        WorkflowHolder.getInstance().getAll()
                .forEach(new WorkflowFactoryRegistrar(worker, applicationContext));
    }

    private void registerActivitiesImplementations(Worker worker) {
        worker.registerActivitiesImplementations(findActivityBeans());
    }

    @RequiredArgsConstructor
    private static class WorkflowFactoryRegistrar<T> implements BiConsumer<String, Class<T>> {
        private final Worker worker;
        private final ApplicationContext applicationContext;

        @Override
        public void accept(String beanName, Class<T> beanInterfaceType) {
            worker.addWorkflowImplementationFactory(
                    new WorkflowImplementationOptions.Builder().build(),
                    beanInterfaceType,
                    () -> (T) applicationContext.getBean(beanName));
            log.info("Workflow '{}' successfully registered", beanInterfaceType);
        }
    }

    private Object[] findActivityBeans() {
        return ActivityHolder.getInstance().getAll().keySet().stream()
                .map(applicationContext::getBean)
                .toArray();
    }
}
