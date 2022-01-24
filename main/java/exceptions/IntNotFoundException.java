package exceptions;

public class IntNotFoundException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Число не найдено";

    public IntNotFoundException() {
        super(DEFAULT_MESSAGE);
    }
}
