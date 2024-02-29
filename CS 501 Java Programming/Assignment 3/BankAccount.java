
//Create a Java class named `BankAccount` for simulating basic bank operations
public class BankAccount {
    //Data fields
    String accountNumber; //The account number
    String accountHolder; //The name of the account holder
    double balance; //The current balance

    //Constructors
    //No-argument constructor
    //Initialize `accountNumber` and `accountHolder` to empty strings and `balance` to zero
    public BankAccount() {
        accountNumber = "";
        accountHolder = "";
        balance = 0;
    }

    //Constructor with parameters - initialize all fields with given values
    public BankAccount(String id, String owner, double availableFunds) {
        accountNumber = id;
        accountHolder = owner;
        balance = availableFunds;
    }

    //Methods:
    //Increases the `balance` by the given `amount`
    public void deposit(double amount) {
        this.balance += amount;
    }

    //Increases the `balance` by the given `amount`
    public void withdraw(double amount) {
        if ((this.balance - amount) >= 0) {
            this.balance -= amount;
        }
        else System.out.println(("Not enough balance to withdraw " + amount));
    }

    public static void main(String[] args) {

        // Create a BankAccount object using the second constructor
        BankAccount account1 = new BankAccount("992344", "J K", 1000.0);

        // Deposit and withdraw funds
        account1.deposit(500.0);
        account1.withdraw(200.0);

        // Display account information and updated balance
        System.out.println("Account Holder: " + account1.accountHolder);
        System.out.println("Account Number: " + account1.accountNumber);
        System.out.println("Balance: $" + account1.balance);

    }
}
