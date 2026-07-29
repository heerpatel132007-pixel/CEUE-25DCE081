public class Account {

    private final String accountNumber;
    private long balance;
    private static int count = 1;

    private static String generateAccountNumber() {
        return "AC000" + count++;
    }

    Account(String ownerName, long balance) {
        this.balance = balance;
        accountNumber = generateAccountNumber();
    }

    Account(String ownerName) {
        this(ownerName, 0);
    }

    public void deposit(long amount) {
        balance += amount;
    }

    public boolean withdraw(long amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public long getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}