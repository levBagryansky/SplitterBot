package message_handlers;

import logic.Split;
import org.apache.commons.lang3.tuple.Pair;
import org.telegram.telegrambots.meta.api.objects.Message;

public class ConfiguringSplit implements MessageHandler {

    private final Split currSplit;

    ConfiguringSplit(final Split currSplit) {
        this.currSplit = currSplit;
    }

    @Override
    public Pair<MessageHandler, String> handle(Message message) {
        return Pair.of(
            new OnStartHandler(),
            "Дальше не прописано"
        );
    }
}
