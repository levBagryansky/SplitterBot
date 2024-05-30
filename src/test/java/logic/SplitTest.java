package logic;

import org.junit.jupiter.api.Test;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SplitTest {

    @Test
    void calculatesCorrectly() {
        final List<Event> events = List.of(
            new Event("description", 1200L, "D", Map.of("A", 1000L, "C", 200L)),
            new Event("description", 500L, "C", Map.of("B", 500L))
        );
        final Split split = new Split(
            "description",
            List.of("A", "B", "C", "D"),
            List.of(
                new Event("description", 1200L, "D", Map.of("A", 1000L, "C", 200L)),
                new Event("description", 500L, "C", Map.of("B", 500L))
            )
        );
        MatcherAssert.assertThat(
            SplitTest.events2debts(events),
            Matchers.equalTo(SplitTest.operations2debts(split.operations()))
        );

    }

    private static Map<String, Long> events2debts(final Collection<Event> events) {
        final Map<String, Long> debts = new HashMap<>();
        events.forEach(event -> {
            debts.compute(event.sponsor(),
                (key, value) -> Objects.requireNonNullElse(value, 0L) - event.sum()
            );
            for (final var entry: event.user2consumption().entrySet()) {
                debts.compute(
                    entry.getKey(),
                    (key, value) -> Objects.requireNonNullElse(value, 0L) + entry.getValue()
                );
            }
        });
        return debts;
    }

    private static Map<String, Long> operations2debts(final Collection<Operation> operations) {
        final Map<String, Long> debts = new HashMap<>();
        operations.forEach(operation -> {
            debts.compute(
                operation.src(),
                (key, value) -> Objects.requireNonNullElse(value, 0L) + operation.price()
            );
            debts.compute(
                operation.dest(),
                (key, value) -> Objects.requireNonNullElse(value, 0L) - operation.price()
            );
        });
        return debts;
    }
}
