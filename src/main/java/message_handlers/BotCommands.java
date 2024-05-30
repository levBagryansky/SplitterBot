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
    public static final String INIT_SPLIT_STRING = "/init_split";
    public static final String FIND_SPLIT_STRING = "/find_split";
    public static final String ADD_EVENT_STRING = "/add_event";
    public static final String RESULTS_STRING = "/results";
    public static final String EXIT_STRING = "/exit";

    BotCommands(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
