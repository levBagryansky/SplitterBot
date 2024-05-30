package logic;

public class SplitCreationFailure extends Exception {

    public SplitCreationFailure(final String format, final Object ... args) {
        super(String.format(format, args));
    }

    public SplitCreationFailure(final String message, final Throwable root) {
        super(message, root);
    }
}
