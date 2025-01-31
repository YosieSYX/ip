package cherry.utils;

public class InputException extends Exception {
    private final String message;

    public InputException(String message) {
        this.message = message;
    }

    public String toString() {
        return this.message;
    }
}
