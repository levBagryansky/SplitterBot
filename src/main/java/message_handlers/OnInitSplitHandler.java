package message_handlers;

import data_base.DataBase;
import logic.Split;
import org.apache.commons.lang3.tuple.Pair;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OnInitSplitHandler implements MessageHandler{

    @Override
    public Pair<MessageHandler, SendMessage> handle(Message message) {
        return Pair.of(
            answer -> {
                final Split split = OnInitSplitHandler.createByText(answer.getText());
                final String tag = DataBase.instance.addSplit(split);
                return Pair.of(
                    new ConfiguringSplit(split),
                    MessageHandler.messageFromString(
                        String.format(
                            "Вы создали сплит с тэгом `%s`\\. Теперь добавте по чеку с помощью %s\\.",
                            tag,
                            BotCommands.ADD_EVENT_STRING.replace("_", "\\_")
                        ),
                        true
                    )
                );
            },
            MessageHandler.messageFromString("Для создание сплита укажите описание и список участников в формате: Описание. участник1, участник 2, .. .")
        );
    }

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
        System.out.println(description + ": " + Arrays.toString(participants.toArray()));
        return new Split(description, participants);
    }
}
