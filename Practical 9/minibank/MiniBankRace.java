package minibank;

public class MiniBankRace {

    public static void main(String[] args)
            throws InterruptedException {

        Account account = new Account(0);

        Thread t1 = new Thread(
                new AccountWorker(account, 2, 10),
                "Worker-1");

        Thread t2 = new Thread(
                new AccountWorker(account, 2, 10),
                "Worker-2");

        Thread t3 = new Thread(
                new AccountWorker(account, 2, 10),
                "Worker-3");

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println("Final Balance: "
                + account.balance);

        System.out.println("Expected Balance: 60");
    }
}