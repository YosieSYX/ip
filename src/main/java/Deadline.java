import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected Object by;

    public Deadline(String description, String by) {
        super(description.split(" /by ")[0]);
        this.by = parseDateOrDay(by);
    }

    public Object getDateOrDay() {
        return this.by;
    }

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



    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formatDate(by) + ")";
    }
}