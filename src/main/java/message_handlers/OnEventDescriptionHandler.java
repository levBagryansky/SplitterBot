package message_handlers;

import logic.EventCreationFailure;
import logic.EventFromString;
import logic.Split;
import org.apache.commons.lang3.tuple.Pair;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class OnEventDescriptionHandler implements MessageHandler {

    private final Split split;

    public OnEventDescriptionHandler(Split split) {
        this.split = split;
    }

    @Override
    public Pair<MessageHandler, SendMessage> handle(Message message) {
        try {
            this.split.addEvent(
                new EventFromString(message.getText()).get()
            );
            return Pair.of(
                new ConfiguringSplitHandler(this.split),
                MessageHandler.messageFromString(
                    "Добавили чек. Введите новый чек командой %s или посчитайте текущие переводы через %s",
                    BotCommands.ADD_EVENT,
                    BotCommands.RESULTS
                )
            );
        } catch (final EventCreationFailure failure) {
            return Pair.of(
                new ConfiguringSplitHandler(this.split),
                MessageHandler.messageFromString(
                    "Не удалось добавить чек, %s. Введите новый чек командой %s или посчитайте текущие переводы через %s",
                    failure.getMessage(),
                    BotCommands.ADD_EVENT,
                    BotCommands.RESULTS
                )
            );
        }
    }
}
