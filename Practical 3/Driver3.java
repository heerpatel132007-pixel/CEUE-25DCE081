public class Driver3 {

    public static void main(String[] args) {

        
        Account acc1 = new Account(1001, "Heer Patel", 25000);
        Account acc2 = new Account(1001, "Heer Patel", 50000);
        Account acc3 = new Account(1002, "Rahul Shah", 18000);

        
        System.out.println(acc1);
        System.out.println(acc2);
        System.out.println(acc3);

        
        System.out.println("\nacc1 equals acc2: " + acc1.equals(acc2));
        System.out.println("acc1 equals acc3: " + acc1.equals(acc3));

       
        Customer.Address address =
                new Customer.Address(
                        "101, Green Park",
                        "Ahmedabad",
                        "380001");

        Customer customer1 = new Customer("Heer Patel", address);

       
        Customer customer2 = customer1.clone();

        System.out.println("\nCustomer Name: " + customer2.getName());
        System.out.println("City: " + customer2.getAddress().getCity());

        
        if (acc1 instanceof Account) {
            System.out.println("\nacc1 is an Account object.");
        }

        if (customer1 instanceof Customer) {
            System.out.println("customer1 is a Customer object.");
        }
    }
}