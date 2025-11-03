package by.dosin.first.exception;

public class ArrayAppException extends Exception {

    public ArrayAppException() {
        super();
    }

    public ArrayAppException(String message) {
        super(message);
    }

    public ArrayAppException(String message, Throwable cause) {
        super(message, cause);
    }

    public ArrayAppException(Throwable cause) {
        super(cause);
    }


}
