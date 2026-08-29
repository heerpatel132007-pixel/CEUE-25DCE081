import java.util.regex.Pattern;

public class MiniBank {

    static boolean mobile(String s) {
        return Pattern.matches("[6-9][0-9]{9}", s);
    }

    static boolean email(String s) {
        return Pattern.matches(".+@.+\\..+", s);
    }

    static boolean pan(String s) {
        return Pattern.matches("[A-Z]{5}[0-9]{4}[A-Z]", s);
    }

    static boolean ifsc(String s) {
        return Pattern.matches("[A-Z]{4}0[A-Z0-9]{6}", s);
    }

    static void statement(Account a) {
        StringBuilder s = new StringBuilder();
        s.append("Account Statement\n");
        s.append("Account No: ").append(a.no).append("\n");
        s.append("Owner: ").append(a.name).append("\n");
        s.append("Balance: ").append(a.balance);
        System.out.println(s);
    }
}