import java.util.*;

public class StockIssue {

    
    static class OutOfStockException extends Exception {

        private int shortfall;

        public OutOfStockException(String message, int shortfall) {
            super(message);
            this.shortfall = shortfall;
        }

        public int getShortfall() {
            return shortfall;
        }
    }


    static class InvalidQuantityException extends Exception {

        public InvalidQuantityException(String message) {
            super(message);
        }
    }


    static class Warehouse {

        private Map<String, Integer> stock = new HashMap<>();

        public Warehouse() {
            stock.put("Laptop", 5);
            stock.put("Mouse", 10);
            stock.put("Keyboard", 3);
        }

        public void issue(String item, int quantity)
                throws OutOfStockException, InvalidQuantityException {

            if (quantity <= 0) {
                throw new InvalidQuantityException(
                        "Quantity must be greater than zero."
                );
            }

            int available = stock.getOrDefault(item, 0);

            if (available < quantity) {

                int shortfall = quantity - available;

                throw new OutOfStockException(
                        "Not enough stock for " + item,
                        shortfall
                );
            }

            stock.put(item, available - quantity);

            System.out.println(
                    "Issued " + quantity + " " + item + "(s) successfully."
            );
        }
    }

    public static void main(String[] args) {

        Warehouse warehouse = new Warehouse();

        String[] items = {
                "Laptop",
                "Mouse",
                "Keyboard",
                "Laptop"
        };

        int[] quantities = {
                2,
                20,
                0,
                3
        };

        for (int i = 0; i < items.length; i++) {

            System.out.println("\nRequest: "
                    + quantities[i] + " " + items[i]);

            try {

                warehouse.issue(items[i], quantities[i]);

            } catch (OutOfStockException exception) {

                System.out.println("Out of stock: "
                        + exception.getMessage());

                System.out.println(
                        "Shortfall = " + exception.getShortfall()
                );

            } catch (InvalidQuantityException exception) {

                System.out.println(
                        "Invalid quantity: "
                                + exception.getMessage()
                );
            }
        }

        System.out.println("\nAll requests processed.");
    }
}