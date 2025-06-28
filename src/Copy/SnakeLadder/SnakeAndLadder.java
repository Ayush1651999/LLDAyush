package Copy.SnakeLadder;// Snake and Ladder Game - LLD in Java

import java.util.*;

class Player {
    private String name;
    private int position;

    public Player(String name) {
        this.name = name;
        this.position = 0;
    }

    public String getName() { return name; }
    public int getPosition() { return position; }
    public void setPosition(int position) { this.position = position; }
}

class Snake {
    private int head;
    private int tail;

    public Snake(int head, int tail) {
        this.head = head;
        this.tail = tail;
    }

    public int getHead() { return head; }
    public int getTail() { return tail; }
}

class Ladder {
    private int start;
    private int end;

    public Ladder(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() { return start; }
    public int getEnd() { return end; }
}

class Board {
    private int size;
    private List<Snake> snakes;
    private List<Ladder> ladders;

    public Board(int size, List<Snake> snakes, List<Ladder> ladders) {
        this.size = size;
        this.snakes = snakes;
        this.ladders = ladders;
    }

    public int getSize() { return size; }
    public List<Snake> getSnakes() { return snakes; }
    public List<Ladder> getLadders() { return ladders; }

    public int getNewPosition(int currentPosition) {
        for (Snake snake : snakes) {
            if (snake.getHead() == currentPosition) {
                System.out.println("Bitten by snake!");
                return snake.getTail();
            }
        }
        for (Ladder ladder : ladders) {
            if (ladder.getStart() == currentPosition) {
                System.out.println("Climbed a ladder!");
                return ladder.getEnd();
            }
        }
        return currentPosition;
    }
}

class Dice {
    private Random rand = new Random();
    public int roll() {
        return rand.nextInt(6) + 1;
    }
}

class Game {
    private Board board;
    private Queue<Player> players;
    private Dice dice;

    public Game(Board board, List<Player> players) {
        this.board = board;
        this.players = new LinkedList<>(players);
        this.dice = new Dice();
    }

    public void start() {
        while (true) {
            Player currentPlayer = players.poll();
            int diceRoll = dice.roll();
            System.out.println(currentPlayer.getName() + " rolled a " + diceRoll);

            int newPos = currentPlayer.getPosition() + diceRoll;
            if (newPos > board.getSize()) {
                players.offer(currentPlayer);
                continue;
            }

            newPos = board.getNewPosition(newPos);
            currentPlayer.setPosition(newPos);

            System.out.println(currentPlayer.getName() + " moved to position " + newPos);

            if (newPos == board.getSize()) {
                System.out.println(currentPlayer.getName() + " wins!");
                break;
            }

            players.offer(currentPlayer);
        }
    }
}

public class SnakeAndLadder {
    public static void main(String[] args) {
        List<Snake> snakes = Arrays.asList(
                new Snake(14, 7),
                new Snake(31, 26),
                new Snake(38, 20),
                new Snake(84, 28),
                new Snake(97, 78)
        );

        List<Ladder> ladders = Arrays.asList(
                new Ladder(3, 22),
                new Ladder(5, 8),
                new Ladder(11, 26),
                new Ladder(20, 29),
                new Ladder(27, 84)
        );

        List<Player> players = Arrays.asList(
                new Player("Alice"),
                new Player("Bob")
        );

        Board board = new Board(100, snakes, ladders);
        Game game = new Game(board, players);
        game.start();
    }
}
