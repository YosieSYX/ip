package cherry.main;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Represents a list of tasks and provides methods to manage them.
 * This class allows adding, removing, marking tasks as done or undone, and printing the list of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with an existing list of tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList with.
     */
    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the underlying list of tasks.
     *
     * @return The list of tasks.
     */
    ArrayList<Task> toList() {
        return this.tasks;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks.
     */
    int count() {
        return this.tasks.size();
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the list at the specified index.
     * If the index is invalid, an error message is printed.
     *
     * @param index The index of the task to remove.
     */
    public void removeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        } else {
            System.out.println("Invalid index.");
        }
    }

    /**
     * Retrieves a task from the list at the specified index.
     * If the index is invalid, an error message is printed and null is returned.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index, or null if the index is invalid.
     */
    public Task getTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            return tasks.get(index);
        } else {
            System.out.println("Invalid index.");
            return null;
        }
    }

    /**
     * Marks the task at the specified index as done.
     * If the index is invalid, no action is taken.
     *
     * @param index The index of the task to mark as done.
     */
    public void markAsDone(int index) {
        Task task = getTask(index);
        if (task != null) {
            task.markAsDone();
        }
    }

    /**
     * Marks the task at the specified index as undone.
     * If the index is invalid, no action is taken.
     *
     * @param index The index of the task to mark as undone.
     */
    public void markAsUndone(int index) {
        Task task = getTask(index);
        if (task != null) {
            task.markAsUndone();
        }
    }

    /**
     * Searches for tasks containing any of the provided keywords in their descriptions.
     *
     * @param keywords One or more keywords to search for in task descriptions.
     * @return A string containing the list of tasks that match the keywords,
     * or a message stating no matches were found.
     */
    public String findTasks(String... keywords) {
        List<Task> matchingTasks = tasks.stream()
                .filter(task -> java.util.Arrays.stream(keywords)
                        .anyMatch(task.getDescription()::contains))
                .toList();

        if (matchingTasks.isEmpty()) {
            return "Sorry, no matching tasks found for the given keywords.";
        }

        return IntStream.range(0, matchingTasks.size())
                .mapToObj(i -> (i + 1) + ". " + matchingTasks.get(i))
                .collect(Collectors.joining("\n"));
    }

    public String getTasks() {
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            list.append((i + 1) + ". " + tasks.get(i) + "\n");
        }
        return list.toString();
    }

    /**
     * Prints all tasks in the list with their corresponding indices.
     */
    public void printTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }
}
