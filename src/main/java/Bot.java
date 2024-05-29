import config.BotConfig;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Bot extends TelegramLongPollingBot {

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
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(String.valueOf(update.getMessage().getChatId()));
            sendMessage.setText(update.getMessage().getText());

            try {
                execute(sendMessage);
            } catch (final TelegramApiException exc) {
                exc.printStackTrace();
            }
        }
    }
}
