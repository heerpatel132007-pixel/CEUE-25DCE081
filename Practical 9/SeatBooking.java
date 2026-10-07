class Booking {
    int seatsLeft = 5;

    void book(String name) {
        if (seatsLeft > 0) {

            try {
                Thread.sleep(10);
            } catch (Exception e) {
            }

            seatsLeft--;
            System.out.println(name + " booked a seat");
        } else {
            System.out.println(name + " could not book");
        }
    }
}

public class SeatBooking {
    public static void main(String[] args) throws Exception {

        Booking booking = new Booking();

        Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {

            final int number = i + 1;

            threads[i] = new Thread(() -> {
                booking.book("Person " + number);
            });

            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("Seats left: " + booking.seatsLeft);
    }
}