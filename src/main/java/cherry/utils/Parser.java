package cherry.utils;

/**
 * The Parser class provides methods to parse different types of input strings.
 */
public class Parser {

    /**
     * Parses an integer from the input string.
     *
     * @param input The input string containing the integer.
     * @return The parsed integer.
     */
    public int parseInt(String input) {
        return Integer.parseInt(input.split(" ")[1]);
    }

    /**
     * Parses the input string and returns the third word.
     *
     * @param input The input string containing words separated by spaces.
     * @return The third word in the input string.
     * @throws IllegalArgumentException if the input has fewer than three words.
     */
    public String parseTag(String input) {
        String[] parts = input.split(" ");
        if (parts.length < 3) {
            throw new IllegalArgumentException("Input must contain at least three words.");
        }
        return parts[2];
    }


    /**
     * Parses the input string for the "find" command and extracts the keywords.
     *
     * @param input The user input string containing the "find" command followed by keywords.
     * @return An array of strings split by spaces, where the first element is the command and the subsequent elements are keywords.
     * @throws InputException If no keywords are provided after the command.
     */
    public String[] parseFind(String input) {
        String[] parts = input.split(" ");
        try {
            if (parts.length < 2) {
                throw new InputException("Please enter a key word.");
            }
        } catch (InputException e) {
            System.err.println(e.getMessage());
        }
        return parts;
    }

    /**
     * Parses a deadline task from the input string.
     *
     * @param input The input string containing the deadline task.
     * @return An array of strings where the first element is the task description
     * and the second element is the deadline.
     */
    public String[] parseDeadline(String input) {
        String[] parts = input.split(" /by ");
        try {
            if (parts.length < 2) {
                throw new InputException("Please use 'deadline <task> /by <time>' format.");
            }
        } catch (InputException e) {
            System.err.println(e.getMessage());
        }
        return parts;
    }

    /**
     * Parses an event task from the input string.
     *
     * @param input The input string containing the event task.
     * @return An array of strings where the first element is the task description,
     * the second element is the start time, and the third element is the end time.
     */
    public String[] parseEvents(String input) {
        String[] parts = new String[3];
        try {
            int fromIndex = input.indexOf("/from");
            int toIndex = input.indexOf("/to");

            if (fromIndex == -1) {
                throw new InputException("Please use 'event <task> /from <start> /to <end>' format.");
            }
            if (toIndex == -1) {
                throw new InputException("Please provide an end time for this event.");
            } else {
                parts[0] = input.substring(0, fromIndex);
                parts[1] = input.substring(fromIndex + 6, toIndex - 1);
                parts[2] = input.substring(toIndex + 4);
            }
        } catch (InputException e) {
            System.err.println(e.getMessage());
        }
        return parts;
    }
}
