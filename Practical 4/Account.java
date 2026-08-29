public class Account {
    int no;
    String name;
    double balance;

    Account(int no, String name, double balance) {
        this.no = no;
        this.name = name;
        this.balance = balance;
    }

    public String toString() {
        return "Account No: " + no + ", Owner: " + name +
               ", Balance: " + balance;
    }

    public boolean equals(Object o) {
        return o instanceof Account &&
               no == ((Account)o).no;
    }
}