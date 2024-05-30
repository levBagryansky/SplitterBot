package message_handlers;

public enum BotCommands {

    START("/start"),
    INIT_SPLIT("/init_split"),
    FIND_SPLIT("/find_split"),
    ADD_EVENT("/add_event"),
    RESULTS("/results"),
    PRINT_SPLIT(STRINGS.PRINT_SPLIT),
    EXIT(STRINGS.EXIT);


    public static class STRINGS {
        public static final String START = "/start";
        public static final String INIT_SPLIT = "/init_split";
        public static final String FIND_SPLIT = "/find_split";
        public static final String ADD_EVENT = "/add_event";
        public static final String RESULTS = "/results";
        public static final String PRINT_SPLIT = "/print_split";
        public static final String EXIT = "/exit";
    }

    private final String name;

    BotCommands(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
