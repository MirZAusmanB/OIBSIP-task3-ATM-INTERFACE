import java.util.InputMismatchException;
import java.util.Scanner;

class ATMSystem {
    private int hardcodedUserId;
    private int hardcodedPin;
    private int hardcodedAccountNumber;
    private double initialBalance;

    public ATMSystem(int hardcodedUserId, int hardcodedPin, int hardcodedAccountNumber, double initialBalance) {
        this.hardcodedUserId = hardcodedUserId;
        this.hardcodedPin = hardcodedPin;
        this.hardcodedAccountNumber = hardcodedAccountNumber;
        this.initialBalance = initialBalance;
    }

    public User authenticateUser(int enteredUserId, int enteredPin) {
        return (enteredUserId == hardcodedUserId && enteredPin == hardcodedPin) ?
                new User(enteredUserId, hardcodedAccountNumber, initialBalance) :
                null;
    }

    public void runATM(Scanner scanner, User user) {
        while (true) {
            int choice = getIntegerInput(scanner, user.displayMenu());

            switch (choice) {
                case Constants.CHECK_BALANCE:
                    user.checkBalance();
                    break;
                case Constants.DEPOSIT:
                    handleDeposit(scanner, user);
                    break;
                case Constants.WITHDRAW:
                    handleWithdrawal(scanner, user);
                    break;
                case Constants.TRANSFER:
                    handleTransfer(scanner, user, hardcodedAccountNumber);
                    break;
                case Constants.TRANSACTION_HISTORY:
                    user.displayTransactionHistory();
                    break;
                case Constants.EXIT:
                    System.out.println("Exiting ATM. Thank you!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void handleDeposit(Scanner scanner, User user) {
        double depositAmount = getDoubleInput(scanner, "Enter deposit amount: ");
        user.deposit(depositAmount);
        user.checkBalance(); // Display current balance
    }

    private void handleWithdrawal(Scanner scanner, User user) {
        double withdrawalAmount = getDoubleInput(scanner, "Enter withdrawal amount: ");
        user.withdraw(withdrawalAmount);
        user.checkBalance(); // Display current balance
    }

    private void handleTransfer(Scanner scanner, User user, int targetAccountNumber) {
        int targetAccount = getIntegerInput(scanner, "Enter account number to transfer: ");

        if (targetAccount == targetAccountNumber) {
            double transferAmount = getDoubleInput(scanner, "Enter amount to transfer: ");
            user.transfer(transferAmount);
            user.checkBalance(); // Display current balance
        } else {
            System.out.println("Invalid account number. Transfer failed.");
        }
    }

    public int getIntegerInput(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // consume the invalid input
            }
        }
    }

    public double getDoubleInput(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // consume the invalid input
            }
        }
    }
}