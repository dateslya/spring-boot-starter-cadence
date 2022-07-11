package da.teslya.spring.boot.cadence.activity.local;

import com.uber.cadence.activity.LocalActivityOptions.Builder;

import java.util.function.BiConsumer;

@FunctionalInterface
public interface LocalActivityOptionsBuilderCustomizer extends
        BiConsumer<Builder, LocalActivityProperties> {

}
