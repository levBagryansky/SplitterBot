package message_handlers;

import data_base.DataBase;
import logic.Split;
import org.apache.commons.lang3.tuple.Pair;
import org.telegram.telegrambots.meta.api.objects.Message;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OnInitSplitHandler implements MessageHandler{

    /**
     *
     * @param text description and list of participants.
     * @return new {@link Split}
     * @todo #1:90min Verify text.
     */
    private static Split createByText(final String text) {
        final String[] parts = text.split("\\.");
        final String description = parts[0].trim();
        final String participantsList = parts[1];
        final String[] participantsArray = participantsList.split(",");
        final List<String> participants = new ArrayList<>();
        for (String participant : participantsArray) {
            participants.add(participant.trim());
        }
        System.out.println(description + Arrays.toString(participantsArray));
        return new Split(description, participants);
    }

    @Override
    public Pair<MessageHandler, String> handle(Message message) {
        return null;
    }
}
