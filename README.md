# Task 3: ATM Interface

An Object-Oriented ATM Machine Interface built in Java for the CodSoft Java Development Internship.

---

## 🎯 Task Description
1. **ATM Class**: Create a class `ATM` to represent the ATM machine with menu navigation.
2. **User Interface**: Design options for:
   - Withdrawing funds
   - Depositing funds
   - Checking current account balance
   - Transaction History / Mini Statement
3. **Core Methods**: Implement methods for `withdraw(amount)`, `deposit(amount)`, and `checkBalance()`.
4. **Bank Account Class**: Create a class `BankAccount` to represent the user's account and securely store the balance.
5. **Class Connection**: Connect the `ATM` class with the `BankAccount` class so operations safely inspect and mutate the balance.
6. **Input Validation**: Validate user inputs to prevent negative amounts, non-numeric values, or overdrawing beyond the available balance.
7. **Feedback Messages**: Display clear, user-friendly success and failure notifications for every transaction.

---

## 🏗️ Architecture & OOP Design
- **`BankAccount.java`**: Encapsulates account details (account holder name, account number, balance, and transaction history log). Enforces transaction business rules.
- **`ATM.java`**: Handles user interaction, input parsing, validation, and delegates business logic to the connected `BankAccount`.
- **`ATMInterface.java`**: Main entry point initializing a `BankAccount` and connecting it with an `ATM` instance.

---

## 🛠️ How to Compile & Run

### 1. Compile
```bash
javac Task3_ATMInterface/src/*.java
```

### 2. Run
```bash
java -cp Task3_ATMInterface/src ATMInterface
```
