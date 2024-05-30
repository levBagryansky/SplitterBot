package logic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class EventFromString implements Supplier<Event> {

    private final String input;

    public EventFromString(final String input) {
        this.input = input;
    }

    @Override
    public Event get() {
        try {
            String[] parts = input.split(",");
            String description = parts[0].trim();
            String sum = parts[1].trim();
            String payer = parts[2].trim();
            String[] participantsArray = Arrays.copyOfRange(parts, 3, parts.length);

            Map<String, Long> user2consumption = new HashMap<>();
            for (String participantWithAmount : participantsArray) {
                String[] participantAndAmount = participantWithAmount.split("-");
                String participant = participantAndAmount[0].trim();
                double amount = Double.parseDouble(participantAndAmount[1].trim()) * 100;
                user2consumption.put(participant, (long) amount);
            }

            double sumOfTheCheck = Double.parseDouble(sum) * 100;

            return new Event(description, (long) sumOfTheCheck, payer, user2consumption);
        } catch (final RuntimeException exc) {
            exc.printStackTrace();
            throw new EventCreationFailure("Что-то не так с форматом", exc);
        }
    }

}
