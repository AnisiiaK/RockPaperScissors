import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        //String[] arg = {"rock", "paper", "scissors", "lizard", "spock"};
        // String[] arg = {"rock", "rock", "paper"};

        RockPaperScissors game = new RockPaperScissors(args);
        game.printHmac();

        int userMove;
        do {
            game.printMovesMenu();
            System.out.print("Enter your move: ");
            Scanner scanner = new Scanner(System.in);
            userMove = scanner.nextInt();
        } while (userMove < 0 || userMove > args.length);

        if (userMove == 0)
            System.exit(0);
        game.setUserMove(userMove - 1);

        game.startGame();

    }

}
