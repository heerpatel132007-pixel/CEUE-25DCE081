import java.util.*;

enum Move { ROCK, PAPER, SCISSORS, LIZARD, SPOCK }

public class RPSLS {

    static int winner(Move p, Move c) {
        if (p == c) return 0;

        return switch (p) {
            case ROCK -> (c == Move.SCISSORS || c == Move.LIZARD) ? 1 : -1;
            case PAPER -> (c == Move.ROCK || c == Move.SPOCK) ? 1 : -1;
            case SCISSORS -> (c == Move.PAPER || c == Move.LIZARD) ? 1 : -1;
            case LIZARD -> (c == Move.PAPER || c == Move.SPOCK) ? 1 : -1;
            case SPOCK -> (c == Move.ROCK || c == Move.SCISSORS) ? 1 : -1;
        };
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random r = new Random();

        int player = 0, computer = 0;

        for (int i = 1; i <= 5; i++) {

            Move c = Move.values()[r.nextInt(5)];

            System.out.print("Enter move: ");
            Move p = Move.valueOf(sc.next().toUpperCase());

            int result = winner(p, c);

            System.out.println("Computer: " + c);

            if (result == 1) {
                System.out.println("You Win");
                player++;
            } else if (result == -1) {
                System.out.println("Computer Wins");
                computer++;
            } else {
                System.out.println("Tie");
            }
        }

        System.out.println("Score: You " + player + " - " + computer + " Computer");

        sc.close();
    }
}