package cherry.main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import cherry.utils.Parser;
import cherry.utils.Storage;
import cherry.utils.Ui;


/**
 * Cherry is a chatbot that helps users manage their tasks.
 * It supports various commands such as adding tasks, marking them as done, and listing tasks.
 */
public class Cherry {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private final Parser parser;

    /**
     * Constructs a Cherry chatbot with a specified file path for storage.
     *
     * @param filePath The file path for task storage.
     */
    public Cherry(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
        parser = new Parser();
    }

    /**
     * Processes the user input and returns the chatbot's response.
     *
     * @param input The user command.
     * @return The chatbot's response as a string.
     */
    public String getResponse(String input) {
        if (input.equalsIgnoreCase("bye")) {
            return handleBye();
        } else if (input.equalsIgnoreCase("list")) {
            return handleList();
        } else if (input.startsWith("find")) {
            return handleFind(input);
        } else if (input.startsWith("by")) {
            return handleByDate(input);
        } else if (input.startsWith("mark")) {
            return handleMark(input);
        } else if (input.startsWith("unmark")) {
            return handleUnmark(input);
        } else if (input.startsWith("delete")) {
            return handleDelete(input);
        } else if (input.startsWith("todo")) {
            return handleTodo(input);
        } else if (input.startsWith("deadline")) {
            return handleDeadline(input);
        } else if (input.startsWith("event")) {
            return handleEvent(input);
        } else if (input.startsWith("tag")) {
            return handleTag(input);
        }
        return "Please give a valid command!";
    }

    /**
     * Handles the "bye" command.
     *
     * @return The goodbye message.
     */
    private String handleBye() {
        return "Goodbye! Hope to see you again!";
    }

    /**
     * Handles the "list" command.
     *
     * @return The list of tasks.
     */
    private String handleList() {
        return tasks.getTasks();
    }

    /**
     * Handles the "find" command.
     *
     * @param input The user input containing the search keyword.
     * @return The list of tasks matching the search keyword.
     */
    private String handleFind(String input) {
        String[] parts = parser.parseFind(input);
        return tasks.findTasks(parts);
    }

    /**
     * Handles the "by" command to find tasks by date.
     *
     * @param input The user input containing the date.
     * @return The list of tasks occurring on the specified date.
     */
    private String handleByDate(String input) {
        try {
            String oriDate = input.split("by")[1].trim();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
            LocalDate date = LocalDate.parse(oriDate, formatter);

            StringBuilder result = new StringBuilder();
            for (Task task : tasks.toList()) {
                if (task instanceof Deadline && date.equals(((Deadline) task).getDateOrDay())) {
                    result.append(task).append("\n");
                } else if (task instanceof Events && date.equals(((Events) task).getEndDate())) {
                    result.append(task).append("\n");
                }
            }
            return result.length() > 0 ? result.toString() : "No tasks found for that date.";
        } catch (Exception e) {
            return "Invalid date format! Use: MMM dd yyyy (e.g., Jan 01 2024)";
        }
    }

    /**
     * Handles marking a task as done.
     *
     * @param input The user input containing the task number.
     * @return The confirmation message.
     */
    private String handleMark(String input) {
        int taskNumber = parser.parseInt(input);
        tasks.markAsDone(taskNumber - 1);
        storage.save(tasks.toList());
        return "Nice! I've marked this task as done.";
    }

    private String handleTag(String input) {
        int taskNumber = parser.parseInt(input);
        String tagName = parser.parseTag(input);
        tasks.tagTask(taskNumber - 1, tagName);
        storage.save(tasks.toList());
        return "Alright, I've tagged this task for you";
    }

    /**
     * Handles unmarking a task as not done.
     *
     * @param input The user input containing the task number.
     * @return The confirmation message.
     */
    private String handleUnmark(String input) {
        int taskNumber = parser.parseInt(input);
        tasks.markAsUndone(taskNumber - 1);
        storage.save(tasks.toList());
        return "Nice! I've marked this task as not done yet.";
    }

    /**
     * Handles deleting a task.
     *
     * @param input The user input containing the task number.
     * @return The confirmation message.
     */
    private String handleDelete(String input) {
        int taskNumber = parser.parseInt(input);
        tasks.removeTask(taskNumber - 1);
        storage.save(tasks.toList());
        return "Okay, I've removed this task from your task list.";
    }

    /**
     * Handles adding a "todo" task.
     *
     * @param input The user input containing the task description.
     * @return The confirmation message.
     */
    private String handleTodo(String input) {
        try {
            String taskDescription = input.substring(5).trim();

            if (taskDescription.isEmpty()) {
                return "Please indicate what you want to do.";
            }

            Task task = new ToDos(taskDescription);
            tasks.addTask(task);
            storage.save(tasks.toList());

            return "Added: " + taskDescription + "\nNow you have " + tasks.count() + " tasks in the list!";
        } catch (StringIndexOutOfBoundsException e) {
            return "Invalid command! Please provide a task description.";
        }

    }

    /**
     * Handles adding a "deadline" task.
     *
     * @param input The user input containing the deadline details.
     * @return The confirmation message.
     */
    private String handleDeadline(String input) {
        String[] parts = parser.parseDeadline(input);
        Task task = new Deadline(parts[0], parts[1]);
        tasks.addTask(task);
        storage.save(tasks.toList());
        return "Added: " + input + "\nNow you have " + tasks.count() + " tasks in the list!";
    }

    /**
     * Handles adding an "event" task.
     *
     * @param input The user input containing the event details.
     * @return The confirmation message.
     */
    private String handleEvent(String input) {
        String[] parts = parser.parseEvents(input);
        tasks.addTask(new Events(parts[0], parts[1], parts[2]));
        storage.save(tasks.toList());
        return "Added: " + input + "\nNow you have " + tasks.count() + " tasks in the list!";
    }
}

