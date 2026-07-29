public class MiniBank {

    public static void main(String[] args) {

        Account[] a = new Account[3];

        a[0] = new Account("Heer", 1000);
        a[1] = new Account("Diya");
        a[2] = new Account("Dhanvi", 500);

        a[0].deposit(500);
        a[1].deposit(1000);
        a[2].withdraw(200);

        for (int i = 0; i < 3; i++) {
            System.out.println(a[i].getAccountNumber() +
                    " Balance: " + a[i].getBalance());
        }
    }
}