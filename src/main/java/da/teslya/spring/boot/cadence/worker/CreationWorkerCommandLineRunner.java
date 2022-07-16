package da.teslya.spring.boot.cadence.worker;

import com.uber.cadence.worker.Worker;
import com.uber.cadence.worker.WorkerOptions;
import com.uber.cadence.worker.WorkflowImplementationOptions;
import da.teslya.spring.boot.cadence.config.ContextLoader;
import da.teslya.spring.boot.cadence.config.CadenceProperties;
import da.teslya.spring.boot.cadence.config.CustomizersAcceptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.function.BiConsumer;

@Slf4j
@Order(10)
@Component
@RequiredArgsConstructor
public class CreationWorkerCommandLineRunner implements CommandLineRunner {

    private final ContextLoader contextLoader;
    private final CadenceProperties cadenceProperties;
    private final ObjectProvider<WorkerOptionsBuilderCustomizer> builderCustomizers;
    private final Worker.Factory workerFactory;

    @Override
    public void run(String... args) {

        WorkerProperties props = cadenceProperties.getWorker();

        WorkerOptions.Builder builder = new WorkerOptions.Builder();
        new CustomizersAcceptor<>(builderCustomizers).accept(builder, props);

        Worker worker = workerFactory.newWorker(props.getTaskList(), builder.build());
        addWorkflowImplementationFactory(worker);
        registerActivitiesImplementations(worker);
    }

    private void addWorkflowImplementationFactory(Worker worker) {
        contextLoader.getWorkflows()
                .forEach(new WorkflowFactoryRegistrar(worker));
    }

    private void registerActivitiesImplementations(Worker worker) {
        Object[] activityBeans = contextLoader.getActivities().keySet().stream()
                .map(contextLoader::getBean)
                .toArray();
        worker.registerActivitiesImplementations(activityBeans);
    }

    @RequiredArgsConstructor
    private class WorkflowFactoryRegistrar<T> implements BiConsumer<String, Class<T>> {
        private final Worker worker;

        @Override
        public void accept(String beanName, Class<T> workflowInterface) {
            worker.addWorkflowImplementationFactory(
                    new WorkflowImplementationOptions.Builder().build(),
                    workflowInterface,
                    () -> (T) contextLoader.getBean(beanName));
            log.info("Workflow '{}' successfully registered", workflowInterface);
        }
    }
}
