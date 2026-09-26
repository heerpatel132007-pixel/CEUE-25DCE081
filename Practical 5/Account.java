public abstract class Account {

    int no;
    String name;
    double balance;

    Account(int no, String name, double balance) {
        this.no = no;
        this.name = name;
        this.balance = balance;
    }

   
    abstract double interestRate();

    abstract boolean canWithdraw(long amount);

    public String toString() {
        return "Account No: " + no + ", Owner: " + name +
               ", Balance: " + balance;
    }

    public boolean equals(Object o) {
        return o instanceof Account &&
               no == ((Account) o).no;
    }
}



class SavingsAccount extends Account {

    long minBalance;

    SavingsAccount(int no, String name, double balance, long minBalance) {
        super(no, name, balance);
        this.minBalance = minBalance;
    }

    double interestRate() {
        return 4.0;
    }

    boolean canWithdraw(long amount) {
        return balance - amount >= minBalance;
    }
}



class CurrentAccount extends Account {

    long overdraftLimit;

    CurrentAccount(int no, String name, double balance, long overdraftLimit) {
        super(no, name, balance);
        this.overdraftLimit = overdraftLimit;
    }

    double interestRate() {
        return 0.0;
    }

    boolean canWithdraw(long amount) {
        return balance - amount >= -overdraftLimit;
    }
}



class FixedDepositAccount extends Account {

    FixedDepositAccount(int no, String name, double balance) {
        super(no, name, balance);
    }

    double interestRate() {
        return 7.0;
    }

    boolean canWithdraw(long amount) {
        return false;
    }
}