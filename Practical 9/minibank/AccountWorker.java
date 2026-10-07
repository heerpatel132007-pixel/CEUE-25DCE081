package minibank;

public class AccountWorker implements Runnable {

    Account account;
    int times;
    long amount;

    AccountWorker(Account account, int times, long amount) {
        this.account = account;
        this.times = times;
        this.amount = amount;
    }

    public void run() {

        System.out.println(
                Thread.currentThread().getName() + " started");

        for (int i = 1; i <= times; i++) {
            account.deposit(amount);
        }

        System.out.println(
                Thread.currentThread().getName() + " finished");
    }
}