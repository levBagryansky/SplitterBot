import config.BotConfig;
import message_handlers.MessageHandler;
import message_handlers.OnStartHandler;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Bot extends TelegramLongPollingBot {

    private final Map<Long, MessageHandler> handlers = new ConcurrentHashMap<>();

    public Bot(final BotConfig config) {
        super(config.getToken());
    }

    @Override
    public String getBotUsername() {
        return "bills_splitter_bot";
    }

    @Override
    public void onUpdateReceived(final Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            final long chatId = update.getMessage().getChatId();
            final var pair = this.handlers
                .computeIfAbsent(chatId, key -> new OnStartHandler())
                .handle(update.getMessage());
            this.handlers.put(chatId, pair.getLeft());
            this.sendMessage(chatId, pair.getRight());
        }
    }

    private void sendMessage(final Long chatId, final SendMessage sendMessage){
        sendMessage.setChatId(String.valueOf(chatId));
        try {
            this.execute(sendMessage);
        } catch (final TelegramApiException exc) {
            exc.printStackTrace();
        }
    }
}
