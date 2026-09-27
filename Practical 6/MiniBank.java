import java.util.regex.Pattern;

public class MiniBank {

    // Mobile validation
    static boolean mobile(String s) {
        return Pattern.matches("[6-9][0-9]{9}", s);
    }

    // Email validation
    static boolean email(String s) {
        return Pattern.matches(".+@.+\\..+", s);
    }

    // PAN validation
    static boolean pan(String s) {
        return Pattern.matches(
                "[A-Z]{5}[0-9]{4}[A-Z]", s);
    }

    // IFSC validation
    static boolean ifsc(String s) {
        return Pattern.matches(
                "[A-Z]{4}0[A-Z0-9]{6}", s);
    }

    // Account statement
    static void statement(Account a) {

        StringBuilder s = new StringBuilder();

        s.append("Account Statement\n");
        s.append("Account No: ")
                .append(a.no).append("\n");

        s.append("Owner: ")
                .append(a.name).append("\n");

        s.append("Balance: ")
                .append(a.balance).append("\n");

        s.append("Interest Rate: ")
                .append(a.interestRate())
                .append("%\n");

        s.append("Yearly Interest: ")
                .append(a.yearlyInterest());

        System.out.println(s);
    }
}