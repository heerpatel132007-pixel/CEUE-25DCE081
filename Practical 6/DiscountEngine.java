import java.util.*;

@FunctionalInterface
interface DiscountRule {
    double apply(double price);
}

public class DiscountEngine {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        List<Double> prices = Arrays.asList(
            1000.0,
            2000.0,
            500.0,
            1500.01
        );

        System.out.println("---- Discount Engine ----");

        System.out.println("1. 10% Discount");
        System.out.println("2. 20% Discount");
        System.out.println("3. Flat Rs. 100 Discount");

        System.out.print("Choose discount rule: ");
        int choice = scanner.nextInt();

        DiscountRule discountRule;

        switch (choice) {

            case 1:
                discountRule = price -> price - (price * 0.10);
                break;

            case 2:
                discountRule = price -> price - (price * 0.20);
                break;

            case 3:
                discountRule = price -> price - 100;
                break;

            default:
                System.out.println("Invalid choice.");
                scanner.close();
                return;
        }

        System.out.println();
        System.out.println("Original Price -> Final Price");

        for (double price : prices) {

            double finalPrice = discountRule.apply(price);

            System.out.println(
                "Rs. " + price + " -> Rs. " + finalPrice
            );
        }

        scanner.close();
    }
}