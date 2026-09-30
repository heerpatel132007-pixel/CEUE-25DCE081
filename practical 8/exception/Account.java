package exception;

import java.util.List;

public class Account {

    String name;
    long balance;

    Account(String name, long balance) {
        this.name = name;
        this.balance = balance;
    }

    void withdraw(long amount)
            throws InsufficientFundsException,
            InvalidAmountException {

        if (amount <= 0) {
            throw new InvalidAmountException(
                    "Amount must be greater than 0");
        }

        if (amount > balance) {
            throw new InsufficientFundsException(
                    "Insufficient balance",
                    amount - balance);
        }

        balance = balance - amount;

        System.out.println(
                "Withdraw successful: " + amount);
    }

    void deposit(long amount)
            throws InvalidAmountException {

        if (amount <= 0) {
            throw new InvalidAmountException(
                    "Amount must be greater than 0");
        }

        balance = balance + amount;

        System.out.println(
                "Deposit successful: " + amount);
    }

    void transfer(Account to, long amount)
            throws BankException {

        try {

            if (to == null) {
                throw new AccountNotFoundException(
                        "Account not found");
            }

            withdraw(amount);
            to.deposit(amount);

            System.out.println(
                    "Transfer successful");

        } catch (BankException e) {

            System.out.println(
                    "Transfer failed: "
                            + e.getMessage());

            throw e;

        } finally {

            System.out.println(
                    "Transfer completed");
        }
    }

    static class BankResource
            implements AutoCloseable {

        public void use() {
            System.out.println(
                    "Resource is being used");
        }

        public void close() {
            System.out.println(
                    "Resource closed");
        }
    }

    public static void main(String[] args) {

        Account a1 =
                new Account("Heer", 5000);

        Account a2 =
                new Account("Rahul", 2000);

        System.out.println("----- MINIBANK -----");

      
        try {

            a1.deposit(1000);

        } catch (InvalidAmountException e) {

            System.out.println(e.getMessage());

        } finally {

            System.out.println(
                    "Deposit operation finished");
        }

     
        try {

            a1.withdraw(2000);

        } catch (InsufficientFundsException e) {

            System.out.println(e.getMessage());
            System.out.println(
                    "Shortfall: " + e.shortfall);

        } catch (InvalidAmountException e) {

            System.out.println(e.getMessage());

        } finally {

            System.out.println(
                    "Withdraw operation finished");
        }

    
        try {

            a1.withdraw(10000);

        } catch (InsufficientFundsException e) {

            System.out.println(e.getMessage());
            System.out.println(
                    "Shortfall: " + e.shortfall);

        } catch (InvalidAmountException e) {

            System.out.println(e.getMessage());

        } finally {

            System.out.println(
                    "Withdraw operation finished");
        }

  
        try {

            a1.transfer(a2, 500);

        } catch (BankException e) {

            System.out.println(e.getMessage());
        }

       
        try (BankResource resource =
                     new BankResource()) {

            resource.use();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }

        
        System.out.println();
        System.out.println("----- FORM VALIDATION -----");

        SignupForm form =
                new SignupForm(
                        "",
                        "verylongemailaddress123456789@example.com");

        List<String> errors =
                FormChecker.validate(form);

        for (String error : errors) {
            System.out.println(error);
        }

        System.out.println();

        System.out.println(
                "Final Balance of Heer: "
                        + a1.balance);

        System.out.println(
                "Final Balance of Rahul: "
                        + a2.balance);
    }
}