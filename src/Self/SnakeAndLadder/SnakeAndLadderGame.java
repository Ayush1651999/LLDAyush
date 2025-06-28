package Self.SnakeAndLadder;

import Copy.SnakeLadder.SnakeAndLadder;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SnakeAndLadderGame {
    private final int size;
    private final List<Snake> snakes;
    private final List<Ladder> ladders;
    private final List<Player> players;
//    private final Player currentPlayer;

    public SnakeAndLadderGame(int size){
        this.size = size;
        this.snakes = Arrays.asList(new Snake(10, 2), new Snake(20, 19));
        this.ladders = Arrays.asList(new Ladder(3, 5), new Ladder(18, 21));
        this.players = Arrays.asList(new Player("A"), new Player("B"));

//        this.currentPlayer = players.get(0);
    }

    public void start() {
        boolean gameOver = false;
        int currentPlayerIdx = 0;

        while(!gameOver){
            Random random = new Random();
            int diceNumber = random.nextInt(6)+1;

            Player currentPlayer = players.get(currentPlayerIdx);
            currentPlayer.incrementPosition(diceNumber);

            Ladder ladder = getLadderFromThisPosition(currentPlayer);
            Snake snake = getSnakeFromThisPosition(currentPlayer);

            if(ladder != null){
                currentPlayer.setPosition(ladder.getEnd());
            } else if(snake != null) {
                currentPlayer.setPosition(snake.getEnd());
            }

            System.out.println("Player " + currentPlayer.getName() + "'s new position is " + currentPlayer.getPosition());

            if(currentPlayer.getPosition() == this.size){
                gameOver = true;
                System.out.println("Winner is " + currentPlayer.getName());
            } else {
                currentPlayerIdx++;
                if(currentPlayerIdx == players.size()) currentPlayerIdx = 0;
            }
        }
    }

    private Snake getSnakeFromThisPosition(Player currentPlayer) {
        for(Snake snake : snakes){
            if(snake.getStart() == currentPlayer.getPosition()){
                return snake;
            }
        }
        return null;
    }

    private Ladder getLadderFromThisPosition(Player currentPlayer) {
        for(Ladder ladder : ladders){
            if(ladder.getStart() == currentPlayer.getPosition()){
                return ladder;
            }
        }
        return null;
    }
}
