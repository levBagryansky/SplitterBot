
import com.fasterxml.jackson.databind.ObjectMapper;
import config.BotConfig;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            final BotConfig config = new ObjectMapper().readValue(new File(BotConfig.PATH), BotConfig.class);
            System.out.println(config.getToken());
            new TelegramBotsApi(DefaultBotSession.class)
                .registerBot(new Bot(config));
        } catch (final TelegramApiException | IOException exc) {
            exc.printStackTrace();
        }
    }
}
