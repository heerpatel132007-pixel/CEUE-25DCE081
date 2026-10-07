package minibank;

public class MiniBank {

    public static void main(String[] args)
            throws InterruptedException {

        Account a = new Account(101);
        Account b = new Account(102);

        TransactionProcessor processor =
                new TransactionProcessor();

        processor.submit(() -> a.deposit(100));
        processor.submit(() -> a.deposit(200));
        processor.submit(() -> a.withdraw(50));
        processor.submit(() -> a.deposit(150));

        processor.stop();

        System.out.println("Balance: " + a.balance);

        int[] transactions = new int[3];
        int count = 0;

        synchronized (transactions) {

            transactions[count++] = 100;
            System.out.println("Added: 100");

            transactions[count++] = 200;
            System.out.println("Added: 200");

            transactions[count++] = 300;
            System.out.println("Added: 300");

            transactions.notify();
        }

        Thread t1 = new Thread(() -> {

            Account first;
            Account second;

            if (a.accountNumber < b.accountNumber) {
                first = a;
                second = b;
            } else {
                first = b;
                second = a;
            }

            synchronized (first) {
                synchronized (second) {

                    if (a.balance >= 100) {
                        a.balance -= 100;
                        b.balance += 100;

                        System.out.println(
                                "Transfer A to B: 100");
                    }
                }
            }
        }, "Transfer-1");

        Thread t2 = new Thread(() -> {

            Account first;
            Account second;

            if (a.accountNumber < b.accountNumber) {
                first = a;
                second = b;
            } else {
                first = b;
                second = a;
            }

            synchronized (first) {
                synchronized (second) {

                    if (b.balance >= 200) {
                        b.balance -= 200;
                        a.balance += 200;

                        System.out.println(
                                "Transfer B to A: 200");
                    }
                }
            }
        }, "Transfer-2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(
                "Final A Balance: " + a.balance);

        System.out.println(
                "Final B Balance: " + b.balance);
    }
}