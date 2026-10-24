import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Task 3: ATM Interface
 * Represents the User's Bank Account storing balance and account details.
 */
public class BankAccount {
    private final String accountNumber;
    private final String accountHolderName;
    private double balance;
    private final List<String> transactionHistory;

    public BankAccount(String accountNumber, String accountHolderName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = Math.max(initialBalance, 0.0);
        this.transactionHistory = new ArrayList<>();
        recordTransaction("Initial Deposit", this.balance);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    /**
     * Deposits positive amount into the bank account.
     * @param amount the amount to deposit
     * @return true if successful, false otherwise
     */
    public boolean deposit(double amount) {
        if (amount <= 0) {
            return false;
        }
        balance += amount;
        recordTransaction("Deposit", amount);
        return true;
    }

    /**
     * Withdraws amount from account if sufficient balance exists.
     * @param amount the amount to withdraw
     * @return true if withdrawal successful, false if insufficient funds or invalid amount
     */
    public boolean withdraw(double amount) {
        if (amount <= 0 || amount > balance) {
            return false;
        }
        balance -= amount;
        recordTransaction("Withdrawal", -amount);
        return true;
    }

    private void recordTransaction(String type, double amount) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String sign = amount >= 0 ? "+" : "-";
        transactionHistory.add(String.format("[%s] %-15s : %s$%.2f (Balance: $%.2f)",
                timestamp, type, sign, Math.abs(amount), balance));
    }

    public List<String> getTransactionHistory() {
        return Collections.unmodifiableList(transactionHistory);
    }
}
