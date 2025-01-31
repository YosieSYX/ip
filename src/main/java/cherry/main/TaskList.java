package cherry.main;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    TaskList() {
        this.tasks = new ArrayList<>();
    }

    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    ArrayList<Task> toList() {
        return this.tasks;
    }

    int count() {
        return this.tasks.size();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        } else {
            System.out.println("Invalid index.");
        }
    }

    public Task getTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            return tasks.get(index);
        } else {
            System.out.println("Invalid index.");
            return null;
        }
    }

    public void markAsDone(int index) {
        Task task = getTask(index);
        if (task != null) {
            task.markAsDone();
        }
    }

    public void markAsUndone(int index) {
        Task task = getTask(index);
        if (task != null) {
            task.markAsUndone();
        }
    }

    /**
     * Finds tasks that contain the specified keyword in their descriptions.
     *
     * @param keyword The keyword to search for.
     */
    public void findTasks(String keyword) {
        System.out.println("Here are the matching tasks in your list:");
        int count = 1;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().contains(keyword)) {
                System.out.println(count + ". " + tasks.get(i));
                count++;
            }
        }
        if (count == 1) {
            System.out.println("No matching tasks found for this keyword.");
        }
    }

    public void printTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }
}
