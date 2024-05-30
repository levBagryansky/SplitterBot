package data_base;

public class SplitNotFoundException extends Exception {

    public SplitNotFoundException(final String format, final Object ... args) {
        super(String.format(format, args));
    }

    public SplitNotFoundException(final String message, final Throwable root) {
        super(message, root);
    }
}
