import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Task 3: ATM Interface
 * Represents the ATM Machine interacting with a BankAccount.
 */
public class ATM {
    private final BankAccount account;
    private final Scanner scanner;

    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Starts the ATM UI loop.
     */
    public void start() {
        printWelcomeBanner();

        boolean exit = false;
        while (!exit) {
            displayMenu();
            int choice = readUserChoice();

            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    handleDeposit();
                    break;
                case 3:
                    handleWithdrawal();
                    break;
                case 4:
                    printMiniStatement();
                    break;
                case 5:
                    exit = true;
                    System.out.println("\n>> Thank you for banking with CodSoft ATM. Please take your card. Goodbye!");
                    break;
                default:
                    System.out.println(">> Invalid choice! Please select an option between 1 and 5.");
            }

            if (!exit) {
                promptEnterToContinue();
            }
        }
    }

    private void printWelcomeBanner() {
        System.out.println("==================================================");
        System.out.println("          WELCOME TO CODSOFT DIGITAL ATM          ");
        System.out.println("==================================================");
        System.out.printf("  Account Holder : %s%n", account.getAccountHolderName());
        System.out.printf("  Account Number : %s%n", maskAccountNumber(account.getAccountNumber()));
        System.out.println("==================================================");
    }

    private void displayMenu() {
        System.out.println("\n----------------- MAIN MENU ----------------------");
        System.out.println("  1. Check Balance");
        System.out.println("  2. Deposit Funds");
        System.out.println("  3. Withdraw Funds");
        System.out.println("  4. Mini Statement (Transaction History)");
        System.out.println("  5. Exit / Eject Card");
        System.out.println("--------------------------------------------------");
        System.out.print("Please enter your choice (1-5): ");
    }

    private int readUserChoice() {
        try {
            int choice = scanner.nextInt();
            scanner.nextLine(); // clear newline
            return choice;
        } catch (InputMismatchException e) {
            scanner.nextLine(); // clear invalid input
            return -1;
        }
    }

    /**
     * Displays the current account balance.
     */
    public void checkBalance() {
        System.out.println("\n--- [BALANCE INQUIRY] ---");
        System.out.printf("Current Available Balance: $%.2f%n", account.getBalance());
    }

    /**
     * Handles depositing funds into account.
     */
    private void handleDeposit() {
        System.out.println("\n--- [DEPOSIT FUNDS] ---");
        System.out.print("Enter amount to deposit ($): ");
        double amount = readPositiveAmount();

        if (amount <= 0) {
            System.out.println(">> Deposit Failed: Amount must be greater than $0.00.");
            return;
        }

        deposit(amount);
    }

    public void deposit(double amount) {
        boolean success = account.deposit(amount);
        if (success) {
            System.out.printf("[SUCCESS] Successfully deposited $%.2f into your account.%n", amount);
            System.out.printf("[INFO] Updated Balance: $%.2f%n", account.getBalance());
        } else {
            System.out.println("[FAILED] Error: Deposit operation could not be completed.");
        }
    }

    /**
     * Handles withdrawing funds from account with limit validation.
     */
    private void handleWithdrawal() {
        System.out.println("\n--- [WITHDRAW FUNDS] ---");
        System.out.printf("Available Balance: $%.2f%n", account.getBalance());
        System.out.print("Enter amount to withdraw ($): ");
        double amount = readPositiveAmount();

        if (amount <= 0) {
            System.out.println(">> Withdrawal Failed: Amount must be greater than $0.00.");
            return;
        }

        withdraw(amount);
    }

    public void withdraw(double amount) {
        if (amount > account.getBalance()) {
            System.out.println("[FAILED] Transaction Denied: Insufficient Funds!");
            System.out.printf("         Requested Amount: $%.2f | Available Balance: $%.2f%n", amount, account.getBalance());
            return;
        }

        boolean success = account.withdraw(amount);
        if (success) {
            System.out.printf("[SUCCESS] Please collect your cash of $%.2f.%n", amount);
            System.out.printf("[INFO] Remaining Balance: $%.2f%n", account.getBalance());
        } else {
            System.out.println("[FAILED] Error: Unable to complete withdrawal.");
        }
    }

    /**
     * Prints transaction history / statement.
     */
    private void printMiniStatement() {
        System.out.println("\n--- [MINI STATEMENT] ---");
        List<String> history = account.getTransactionHistory();
        if (history.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            for (String record : history) {
                System.out.println("  " + record);
            }
        }
        System.out.printf("Final Balance: $%.2f%n", account.getBalance());
    }

    private double readPositiveAmount() {
        try {
            double amt = scanner.nextDouble();
            scanner.nextLine(); // clear newline
            return amt;
        } catch (InputMismatchException e) {
            scanner.nextLine(); // clear invalid input
            return -1.0;
        }
    }

    private void promptEnterToContinue() {
        System.out.print("\nPress [Enter] to return to menu...");
        scanner.nextLine();
    }

    private String maskAccountNumber(String acc) {
        if (acc == null || acc.length() < 4) return "****";
        return "**** **** " + acc.substring(acc.length() - 4);
    }
}
