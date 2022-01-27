package exceptions;

public class StringNotFoundException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Строка не найдена";

    public StringNotFoundException() {
        super(DEFAULT_MESSAGE);
    }
}
