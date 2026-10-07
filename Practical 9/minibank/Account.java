package minibank;

public class Account {

    long balance;
    public int accountNumber;

    Account(long balance) {
        this.balance = balance;
    }

    void deposit(long amount) {
        long oldBalance = balance;

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }

        balance = oldBalance + amount;
    }

    public Object withdraw(int i) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'withdraw'");
    }

   
}