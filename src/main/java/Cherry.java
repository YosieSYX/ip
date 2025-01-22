import java.util.Scanner;

public class Cherry {
    public static void main(String[] args) {
        System.out.println("Hello from Cherry!\n" );
        System.out.println("What can I do for you today?\n" );
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            input = scanner.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Bye!");
                break;
            }

        }

        scanner.close();

        System.exit(0);
    }
}
