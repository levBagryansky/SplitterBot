package config;

public class BotConfig {

    public static String PATH = "src/main/resources/config.json";

    private final String token;

    public BotConfig(final String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }

}
