import java.util.Objects;

public class Account {

    private int accountNumber;
    private String ownerName;
    private double balance;

    public Account(int accountNumber, String ownerName, double balance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public double getBalance() {
        return balance;
    }

    
    @Override
    public String toString() {
        return "Account Number: " + accountNumber +
               ", Owner Name: " + ownerName +
               ", Balance: ₹" + balance;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Account account = (Account) o;
        return accountNumber == account.accountNumber;
    }

   
    @Override
    public int hashCode() {
        return Objects.hash(accountNumber);
    }
}