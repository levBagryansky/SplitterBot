package message_handlers;

import org.apache.commons.lang3.tuple.Pair;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class MessageAfterStartHandler implements MessageHandler {

    @Override
    public Pair<MessageHandler, SendMessage> handle(Message message) {
        return switch (message.getText()) {
            case BotCommands.INIT_SPLIT_STRING -> new OnInitSplitHandler().handle(message);
            default -> Pair.of(
                new MessageAfterStartHandler(),
                MessageHandler.messageFromString(
                    "Не понял, введите, пожалуйста, заново %s или %s",
                    BotCommands.INIT_SPLIT, BotCommands.FIND_SPLIT
                )
            );
        };
    }
}
