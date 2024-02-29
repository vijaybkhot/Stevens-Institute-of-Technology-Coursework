public class BankAccountTest {
    public static void main(String[] args) {
        //Create amount variables
        double amount1 = 500;
        double amount2 = 0.00;
        double amount3 = 2000;
        double amount4 = -500;
        double amount5 = 422.33;

        // Create a BankAccount object using the second constructor
        BankAccount account1 = new BankAccount("992344", "J K", 1000.0);


        // Display account information and updated balance after depositing different amounts
        System.out.println("Account Holder: " + account1.accountHolder + " with account number : " + account1.accountNumber + " has " + account1.balance + " in the account.");

        account1.deposit(amount1);

        System.out.println("After depositing " + amount1 + " the balance would be " + account1.balance);

        account1.deposit(amount2);

        System.out.println("After depositing " + amount2 + " more into the account the balance would be " + account1.balance);

        account1.deposit(amount3);

        System.out.println("After depositing " + amount3 + " more into the account the balance would be " + account1.balance);

        account1.deposit(amount4);

        System.out.println("After depositing " + amount4 + " more into the account the balance would be " + account1.balance);

        account1.deposit(amount5);

        System.out.println("After depositing " + amount5 + " more into the account the balance would be " + account1.balance);


        // Create a second bank account object

        BankAccount account2 = new BankAccount("56432112", "Chris Johnson", 1000.0);


        // Display account information and updated balance after withdrawing different amounts

        System.out.println("Account Holder: " + account2.accountHolder + " with account number : " + account2.accountNumber + " has " + account2.balance + " in the account.");

        account2.withdraw(amount1);

        System.out.println("After withdrawing " + amount1 + " the balance would be " + account2.balance);

        account2.withdraw(amount2);

        System.out.println("After withdrawing " + amount2 + " more from the account the balance would be " + account2.balance);

        account2.withdraw(amount4);

        System.out.println("After withdrawing " + amount4 + " more from the account the balance would be " + account2.balance);

        account2.withdraw(amount5);

        System.out.println("After withdrawing " + amount5 + " more from the account the balance would be " + account2.balance);


        account2.withdraw(amount3);

        System.out.println("After withdrawing " + amount3 + " more from the account the balance would be " + account2.balance);


    }
}
