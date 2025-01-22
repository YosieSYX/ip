import java.util.Scanner;

public class Cherry {
    public static void main(String[] args) {
        System.out.println("Hello from Cherry!\n" );
        System.out.println("What can I do for you today?\n" );
        Scanner scanner = new Scanner(System.in);
        String input;
        String[] tasks = new String[100];
        int count = 0;
        while (true) {
            input = scanner.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Bye! See you next time!");
                break;
            }
            if (input.equalsIgnoreCase("list")) {
                if(count == 0) {
                    System.out.println("There's nothing in the list now");
                }
                for (int i = 0; i < count; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
            } else {
                if (count < 100) {
                    tasks[count] = input;
                    count++;
                    System.out.println("added: " + input);
                } else {
                    System.out.println("Sorry, You've hit my capacity!");
                }
            }
        }

        scanner.close();

        System.exit(0);
    }
}
