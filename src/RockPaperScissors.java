import java.util.Random;
import java.security.SecureRandom;


public class RockPaperScissors {
    int userMove;
    int compMove;
    byte[] compKey;
    Moves move;

    RockPaperScissors() {
    }

    RockPaperScissors(String[] arg) {
        move = new Moves(arg);
        compMove = generateCompMove();
        compKey = generateCompKey();
    }

    public int generateCompMove() {
        Random rand = new Random();
        int cM = rand.nextInt(move.length);
        return cM;
    }

    public byte[] generateCompKey() {
        compKey = new byte[16];
        SecureRandom rand = new SecureRandom();
        // Random rand = new Random();
        rand.nextBytes(compKey);
        return compKey;
    }

    public void printMovesMenu() {
        move.printMoves();
    }

    public void printGameMoves() {
        System.out.println("Your move: " + move.getMove(userMove));
        System.out.println("Computer move: " + move.getMove(compMove));
    }

    public void printHmac() {
        System.out.println("HMAC: ");
        System.out.println(HMAC.hmacDigest(move.getMove(compMove), compKey, "HmacSHA256"));
    }

    public void printHmacKey() {
        System.out.println("HMAC key: " + HMAC.hmacKey(move.getMove(compMove), compKey));
    }

    public void setUserMove(int userMove) {
        this.userMove = userMove;
    }

    public void startGame() {
        printGameMoves();
        int cmpMoves = compareMoves();
        if (cmpMoves > 0)
            System.out.println("You won!");
        else if (cmpMoves == 0) {
            System.out.println("The game is a draw!");
        } else System.out.println("You lost!");
        printHmacKey();
    }

    public int compareMoves() {
        if ((compMove + 1) % move.length == userMove) return 1;
        else if (compMove == userMove) return 0;
        else return -1;
        //if((userMove - compMove))
    }


    private class Moves {
        public String[] moves;
        public int length;

        Moves() {
        }

        Moves(String[] arg) {
            length = arg.length;
            checkMoves(arg);
        }

        public void setMoves(String[] arg) {
            moves = new String[length];
            System.arraycopy(arg, 0, moves, 0, length);
        }

        public String getMove(int index) {
            return moves[index];
        }

        public void checkAmount(int amount) {
            if (amount < 3 || amount % 2 == 0) {
                System.out.println("Amount of moves is not correct. Please enter odd amount of moves that is >= 3.");
                System.exit(0);
                // throw new IllegalArgumentException("Amount of moves is not correct. Please enter odd amount of moves.");
            }
        }

        public void checkUniqueMoves(String[] arg) {
            for (int i = 0; i < arg.length; i++)
                for (int j = i + 1; j < arg.length; j++) {
                    if (arg[i].equals(arg[j])) {
                        System.out.println("Format of 'Moves' is not correct. All values must be unique. " +
                                "Please enter one more time (example, rock scissors paper).");
                        System.exit(0);
                    }
                }
        }

        public void checkMoves(String[] arg) {
            checkAmount(length);
            checkUniqueMoves(arg);
            setMoves(arg);
        }

        public void printMoves() {
            System.out.println("Available moves:");
            for (int i = 0; i < length; i++)
                System.out.println(i + 1 + " - " + moves[i]);
            System.out.println("0 - exit");
        }
    }

}