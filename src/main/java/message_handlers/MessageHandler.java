package message_handlers;

import org.apache.commons.lang3.tuple.Pair;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * Characterizes the current state. Gets message, answers and goes to the next state.
 */
@FunctionalInterface
public interface MessageHandler {

    Pair<MessageHandler, String> handle(Message message);
}
