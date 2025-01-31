package cherry.utils;

import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String getUserInput() {
        return scanner.nextLine();
    }

    public void showWelcomeMessage() {
        System.out.println("Hello from Cherry!\n");
        System.out.println("What can I do for you today?\n");
    }

    public void showGoodbyeMessage() {
        System.out.println("Bye! See you next time!");
    }

    public void showErrorMessage(String message) {
        System.out.println(message);
    }

    public void showReceivedMessage(String message) {
        System.out.println(message);
    }

    public void close() {
        scanner.close();
    }
}
