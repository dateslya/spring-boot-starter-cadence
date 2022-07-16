package da.teslya.spring.boot.cadence.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;

import java.util.function.BiConsumer;

@RequiredArgsConstructor
public class CustomizersAcceptor<T, U> {

    private final ObjectProvider<? extends BiConsumer<T, U>> customizers;

    public void accept(T target, U props) {
        customizers.orderedStream().forEach(c -> c.accept(target, props));
    }
}
