package cherry.utils;

import java.util.Scanner;

/**
 * The Ui class handles interactions with the user.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a Ui object and initializes the scanner.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Gets the user input from the console.
     *
     * @return The user input as a String.
     */
    public String getUserInput() {
        return scanner.nextLine();
    }

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcomeMessage() {
        System.out.println("Hello from Cherry!\n");
        System.out.println("What can I do for you today?\n");
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void showGoodbyeMessage() {
        System.out.println("Bye! See you next time!");
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public void showErrorMessage(String message) {
        System.out.println(message);
    }

    /**
     * Displays a received message to the user.
     *
     * @param message The message to be displayed.
     */
    public void showReceivedMessage(String message) {
        System.out.println(message);
    }

    /**
     * Closes the scanner.
     */
    public void close() {
        scanner.close();
    }
}