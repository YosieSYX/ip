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

    public void printTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }
}
