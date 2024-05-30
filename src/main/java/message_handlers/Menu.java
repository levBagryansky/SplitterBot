package message_handlers;

import org.apache.commons.lang3.tuple.Pair;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class Menu implements MessageHandler {
    @Override
    public Pair<MessageHandler, SendMessage> handle(Message message) {
        return Pair.of(
            new MessageAfterStartHandler(),
            MessageHandler.messageFromString(
                "Введите %s для создания сплита или %s для нахождения существующего.",
                BotCommands.STRINGS.INIT_SPLIT, BotCommands.STRINGS.FIND_SPLIT
            )
        );
    }
}
