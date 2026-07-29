import java.util.Scanner;

record BankInfo(String name, String branch) {}

enum MenuOption {
    OPEN_ACCOUNT,
    DEPOSIT,
    WITHDRAW,
    TRANSFER,
    EXIT
}

public class MiniBank {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        BankInfo bank = new BankInfo("MiniBank", "Ahmedabad");

        System.out.println("Welcome to " + bank.name());
        System.out.println("Branch: " + bank.branch());

        int choice;

        do {
            System.out.println("\n----- MENU -----");
            System.out.println("1. Open Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> System.out.println("Open Account - To be implemented later.");
                case 2 -> System.out.println("Deposit - To be implemented later.");
                case 3 -> System.out.println("Withdraw - To be implemented later.");
                case 4 -> System.out.println("Transfer - To be implemented later.");
                case 5 -> System.out.println("Thank you for using MiniBank.");
                default -> System.out.println("Invalid choice.");
            }

        } while (choice != 5);

        sc.close();
    }
}