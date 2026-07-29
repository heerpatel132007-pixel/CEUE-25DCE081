public class ParkingLot {

    private int twoWheelers = 0;
    private int fourWheelers = 0;

    private final int twoCap = 2;
    private final int fourCap = 2;

    private static long revenue = 0;

    void park(String type) {

        if (type.equals("two")) {

            if (twoWheelers < twoCap) {
                twoWheelers++;
                revenue += 20;
                System.out.println("Two Wheeler Parked");
            } else {
                System.out.println("Full");
            }

        } else if (type.equals("four")) {

            if (fourWheelers < fourCap) {
                fourWheelers++;
                revenue += 40;
                System.out.println("Four Wheeler Parked");
            } else {
                System.out.println("Full");
            }
        }
    }

    void leave(String type) {

        if (type.equals("two") && twoWheelers > 0) {
            twoWheelers--;
        }

        if (type.equals("four") && fourWheelers > 0) {
            fourWheelers--;
        }
    }

    public static void main(String[] args) {

        ParkingLot p = new ParkingLot();

        p.park("two");
        p.park("two");
        p.park("two");

        p.park("four");
        p.park("four");
        p.park("four");

        p.leave("two");
        p.leave("four");

        System.out.println("Two Wheelers: " + p.twoWheelers);
        System.out.println("Four Wheelers: " + p.fourWheelers);
        System.out.println("Revenue: " + revenue);
    }
}