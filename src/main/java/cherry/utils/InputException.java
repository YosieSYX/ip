package cherry.utils;


/**
 * The InputException class represents an exception that is thrown
 * when there is an input error.
 */
public class InputException extends Exception {
    private final String message;


    /**
     * Constructs an InputException with the specified detail message.
     *
     * @param message The detail message.
     */
    public InputException(String message) {
        this.message = message;
    }

    /**
     * Returns the detail message string of this exception.
     *
     * @return The detail message string.
     */
    @Override
    public String toString() {
        return this.message;
    }
}
