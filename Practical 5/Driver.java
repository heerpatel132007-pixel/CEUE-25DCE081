public class Driver {

    public static void main(String[] args) {

       
        Account a1 = new SavingsAccount(
                101, "Lavanya", 5000, 2000);

        Account a2 = new CurrentAccount(
                101, "Rahul", 7000, 3000);

        Account a3 = new FixedDepositAccount(
                102, "Amit", 9000);


      
        System.out.println(a1);
        System.out.println(a2);
        System.out.println(a3);


       
        System.out.println("\na1 equals a2: " + a1.equals(a2));
        System.out.println("a1 equals a3: " + a1.equals(a3));


      
        System.out.println("\n--- Validators ---");

        System.out.println("Mobile: "
                + MiniBank.mobile("9876543210"));

        System.out.println("Wrong Mobile: "
                + MiniBank.mobile("12345"));

        System.out.println("Email: "
                + MiniBank.email("test@gmail.com"));

        System.out.println("Wrong Email: "
                + MiniBank.email("test@gmail"));

        System.out.println("PAN: "
                + MiniBank.pan("ABCDE1234F"));

        System.out.println("Wrong PAN: "
                + MiniBank.pan("ABC123"));

        System.out.println("IFSC: "
                + MiniBank.ifsc("SBIN0001234"));

        System.out.println("Wrong IFSC: "
                + MiniBank.ifsc("ABC123"));


      
        System.out.println("\n--- Command ---");

        String[] p = "DEPOSIT AC0001 500".split(" ");

        System.out.println("Type: " + p[0]);
        System.out.println("Account: " + p[1]);
        System.out.println("Amount: " + p[2]);


       
        System.out.println("\n--- Account Interest Rates ---");

        Account[] accounts = {a1, a2, a3};

        for (Account account : accounts) {

            System.out.println(
                account.name + " -> "
                + account.interestRate() + "%"
            );

           
            if (account instanceof FixedDepositAccount fd) {
                System.out.println(
                    "Special Note: Fixed Deposit is locked. "
                    + "Withdrawal allowed: "
                    + fd.canWithdraw(1000)
                );
            }
        }


       
        System.out.println("\n--- Withdrawal Rules ---");

        System.out.println(
            "Savings withdrawal 2500: "
            + a1.canWithdraw(2500)
        );

        System.out.println(
            "Current withdrawal 9000: "
            + a2.canWithdraw(9000)
        );

        System.out.println(
            "Fixed Deposit withdrawal 1000: "
            + a3.canWithdraw(1000)
        );


      
        System.out.println();
        MiniBank.statement(a1);
    }
}