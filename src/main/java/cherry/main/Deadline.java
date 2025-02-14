package cherry.main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import cherry.utils.Day;

/**
 * Represents a task with a specific deadline.
 * The deadline can be either a specific date (in the format yyyy-MM-dd) or a day of the week.
 */
public class Deadline extends Task {

    protected Object by;

    /**
     * Constructs a new Deadline task with the given description and deadline.
     *
     * @param description The description of the task.
     * @param by          The deadline of the task, which can be a date (yyyy-MM-dd) or a day of the week.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = parseDateOrDay(by);
    }


    /**
     * Returns the deadline of the task, which can be either a LocalDate or a Day enum.
     *
     * @return The deadline of the task as a LocalDate or Day enum.
     */
    public Object getDateOrDay() {
        return this.by;
    }

    /**
     * Parses the input string to determine if it represents a date or a day of the week.
     * If the input is a valid day of the week, it returns the corresponding Day enum.
     * If the input is a valid date in the format yyyy-MM-dd, it returns a LocalDate object.
     * If the input is neither, it returns null.
     *
     * @param input The input string to parse.
     * @return A LocalDate object, a Day enum, or null if the input is invalid.
     */
    private Object parseDateOrDay(String input) {
        try {
            return Day.valueOf(input);
        } catch (IllegalArgumentException e) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                return LocalDate.parse(input, formatter);
            } catch (DateTimeParseException ex) {
                System.out.println("Invalid date format. Please use yyyy-MM-dd or a day of the week.");
                return null;
            }
        }
    }

    /**
     * Formats the given date or day into a human-readable string.
     * If the input is a LocalDate, it formats it as "MMM dd yyyy".
     * If the input is a Day enum, it returns the string representation of the day.
     * If the input is neither, it returns "No date set."
     *
     * @param date The date or day to format.
     * @return A formatted string representing the date or day.
     */
    private String formatDate(Object date) {
        if (date instanceof LocalDate) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
            return ((LocalDate) date).format(formatter);
        } else if (date instanceof Day) {
            return ((Day) date).toString();
        } else {
            return "No date set.";
        }
    }

    /**
     * Returns a string representation of the Deadline task, including its description and deadline.
     *
     * @return A string representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatDate(by) + ")";
    }
}
