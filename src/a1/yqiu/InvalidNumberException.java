package a1.yqiu;

public class InvalidNumberException extends Exception {
    public InvalidNumberException(String message) {
        super(message);
    }

    public InvalidNumberException() {
        super();
    }
}