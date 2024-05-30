package message_handlers;

import org.apache.commons.lang3.tuple.Pair;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * After the first message "/start".
 */
public class OnStartHandler implements MessageHandler {
    @Override
    public Pair<MessageHandler, SendMessage> handle(Message message) {
        return Pair.of(
            new MessageAfterStartHandler(),
            MessageHandler.messageFromString(
                """
                Привет! Этот бот предназначен для того, чтобы помогать компаниям рассчитываться с долгами после поездок.
                Вам нужно будет ввести информацию о чеках, кто их оплатил, и кто сколько должен в чеке. Затем бот подсчитывает общую сумму денег, которую каждый человек должен, и определяет минимальное количество переводов, необходимых для погашения долгов.
                Введите /init_split для создания сплита или /find_split для нахождения существующего.
                """
            )
        );
    }
}
