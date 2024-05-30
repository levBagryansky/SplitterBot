package message_handlers;

public enum BotCommands {
    START("/start"),
    INIT_SPLIT("/init_split"),
    FIND_SPLIT("/find_split"),
    ADD_EVENT("/add_event"),
    RESULTS("/results"),
    EXIT("/exit");

    private final String name;

    public static final String START_STRING = "/start";
    public static final String INIT_SPLIT_STRING = "/start";
    public static final String FIND_SPLIT_STRING = "/start";
    public static final String ADD_EVENT_STRING = "/start";
    public static final String RESULTS_STRING = "/start";
    public static final String EXIT_STRING = "/start";

    BotCommands(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
