package logic;

public class EventCreationFailure extends RuntimeException {

    public EventCreationFailure(final String format, final Object ... args) {
        super(String.format(format, args));
    }

    public EventCreationFailure(final String message, final Throwable root) {
        super(message, root);
    }
}
