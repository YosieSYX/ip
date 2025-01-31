package cherry.main;

/**
 * Represents a todo task, which is a type of task with a description but no specific deadline or event time.
 * This class extends the base Task class and provides a string representation specific to todo tasks.
 */
public class ToDos extends Task {

    /**
     * Constructs a new ToDos task with the given description.
     *
     * @param description The description of the todo task.
     */
    public ToDos(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the todo task, including its type identifier and description.
     *
     * @return A string representation of the todo task in the format "[T][Status] Description".
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}