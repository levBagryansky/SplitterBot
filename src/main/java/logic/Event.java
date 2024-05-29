package logic;

import java.util.Map;

public class Event {

    public final String description;

    public final Map<String, Long> user2consumption;

    public final Long sum;

    public final String sponsor;

    public Event(final String description, final Long sum, String sponsor, final Map<String, Long> user2consumption) {
        final Long realSum = user2consumption.values().stream().reduce(0L, Long::sum);
        if (!sum.equals(realSum)) {
            throw new RuntimeException(
                String.format("Sum %s is not equals to real sum %s", sum, realSum)
            );
        }
        this.description = description;
        this.sum = sum;
        this.sponsor = sponsor;
        this.user2consumption = user2consumption;
    }
}
