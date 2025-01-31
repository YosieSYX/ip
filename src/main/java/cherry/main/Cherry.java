package cherry.main;

import cherry.utils.InputException;
import cherry.utils.Parser;
import cherry.utils.Storage;

import java.time.LocalDate;

import cherry.utils.Ui;

import java.time.format.DateTimeFormatter;

public class Cherry {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private final Parser parser;

    public Cherry(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
        parser = new Parser();
    }

    public void run() {
        int count = tasks.count();
        ui.showWelcomeMessage();
        String input = ui.getUserInput();
        while (true) {
            if (input.equalsIgnoreCase("bye")) {
                ui.showGoodbyeMessage();
                break;
            } else if (input.equalsIgnoreCase("list")) {
                tasks.printTasks();
            } else if (input.startsWith("by")) {
                String OriDate = input.split("by")[1].trim();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
                LocalDate date = LocalDate.parse(OriDate, formatter);
                for (Task task : tasks.toList()) {
                    if (task instanceof Deadline) {
                        Deadline deadline = (Deadline) task;
                        if (deadline.getDateOrDay() != null && deadline.getDateOrDay().equals(date)) {
                            System.out.println(deadline);
                        }
                    } else if (task instanceof Events) {
                        Events event = (Events) task;
                        if (event.getEndDate() != null && event.getEndDate().equals(date)) {
                            System.out.println(event);
                        }
                    }
                }
            } else if (input.startsWith("mark")) {
                int taskNumber = parser.parseInt(input);
                if (taskNumber > count) {
                    ui.showErrorMessage("You don't have this many tasks yet!");
                } else {
                    tasks.markAsDone(taskNumber - 1);
                    ui.showReceivedMessage("Nice! I've marked this task as done:");
                    storage.save(tasks.toList());
                }
            } else if (input.startsWith("unmark")) {
                int taskNumber = parser.parseInt(input);
                tasks.markAsUndone(taskNumber - 1);
                ui.showReceivedMessage("Nice! I've marked this task as not done yet:");
                storage.save(tasks.toList());
            } else if (input.startsWith("delete")) {
                int taskNumber = parser.parseInt(input);
                tasks.removeTask(taskNumber - 1);
                count--;
                ui.showReceivedMessage("Okay, I've removed this task from your task list.");
                storage.save(tasks.toList());
            } else {
                if (input.startsWith("todo")) {
                    try {
                        if (input.trim().split("\\s+").length < 2) {
                            throw new InputException("Please indicate what you want to do.");
                        } else {
                            Task task = new ToDos(input);
                            tasks.addTask(task);
                            count++;
                            ui.showReceivedMessage("added: " + input);
                            ui.showReceivedMessage("Now you have " + count + " tasks in the list!");
                            storage.save(tasks.toList());
                        }
                    } catch (InputException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (input.startsWith("deadline")) {
                    String[] parts = parser.parseDeadline(input);
                    String time = parts[1];
                    Task task = new Deadline(parts[0], time);
                    tasks.addTask(task);
                    count++;
                    ui.showReceivedMessage("added: " + input);
                    ui.showReceivedMessage("Now you have " + count + " tasks in the list!");
                    storage.save(tasks.toList());
                } else if (input.startsWith("event")) {
                    String[] parts = parser.parseEvents(input);
                    String description = parts[0];
                    String start = parts[1];
                    String end = parts[2];
                    tasks.addTask(new Events(description, start, end));
                    count++;
                    ui.showReceivedMessage("added: " + input);
                    ui.showReceivedMessage("Now you have " + count + " tasks in the list!");
                    storage.save(tasks.toList());
                } else {
                    ui.showErrorMessage("please give a valid todo");
                }
            }
            input = ui.getUserInput();
        }
        ui.close();
        System.exit(0);
    }

    public static void main(String[] args) {
        new Cherry("./data/Tasks.txt").run();
    }
}
