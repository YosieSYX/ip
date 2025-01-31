package cherry.main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Events extends Task {

    protected LocalDate start;
    protected LocalDate end;

    public Events(String description, String start, String end) {
        super(description);
        this.start = parseDate(start);
        this.end = parseDate(end);
    }

    private LocalDate parseDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            return LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            return null;
        }
    }

    private String formatDate(Object date) {
        if (date instanceof LocalDate) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
            return ((LocalDate) date).format(formatter);
        } else {
            return "No date set.";
        }
    }

    public LocalDate getEndDate() {
        return this.end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + formatDate(start) + " to: " + formatDate(end) + ")";
    }
}
