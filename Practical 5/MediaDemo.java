abstract class Media {
    String title;
    int lateDays;

    Media(String title, int lateDays) {
        this.title = title;
        this.lateDays = lateDays;
    }

    abstract double lateFee();
}

class Book extends Media {

    Book(String title, int lateDays) {
        super(title, lateDays);
    }

    double lateFee() {
        return lateDays * 2;
    }
}

class Movie extends Media {

    Movie(String title, int lateDays) {
        super(title, lateDays);
    }

    double lateFee() {
        return lateDays * 5;
    }
}

class Game extends Media {

    Game(String title, int lateDays) {
        super(title, lateDays);
    }

    double lateFee() {
        return lateDays * 3;
    }
}

public class MediaDemo {
    public static void main(String[] args) {

        Media[] returnedMedia = {
            new Book("Java Programming", 4),
            new Movie("Avengers", 3),
            new Game("Minecraft", 5)
        };

        double totalFee = 0;

        for (Media media : returnedMedia) {

            double fee = media.lateFee();

            System.out.println(
                media.title + " Late Fee = ₹" + fee
            );

            totalFee = totalFee + fee;
        }

        System.out.println("Total Late Fees = ₹" + totalFee);
    }
}