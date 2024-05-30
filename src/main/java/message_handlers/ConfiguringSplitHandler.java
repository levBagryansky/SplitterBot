package message_handlers;

import logic.Split;
import org.apache.commons.lang3.tuple.Pair;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class ConfiguringSplitHandler implements MessageHandler {

    private final Split currSplit;

    ConfiguringSplitHandler(final Split currSplit) {
        this.currSplit = currSplit;
    }

    @Override
    public Pair<MessageHandler, SendMessage> handle(Message message) {
        return switch (message.getText()) {
            case BotCommands.STRINGS.PRINT_SPLIT -> Pair.of(
                this,
                    MessageHandler.messageFromString(
                        "%s%nВведите %s для добавления чека или %s для просмотра текущих результатов",
                        this.currSplit.toString(),
                        BotCommands.STRINGS.ADD_EVENT,
                        BotCommands.STRINGS.RESULTS
                    )
            );
            case BotCommands.STRINGS.ADD_EVENT -> Pair.of(
                new OnEventDescriptionHandler(this.currSplit),
                MessageHandler.messageFromString(
                    "Введите чек в формате: <Описание>, <кто оплатил>, <участник1> - <сумма>, <участник2> - <сумма>, .. ."
                )
            );
            case BotCommands.STRINGS.RESULTS -> Pair.of(
                new OnResultsHandler(this.currSplit).handle(message)
            );
            default -> Pair.of(
                new ConfiguringSplitHandler(this.currSplit),
                MessageHandler.messageFromString(
                    "Введите команду %s для добавления чека или %s для просмотра текущих результатов",
                    BotCommands.STRINGS.ADD_EVENT, BotCommands.STRINGS.RESULTS
                )
            );
        };
    }
}
