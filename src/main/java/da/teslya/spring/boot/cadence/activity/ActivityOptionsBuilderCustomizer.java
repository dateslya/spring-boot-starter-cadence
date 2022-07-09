package da.teslya.spring.boot.cadence.activity;

import com.uber.cadence.activity.ActivityOptions.Builder;

import java.util.function.BiConsumer;

@FunctionalInterface
public interface ActivityOptionsBuilderCustomizer extends
        BiConsumer<Builder, ActivityProperties> {

}
