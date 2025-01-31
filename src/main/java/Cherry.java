import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;

public class Cherry {
    public static void main(String[] args) {
        String FILE_PATH = "./data/Cherry.txt";
        Storage storage = new Storage(FILE_PATH);
        ArrayList<Task> tasks = storage.load();
        int count = tasks.size();
        System.out.println("Hello from Cherry!\n" );
        System.out.println("What can I do for you today?\n" );
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            input = scanner.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Bye! See you next time!");
                break;
            }
            else if (input.equalsIgnoreCase("list")) {
                if (tasks.isEmpty()) {
                    System.out.println("No tasks in the list.");
                } else {
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                }
            }
            else if (input.startsWith("by")) {
                String OriDate = input.split("by")[1].trim();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
                LocalDate date = LocalDate.parse(OriDate, formatter);
                for (Task task : tasks) {
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
            }
            else if (input.startsWith("mark")) {
                int taskNumber = Integer.parseInt(input.split(" ")[1]);
                if (taskNumber > count) {
                    System.out.println("You don't have this many tasks yet!");
                }
                else {
                    Task task = tasks.get(taskNumber - 1);
                    task.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + task);
                    storage.save(tasks);
                }
            }
            else if (input.startsWith("unmark")) {
                int taskNumber = Integer.parseInt(input.split(" ")[1]);
                Task task = tasks.get(taskNumber - 1);
                task.markAsUndone();
                System.out.println("Nice! I've marked this task as not done yet:");
                System.out.println("  " + task);
                storage.save(tasks);
            }
            else if (input.startsWith("delete")) {
                int taskNumber = Integer.parseInt(input.split(" ")[1]);
                tasks.remove(taskNumber - 1);
                count--;
                System.out.println("Okay, I've removed this task from your task list.");
                storage.save(tasks);
            }
            else {
                if (input.startsWith("todo")) {
                    try {
                        if (input.trim().split("\\s+").length < 2) {
                            throw new InputException("Please indicate what you want to do.");
                        } else {
                            Task task = new ToDos(input);
                            tasks.add(task);
                            count++;
                            System.out.println("added: " + input);
                            System.out.println("Now you have " + count + " tasks in the list!");
                            storage.save(tasks);
                        }
                    } catch (InputException e) {
                        System.out.println(e.getMessage());
                    }
                }
                else if (input.startsWith("deadline")) {
                    try {
                        String[] parts = input.split(" /by ");
                        if (parts.length < 2) {
                            throw new InputException("Please use 'deadline <task> /by <time>' format.");
                        } else {
                            try {
                                String time = parts[1];
                                Task task = new Deadline(input, time);
                                tasks.add(task);
                                count++;
                                System.out.println("added: " + input);
                                System.out.println("Now you have " + count + " tasks in the list!");
                                storage.save(tasks);
                            } catch (IllegalArgumentException e) {
                                System.out.println("Please use a valid day of the week.");
                            }

                        }
                    } catch (InputException e) {
                        System.out.println(e.getMessage());
                    }
                }
                else if (input.startsWith("event")) {
                    try {
                        String[] parts1 = input.split(" /from ");
                        if (parts1.length < 2) {
                            throw new InputException("Please use 'event <task> /from <start> /to <end>' format.");
                        }
                        else {
                            String start = parts1[1].split(" /to ")[0];
                            String[] parts2 = input.split(" /to ");
                            if (parts2.length < 2) {
                                throw new InputException("Please provide an end time for this event.");
                            }
                            else {
                                String end = parts2[1];
                                tasks.add(new Events(input, start, end));
                                count++;
                                System.out.println("added: " + input);
                                System.out.println("Now you have " + count + " tasks in the list!");
                                storage.save(tasks);
                            }
                        }
                    } catch (InputException e) {
                        System.out.println(e.getMessage());
                    }
                }
                else {
                    System.out.println("Please indicate what type of event this is.");
                }
            }
        }

        scanner.close();
        System.exit(0);
    }
}
