import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


// ==================== ANNOTATIONS ====================

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Id
{
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Positive
{
    String message() default "must be > 0";
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MaxLength
{
    int value();
}


// ==================== INTERFACES ====================

interface Transactable
{
    void deposit(long amount);

    boolean withdraw(long amount);
}


interface InterestBearing
{
    double interestRate();

    double getBalance();

    default double yearlyInterest()
    {
        return getBalance() * interestRate() / 100;
    }
}


@FunctionalInterface
interface WithdrawRule
{
    boolean allow(Account account, long amount);
}


interface Premium
{
}


// ==================== ACCOUNT ====================

abstract class Account
        implements Transactable, InterestBearing
{
    @Id
    int accountNumber;

    @MaxLength(30)
    String name;

    @Positive
    double balance;


    Account(
            int accountNumber,
            String name,
            double balance)
    {
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = balance;
    }


    public abstract double interestRate();

    public abstract boolean canWithdraw(long amount);


    @Override
    public double getBalance()
    {
        return balance;
    }


    @Override
    public void deposit(long amount)
    {
        if (amount > 0)
        {
            balance += amount;

            System.out.println(
                    "Deposited: " + amount);
        }
    }


    @Override
    public boolean withdraw(long amount)
    {
        if (amount > 0 &&
            canWithdraw(amount))
        {
            balance -= amount;
            return true;
        }

        return false;
    }


    @Override
    public String toString()
    {
        return "Account No: "
                + accountNumber
                + ", Owner: "
                + name
                + ", Balance: "
                + balance;
    }


    @Override
    public boolean equals(Object object)
    {
        return object instanceof Account
                && accountNumber ==
                ((Account) object).accountNumber;
    }


    @Override
    public int hashCode()
    {
        return Integer.hashCode(accountNumber);
    }
}


// ==================== SAVINGS ACCOUNT ====================

class SavingsAccount
        extends Account
        implements Premium
{
    long minBalance;


    SavingsAccount(
            int accountNumber,
            String name,
            double balance,
            long minBalance)
    {
        super(
                accountNumber,
                name,
                balance);

        this.minBalance = minBalance;
    }


    @Override
    public double interestRate()
    {
        return 4.0;
    }


    @Override
    public boolean canWithdraw(long amount)
    {
        return balance - amount >= minBalance;
    }
}


// ==================== CURRENT ACCOUNT ====================

class CurrentAccount
        extends Account
{
    long overdraftLimit;


    CurrentAccount(
            int accountNumber,
            String name,
            double balance,
            long overdraftLimit)
    {
        super(
                accountNumber,
                name,
                balance);

        this.overdraftLimit =
                overdraftLimit;
    }


    @Override
    public double interestRate()
    {
        return 0.0;
    }


    @Override
    public boolean canWithdraw(long amount)
    {
        return balance - amount >=
                -overdraftLimit;
    }
}


// ==================== FIXED DEPOSIT ====================

class FixedDepositAccount
        extends Account
{
    FixedDepositAccount(
            int accountNumber,
            String name,
            double balance)
    {
        super(
                accountNumber,
                name,
                balance);
    }


    @Override
    public double interestRate()
    {
        return 7.0;
    }


    @Override
    public boolean canWithdraw(long amount)
    {
        return false;
    }
}


// ==================== MINIBANK ====================

public class MiniBank
{
    static boolean mobile(String value)
    {
        return Pattern.matches(
                "[6-9][0-9]{9}",
                value);
    }


    static boolean email(String value)
    {
        return Pattern.matches(
                ".+@.+\\..+",
                value);
    }


    static boolean pan(String value)
    {
        return Pattern.matches(
                "[A-Z]{5}[0-9]{4}[A-Z]",
                value);
    }


    static boolean ifsc(String value)
    {
        return Pattern.matches(
                "[A-Z]{4}0[A-Z0-9]{6}",
                value);
    }


    static void statement(Account account)
    {
        StringBuilder text =
                new StringBuilder();

        text.append("Account Statement\n");

        text.append("Account No: ")
                .append(account.accountNumber)
                .append("\n");

        text.append("Owner: ")
                .append(account.name)
                .append("\n");

        text.append("Balance: ")
                .append(account.balance)
                .append("\n");

        text.append("Interest Rate: ")
                .append(account.interestRate())
                .append("%\n");

        text.append("Yearly Interest: ")
                .append(account.yearlyInterest());

        System.out.println(text);
    }
}