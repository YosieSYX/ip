public class Deadline extends Task {

    protected Day by;

    public Deadline(String description, Day by) {
        super(description.split(" /by ")[0]);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}