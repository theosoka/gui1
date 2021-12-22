package exceptions;

public class TooManyThingsException extends RuntimeException {
    public TooManyThingsException(String message) {
        super(message);
    }
}
