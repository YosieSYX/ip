import java.util.Scanner;
import java.util.ArrayList;

public class Cherry {
    public static void main(String[] args) {
        System.out.println("Hello from Cherry!\n" );
        System.out.println("What can I do for you today?\n" );
        Scanner scanner = new Scanner(System.in);
        String input;
        ArrayList<Task> tasks = new ArrayList<>();
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
            else if (input.startsWith("mark")) {
                int taskNumber = Integer.parseInt(input.split(" ")[1]);

                Task task = tasks.get(taskNumber - 1);
                task.markAsDone();

                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + task);
            }
            else if (input.startsWith("unmark")) {
                int taskNumber = Integer.parseInt(input.split(" ")[1]);
                Task task = tasks.get(taskNumber - 1);
                task.markAsUndone();
                System.out.println("Nice! I've marked this task as not done yet:");
                System.out.println("  " + task);
            }
            else {
                Task task = new Task(input);
                tasks.add(task);
                System.out.println("added: " + input);
            }
        }

        scanner.close();

        System.exit(0);
    }
}
