public class CinemaShow {

    private int seatsAvailable;
    private int capacity;
    private static int totalBooked = 0;

    CinemaShow(int capacity) {
        this.capacity = capacity;
        seatsAvailable = capacity;
    }

    boolean book(int n) {
        if (n <= seatsAvailable) {
            seatsAvailable -= n;
            totalBooked += n;
            return true;
        }
        return false;
    }

    void cancel(int n) {
        seatsAvailable += n;
        if (seatsAvailable > capacity)
            seatsAvailable = capacity;
    }

    public static void main(String[] args) {

        CinemaShow s = new CinemaShow(100);

        System.out.println(s.book(20));
        System.out.println("Seats: " + s.seatsAvailable);

        System.out.println(s.book(90));
        System.out.println("Seats: " + s.seatsAvailable);

        s.cancel(10);
        System.out.println("Seats: " + s.seatsAvailable);

        System.out.println("Total Booked: " + totalBooked);
    }
}