package cherry.utils;

public class Parser {
    public int parseInt(String input) {
        return Integer.parseInt(input.split(" ")[1]);
    }

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
                parts[1] = input.substring(fromIndex + 6, toIndex);
                parts[2] = input.substring(toIndex + 4);
            }
        } catch (InputException e) {
            System.err.println(e.getMessage());
        }
        return parts;
    }
}

