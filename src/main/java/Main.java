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
            final BotConfig config;
            if (args.length > 0) {
                System.out.println(args[0]);
                config = new BotConfig(args[0]);
            } else {
                config = new BotConfig("7448458229:AAG5x5tvqSe_LJXHWhZBLpXa81JK3mRiFEM");
            }
            //final DataBase dataBase = new NonSavableDataBase();
            new TelegramBotsApi(DefaultBotSession.class)
                .registerBot(new Bot(config));
        } catch (final TelegramApiException exc) {
            exc.printStackTrace();
        }
    }
}
