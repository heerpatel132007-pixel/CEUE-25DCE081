interface Transactable {

    void deposit(long amount);

    boolean withdraw(long amount);
}

interface InterestBearing {

    double interestRate();

    double getBalance();

    default double yearlyInterest() {
        return getBalance() * interestRate() / 100;
    }
}

// Functional interface
@FunctionalInterface
interface WithdrawRule {

    boolean allow(Account account, long amount);
}

// Marker interface
interface Premium {
}


public abstract class Account
        implements Transactable, InterestBearing {

    int no;
    String name;
    double balance;

    Account(int no, String name, double balance) {
        this.no = no;
        this.name = name;
        this.balance = balance;
    }

    public abstract double interestRate();

    public abstract boolean canWithdraw(long amount);

    public double getBalance() {
        return balance;
    }

    @Override
    public void deposit(long amount) {

        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount);
        }
    }

    @Override
    public boolean withdraw(long amount) {

        if (amount > 0 && canWithdraw(amount)) {
            balance -= amount;
            return true;
        }

        return false;
    }

    @Override
    public String toString() {

        return "Account No: " + no
                + ", Owner: " + name
                + ", Balance: " + balance;
    }

    @Override
    public boolean equals(Object o) {

        return o instanceof Account
                && no == ((Account) o).no;
    }
}


// Savings Account
class SavingsAccount extends Account
        implements Premium {

    long minBalance;

    SavingsAccount(
            int no,
            String name,
            double balance,
            long minBalance) {

        super(no, name, balance);
        this.minBalance = minBalance;
    }

    public double interestRate() {
        return 4.0;
    }

    public boolean canWithdraw(long amount) {
        return balance - amount >= minBalance;
    }
}


// Current Account
class CurrentAccount extends Account {

    long overdraftLimit;

    CurrentAccount(
            int no,
            String name,
            double balance,
            long overdraftLimit) {

        super(no, name, balance);
        this.overdraftLimit = overdraftLimit;
    }

    public double interestRate() {
        return 0.0;
    }

    public boolean canWithdraw(long amount) {
        return balance - amount >= -overdraftLimit;
    }
}


// Fixed Deposit Account
class FixedDepositAccount extends Account {

    FixedDepositAccount(
            int no,
            String name,
            double balance) {

        super(no, name, balance);
    }

    public double interestRate() {
        return 7.0;
    }

    public boolean canWithdraw(long amount) {
        return false;
    }
}