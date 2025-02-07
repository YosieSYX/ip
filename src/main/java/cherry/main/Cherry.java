package cherry.main;

import cherry.utils.InputException;
import cherry.utils.Parser;
import cherry.utils.Storage;
import cherry.utils.Ui;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;


/**
 * The Cherry class represents a task management application.
 * It loads tasks from a file, processes user input, and manages task operations.
 */
public class Cherry {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private final Parser parser;

    /**
     * Constructs a Cherry application instance.
     *
     * @param filePath the file path for storing task data
     */
    public Cherry(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
        parser = new Parser();
    }

    public String getResponse(String input) {
        int count = tasks.count();

        if (input.equalsIgnoreCase("bye")) {
            return "Goodbye! Hope to see you again!";
        } else if (input.equalsIgnoreCase("list")) {
            return tasks.getTasks();
        } else if (input.startsWith("find")) {
            String[] parts = parser.parseFind(input);
            return tasks.findTasks(parts[1]);
        } else if (input.startsWith("by")) {
            try {
                String OriDate = input.split("by")[1].trim();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
                LocalDate date = LocalDate.parse(OriDate, formatter);

                StringBuilder result = new StringBuilder();
                for (Task task : tasks.toList()) {
                    if (task instanceof Deadline) {
                        Deadline deadline = (Deadline) task;
                        if (date.equals(deadline.getDateOrDay())) {
                            result.append(deadline).append("\n");
                        }
                    } else if (task instanceof Events) {
                        Events event = (Events) task;
                        if (date.equals(event.getEndDate())) {
                            result.append(event).append("\n");
                        }
                    }
                }
                return result.length() > 0 ? result.toString() : "No tasks found for that date.";
            } catch (Exception e) {
                return "Invalid date format! Use: MMM dd yyyy (e.g., Jan 01 2024)";
            }
        } else if (input.startsWith("mark")) {
            int taskNumber = parser.parseInt(input);
            if (taskNumber > count) {
                return "You don't have this many tasks yet!";
            }
            tasks.markAsDone(taskNumber - 1);
            storage.save(tasks.toList());
            return "Nice! I've marked this task as done.";
        } else if (input.startsWith("unmark")) {
            int taskNumber = parser.parseInt(input);
            tasks.markAsUndone(taskNumber - 1);
            storage.save(tasks.toList());
            return "Nice! I've marked this task as not done yet.";
        } else if (input.startsWith("delete")) {
            int taskNumber = parser.parseInt(input);
            tasks.removeTask(taskNumber - 1);
            storage.save(tasks.toList());
            return "Okay, I've removed this task from your task list.";
        } else if (input.startsWith("todo")) {
            try {
                if (input.trim().split("\\s+").length < 2) {
                    throw new InputException("Please indicate what you want to do.");
                }
                Task task = new ToDos(input.substring(5).trim());
                tasks.addTask(task);
                storage.save(tasks.toList());
                return "Added: " + input + "\nNow you have " + tasks.count() + " tasks in the list!";
            } catch (InputException e) {
                return e.getMessage();
            }
        } else if (input.startsWith("deadline")) {
            String[] parts = parser.parseDeadline(input);
            Task task = new Deadline(parts[0], parts[1]);
            tasks.addTask(task);
            storage.save(tasks.toList());
            return "Added: " + input + "\nNow you have " + tasks.count() + " tasks in the list!";
        } else if (input.startsWith("event")) {
            String[] parts = parser.parseEvents(input);
            tasks.addTask(new Events(parts[0], parts[1], parts[2]));
            storage.save(tasks.toList());
            return "Added: " + input + "\nNow you have " + tasks.count() + " tasks in the list!";
        }

        return "Please give a valid command!";
    }

    /*
    public static void main(String[] args) {
        new Cherry("./data/Tasks.txt").run();
    }
    */

}

