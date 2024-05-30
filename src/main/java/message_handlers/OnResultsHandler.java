package message_handlers;

import logic.Operation;
import logic.Split;
import org.apache.commons.lang3.tuple.Pair;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public class OnResultsHandler implements MessageHandler {

    private final Split split;

    public OnResultsHandler(Split split) {
        this.split = split;
    }

    @Override
    public Pair<MessageHandler, SendMessage> handle(Message message) {
        return Pair.of(
            new ConfiguringSplitHandler(this.split),
            MessageHandler.messageFromString(
                "%sДобавьте чек с помощью %s или выйдите с помощью %s",
                this.operations(),
                BotCommands.ADD_EVENT.toString(),
                BotCommands.EXIT.toString()
            )
        );
    }

    private String operations() {
        final StringBuilder builder = new StringBuilder("");
        for (final Operation operation: this.split.operations()) {
            builder.append(
                String.format(
                    "%s -> %s %d.%d%n",
                    operation.src(),
                    operation.dest(),
                    operation.price() / 100,
                    operation.price() % 100
                )
            );
        }
        return builder.toString();
    }
}
