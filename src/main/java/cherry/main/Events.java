package cherry.main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task with a start and end date.
 * The event is associated with a description and a time frame defined by two dates.
 */
public class Events extends Task {

    protected LocalDate start;
    protected LocalDate end;

    /**
     * Constructs a new Events task with the given description, start date, and end date.
     *
     * @param description The description of the event task.
     * @param start       The start date of the event in the format yyyy-MM-dd.
     * @param end         The end date of the event in the format yyyy-MM-dd.
     */
    public Events(String description, String start, String end) {
        super(description);
        this.start = parseDate(start);
        this.end = parseDate(end);
    }

    /**
     * Parses the input string into a LocalDate object using the format yyyy-MM-dd.
     * If the input string is not in the correct format, it prints an error message and returns null.
     *
     * @param date The input string representing the date.
     * @return A LocalDate object if the input is valid, otherwise null.
     */
    private LocalDate parseDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            return null;
        }
    }

    /**
     * Formats the given date into a human-readable string in the format "MMM dd yyyy".
     * If the input is not a LocalDate, it returns "No date set."
     *
     * @param date The date to format.
     * @return A formatted string representing the date, or "No date set." if the input is invalid.
     */
    private String formatDate(Object date) {
        if (!(date instanceof LocalDate)) {
            return "No date set.";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return ((LocalDate) date).format(formatter);
    }

    /**
     * Returns the end date of the event.
     *
     * @return The end date as a LocalDate object.
     */
    public LocalDate getEndDate() {
        return this.end;
    }

    /**
     * Returns a string representation of the Events task, including its description, start date, and end date.
     *
     * @return A string representation of the Events task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + formatDate(start) + " to: " + formatDate(end) + ")";
    }
}
