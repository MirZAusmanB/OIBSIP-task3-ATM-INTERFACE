import java.text.DecimalFormat;

class User {
    private int userId;
    private int accountNumber;
    private double balance;
    private TransactionHistory transactionHistory;

    private static final DecimalFormat currencyFormat = new DecimalFormat("#,###.##");

    public User(int userId, int accountNumber, double balance) {
        this.userId = userId;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.transactionHistory = new TransactionHistory();
    }

    public String displayMenu() {
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Transfer");
        System.out.println("5. Transaction History");
        System.out.println("6. Exit");
        System.out.print("Choose an option: ");
        return "Choose an option: ";
    }

    public void deposit(double depositAmount) {
        if (depositAmount > 0) {
            balance += depositAmount;
            transactionHistory.addTransaction("Deposit", depositAmount);
            System.out.println("The amount of " + formatCurrency(depositAmount) + " deposited successfully.");
        } else {
            System.out.println("Invalid deposit amount. Please enter a positive value.");
        }
    }

    public void withdraw(double withdrawAmount) {
        if (withdrawAmount > 0) {
            if (withdrawAmount <= balance) {
                balance -= withdrawAmount;
                transactionHistory.addTransaction("Withdrawal", -withdrawAmount);
                System.out.println("The amount of " + formatCurrency(withdrawAmount) + " withdrawn successfully.");
            } else {
                System.out.println("Insufficient funds. Withdrawal amount exceeds account balance.");
            }
        } else {
            System.out.println("Invalid withdrawal amount. Please enter a positive value.");
        }
    }

    public void transfer(double transferAmount) {
        if (transferAmount > 0 && transferAmount <= balance) {
            balance -= transferAmount;
            transactionHistory.addTransaction("Transfer to Account " + accountNumber, -transferAmount);
            System.out.println("The amount of " + formatCurrency(transferAmount) + " transferred successfully.");
        } else {
            System.out.println("Invalid transfer amount or insufficient funds. Transfer failed.");
        }
    }

    public void checkBalance() {
        System.out.println("Your current balance is: " + formatCurrency(balance));
    }

    public void displayTransactionHistory() {
        System.out.println("Transaction History:");
        transactionHistory.displayHistory();
    }

    private String formatCurrency(double amount) {
        return currencyFormat.format(amount) + " PKR";
    }
}