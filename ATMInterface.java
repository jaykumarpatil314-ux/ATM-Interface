/**
 * Task 3: ATM Interface (Driver Main Class)
 * CodSoft Java Development Internship
 * 
 * Sets up a user BankAccount and launches the interactive ATM system.
 */
public class ATMInterface {

    public static void main(String[] args) {
        // Instantiate a bank account with initial customer details and balance
        BankAccount userAccount = new BankAccount("1234567890", "Alex Johnson", 1000.00);

        // Connect the ATM system with the user's bank account
        ATM atm = new ATM(userAccount);

        // Start the ATM interface
        atm.start();
    }
}
