import java.util.Scanner;

record Vehicle(String number, String type) {}

public class TollBooth {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int total = 0;
        int bike = 0, car = 0, truck = 0;

        while (true) {

            System.out.print("Enter vehicle number (or done): ");
            String number = sc.next();

            if (number.equals("done"))
                break;

            System.out.print("Enter vehicle type (bike/car/truck): ");
            String type = sc.next();

            Vehicle v = new Vehicle(number, type);

            switch (v.type()) {
                case "bike":
                    total += 20;
                    bike++;
                    break;

                case "car":
                    total += 50;
                    car++;
                    break;

                case "truck":
                    total += 150;
                    truck++;
                    break;
            }
        }

        System.out.println("Total toll: " + total);

        if (bike >= car && bike >= truck)
            System.out.println("Most frequent: bike");
        else if (car >= bike && car >= truck)
            System.out.println("Most frequent: car");
        else
            System.out.println("Most frequent: truck");

        sc.close();
    }
}