import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("WELCOME TO MUB BANK!!");

        // Hardcoded user ID, PIN, and account number for demonstration purposes
        int hardcodedUserId = 123456;
        int hardcodedPin = 7890;
        int hardcodedAccountNumber = 987654;

        ATMSystem atmSystem = new ATMSystem(hardcodedUserId, hardcodedPin, hardcodedAccountNumber, 10000);

        // Prompt for user ID and PIN
        int enteredUserId = atmSystem.getIntegerInput(scanner, "Enter user ID: ");
        int enteredPin = atmSystem.getIntegerInput(scanner, "Enter PIN: ");

        // Simulate user authentication
        User user = atmSystem.authenticateUser(enteredUserId, enteredPin);

        if (user != null) {
            // User authenticated, unlock ATM functionalities
            atmSystem.runATM(scanner, user);
        } else {
            System.out.println("Authentication failed. Exiting.");
        }
    }
}