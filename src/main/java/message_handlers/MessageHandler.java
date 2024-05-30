package message_handlers;

import org.apache.commons.lang3.tuple.Pair;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * Characterizes the current state. Gets message, answers and goes to the next state.
 */
@FunctionalInterface
public interface MessageHandler {

    Pair<MessageHandler, SendMessage> handle(Message message);

    static SendMessage messageFromString(final String format, Object... args) {
        return messageFromString(String.format(format, args), false);
    }

    static SendMessage messageFromString(final String string, final boolean markdown) {
        final SendMessage res = new SendMessage();
        res.enableMarkdownV2(markdown);
        res.setText(string);
        return res;
    }
}
