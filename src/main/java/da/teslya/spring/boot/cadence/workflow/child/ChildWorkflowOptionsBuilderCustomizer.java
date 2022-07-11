package da.teslya.spring.boot.cadence.workflow.child;

import com.uber.cadence.workflow.ChildWorkflowOptions.Builder;

import java.util.function.BiConsumer;

@FunctionalInterface
public interface ChildWorkflowOptionsBuilderCustomizer extends
        BiConsumer<Builder, ChildWorkflowProperties> {

}
