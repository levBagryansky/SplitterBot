package logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Split {

    final private String description;

    final private Collection<String> participants;

    final private Collection<Event> events;

    final private Map<String, Long> debts;

    public Split (final String description, final Collection<String> participants) {
        this(description, participants, new ArrayList<>());
    }

    public Split(final String description, final Collection<String> participants, final Collection<Event> events) {
        this.description = description;
        this.participants = participants;
        this.events = new ArrayList<>(events);
        this.debts = new HashMap<>(participants.size());
        events.forEach(this::addEvent);
    }

    public synchronized void addEvent(final Event event) {
        this.events.add(event);
        this.debts.compute(event.sponsor,
            (key, value) -> {
                if (!participants.contains(key)) {
                    throw new RuntimeException(
                        String.format(
                            "%s is not a partition of the bill with description \"%s\". Partitions are %s",
                            key,
                            this.description,
                            String.join(", ", this.participants)
                        )
                    );
                }
                return Objects.requireNonNullElse(value, 0L) - event.sum;
            }
        );
        for (final var entry: event.user2consumption.entrySet()) {
            this.debts.compute(
                entry.getKey(),
                (key, value) -> {
                    if (!participants.contains(key)) {
                        throw new RuntimeException(
                            String.format(
                                "%s is not a partition of the bill with description \"%s\". Partitions are %s",
                                key,
                                this.description,
                                String.join(", ", this.participants)
                            )
                        );
                    }
                    return Objects.requireNonNullElse(value, 0L) + entry.getValue();
                }
            );
        }
    }

    public synchronized List<Operation> operations() {
        final Map<String, Long> copyDepth = new HashMap<>(this.debts);
        final List<Operation> res = new ArrayList<>();
        while (true) {
            var max = Collections.max(copyDepth.entrySet(), Map.Entry.comparingByValue());
            if (max.getValue() == 0L) {
                break;
            }
            var min = Collections.min(copyDepth.entrySet(), Map.Entry.comparingByValue());
            if (min.getValue() >= 0) {
                throw new RuntimeException("min value should be negative");
            }
            final long dept = Math.min(max.getValue(), -min.getValue());
            max.setValue(max.getValue() - dept);
            min.setValue(min.getValue() + dept);
            res.add(new Operation(max.getKey(), min.getKey(), dept));
        }
        return res;
    }

}
