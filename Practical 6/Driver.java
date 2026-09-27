public class Driver {

    public static void main(String[] args) {

        // --------------------------------
        // Create Accounts
        // --------------------------------

        Account a1 = new SavingsAccount(
                101, "Lavanya", 5000, 2000);

        Account a2 = new CurrentAccount(
                101, "Rahul", 7000, 3000);

        Account a3 = new FixedDepositAccount(
                102, "Amit", 9000);


        // --------------------------------
        // Display Accounts
        // --------------------------------

        System.out.println("--- Accounts ---");

        System.out.println(a1);
        System.out.println(a2);
        System.out.println(a3);


        // --------------------------------
        // Equals
        // --------------------------------

        System.out.println(
                "\na1 equals a2: "
                        + a1.equals(a2));

        System.out.println(
                "a1 equals a3: "
                        + a1.equals(a3));


        // --------------------------------
        // Validators
        // --------------------------------

        System.out.println("\n--- Validators ---");

        System.out.println(
                "Mobile: "
                        + MiniBank.mobile("9876543210"));

        System.out.println(
                "Wrong Mobile: "
                        + MiniBank.mobile("12345"));

        System.out.println(
                "Email: "
                        + MiniBank.email("test@gmail.com"));

        System.out.println(
                "Wrong Email: "
                        + MiniBank.email("test@gmail"));

        System.out.println(
                "PAN: "
                        + MiniBank.pan("ABCDE1234F"));

        System.out.println(
                "Wrong PAN: "
                        + MiniBank.pan("ABC123"));

        System.out.println(
                "IFSC: "
                        + MiniBank.ifsc("SBIN0001234"));

        System.out.println(
                "Wrong IFSC: "
                        + MiniBank.ifsc("ABC123"));


        // --------------------------------
        // Command
        // --------------------------------

        System.out.println("\n--- Command ---");

        String[] p =
                "DEPOSIT AC0001 500".split(" ");

        System.out.println("Type: " + p[0]);
        System.out.println("Account: " + p[1]);
        System.out.println("Amount: " + p[2]);


        // --------------------------------
        // Interest Rates
        // --------------------------------

        System.out.println(
                "\n--- Account Interest Rates ---");

        Account[] accounts = {
                a1, a2, a3
        };

        for (Account account : accounts) {

            System.out.println(
                    account.name + " -> "
                            + account.interestRate()
                            + "%"
            );

            System.out.println(
                    "Yearly Interest: "
                            + account.yearlyInterest()
            );

            if (account instanceof FixedDepositAccount) {

                System.out.println(
                        "Special Note: Fixed Deposit "
                                + "is locked. Withdrawal allowed: "
                                + account.canWithdraw(1000)
                );
            }
        }


        // --------------------------------
        // Deposit and Withdraw
        // --------------------------------

        System.out.println(
                "\n--- Deposit / Withdraw ---");

        a1.deposit(1000);

        System.out.println(
                "New Savings Balance: "
                        + a1.balance);

        System.out.println(
                "Withdraw 500: "
                        + a1.withdraw(500));

        System.out.println(
                "Balance after withdrawal: "
                        + a1.balance);


        // --------------------------------
        // Withdraw Rule
        // Anonymous Class
        // --------------------------------

        System.out.println(
                "\n--- Withdrawal Rules ---");

        WithdrawRule anonymousRule =
                new WithdrawRule() {

                    public boolean allow(
                            Account account,
                            long amount) {

                        return account.canWithdraw(amount);
                    }
                };

        System.out.println(
                "Anonymous class - "
                        + "Savings withdrawal 2500: "
                        + anonymousRule.allow(a1, 2500)
        );


        // --------------------------------
        // Withdraw Rule
        // Lambda
        // --------------------------------

        WithdrawRule lambdaRule =
                (account, amount)
                        -> account.canWithdraw(amount);

        System.out.println(
                "Lambda - Current withdrawal 9000: "
                        + lambdaRule.allow(a2, 9000)
        );

        System.out.println(
                "Lambda - Fixed Deposit withdrawal 1000: "
                        + lambdaRule.allow(a3, 1000)
        );


        // --------------------------------
        // Premium Marker Interface
        // --------------------------------

        System.out.println(
                "\n--- Premium Check ---");

        if (a1 instanceof Premium) {

            System.out.println(
                    "Lavanya's Savings Account "
                            + "is Premium.");
        }


        // --------------------------------
        // Statement
        // --------------------------------

        System.out.println();

        MiniBank.statement(a1);
    }
}