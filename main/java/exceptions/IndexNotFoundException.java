package exceptions;

public class IndexNotFoundException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Выход за пределы массива";

    public IndexNotFoundException() {
        super(DEFAULT_MESSAGE);
    }
}
