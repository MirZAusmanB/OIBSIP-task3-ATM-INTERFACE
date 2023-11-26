import java.text.DecimalFormat;
class TransactionHistory {
    private static final int MAX_HISTORY_SIZE = 10;
    private String[] transactions;
    private int index;

    public TransactionHistory() {
        this.transactions = new String[MAX_HISTORY_SIZE];
        this.index = 0;
    }

    public void addTransaction(String description, double amount) {
        transactions[index] = description + ": " + formatCurrency(amount);
        index = (index + 1) % MAX_HISTORY_SIZE;
    }

    public void displayHistory() {
        int startIndex = (index == 0) ? 0 : index;
        for (int i = 0; i < MAX_HISTORY_SIZE; i++) {
            int currentIndex = (startIndex + i) % MAX_HISTORY_SIZE;
            if (transactions[currentIndex] != null) {
                System.out.println(transactions[currentIndex]);
            }
        }
    }

    private String formatCurrency(double amount) {
        return new DecimalFormat("#,###.##").format(amount) + " PKR";
    }
}