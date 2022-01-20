package exceptions;

public class ArrayIsEmptyException extends RuntimeException{
    private static final String DEFAULT_MESSAGE = "Пустой массив";

    public ArrayIsEmptyException() {
        super(DEFAULT_MESSAGE);
    }
}
