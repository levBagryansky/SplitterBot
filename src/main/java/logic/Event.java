package logic;

import java.util.Map;

public record Event(String description, Long sum, String sponsor,
                    Map<String, Long> user2consumption) {

    public Event {
        final Long realSum = user2consumption.values().stream().reduce(0L, Long::sum);
        if (!sum.equals(realSum)) {
            throw new EventCreationFailure(
                String.format("Sum %s is not equals to real sum %s", sum, realSum)
            );
        }
    }

    @Override
    public String toString() {
        return "{" +
            description +
            ", сумма =" + ((double) sum) / 100 + "руб" +
            ", расплачивался " + sponsor +
            ", потребляли (в копейках)" + user2consumption +
            '}';
    }
}
