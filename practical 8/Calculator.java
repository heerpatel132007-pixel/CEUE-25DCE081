import java.util.Scanner;

public class Calculator {

    static class DivideByZeroException extends Exception {
        public DivideByZeroException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean calculationSuccessful = false;

        while (!calculationSuccessful) {

            try {
                System.out.print("Enter first number: ");
                double firstNumber = Double.parseDouble(scanner.nextLine());

                System.out.print("Enter second number: ");
                double secondNumber = Double.parseDouble(scanner.nextLine());

                System.out.print("Enter operator (+, -, *, /): ");
                String operator = scanner.nextLine();

                double result;

                switch (operator) {

                    case "+":
                        result = firstNumber + secondNumber;
                        break;

                    case "-":
                        result = firstNumber - secondNumber;
                        break;

                    case "*":
                        result = firstNumber * secondNumber;
                        break;

                    case "/":
                        if (secondNumber == 0) {
                            throw new DivideByZeroException(
                                    "Cannot divide by zero."
                            );
                        }
                        result = firstNumber / secondNumber;
                        break;

                    default:
                        System.out.println("Invalid operator.");
                        continue;
                }

                System.out.println("Result = " + result);
                calculationSuccessful = true;

            } catch (NumberFormatException exception) {

                System.out.println("Invalid number. Please enter numbers only.");

            } catch (DivideByZeroException exception) {

                System.out.println("Error: " + exception.getMessage());

            } finally {

                System.out.println("Calculation attempt completed.");
                System.out.println("-----------------------------");
            }
        }

        scanner.close();
        System.out.println("Valid calculation completed.");
    }
}