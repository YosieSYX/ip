package cherry.main;

import java.io.Serial;
import java.io.Serializable;

/**
 * Represents a task with a description and a completion status.
 * This class serves as the base class for different types of tasks (e.g., Deadline, Events).
 * It implements Serializable to allow for saving and loading tasks from storage.
 */
public class Task implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    protected String description;
    protected boolean isDone;
    protected String tag;

    /**
     * Constructs a new Task with the given description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.tag = "";
    }

    /**
     * Returns the status icon of the task, which indicates whether the task is done or not.
     * A "X" represents a completed task, while a space " " represents an incomplete task.
     *
     * @return The status icon as a string.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the task as done by setting the isDone flag to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone by setting the isDone flag to false.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    public void tag(String tagName) {
        this.tag = tagName;
    }

    /**
     * Returns a string representation of the task, including its status icon and description.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
