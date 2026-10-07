package minibank;

public class Account {

    long accountNumber;
    long balance;

    Account(long accountNumber, long balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    synchronized void deposit(long amount) {
        balance = balance + amount;

        System.out.println(
                Thread.currentThread().getName()
                        + " deposited " + amount);
    }

    synchronized void withdraw(long amount) {
        if (amount <= balance) {
            balance = balance - amount;

            System.out.println(
                    Thread.currentThread().getName()
                            + " withdrew " + amount);
        }
    }
}