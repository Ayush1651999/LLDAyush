package Self.TicTacToe;

import java.util.Scanner;

public class TicTacToeDemo {
    public static void main(String[] args){
        Board board = new Board(3);

        Player p1 = new Player("p1", "X");
        Player p2 = new Player("p2", "0");
        Player currentPlayer = p1;

        boolean finished = false;

        while(!finished){
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your coordinates : ");

            try {
                String[] coordinates = scanner.nextLine().split(",");

                int row = Integer.parseInt(coordinates[0]);
                int col = Integer.parseInt(coordinates[1]);

                if (board.validInput(row, col)) {
                    board.set(row, col, currentPlayer.getSymbol());

                    finished = board.isGameFinished()[0];
                    if (finished) {
                        boolean draw = !board.isGameFinished()[1];
                        if (draw) {
                            System.out.println("Game drawn");
                        } else {
                            System.out.println("The winner is " + currentPlayer.getName());
                        }
                    }
                    currentPlayer = (currentPlayer == p1 ? p2 : p1);
                } else {
                    System.out.println("Please enter a valid input");
                }

                System.out.println(board.toString());

            } catch (Exception e){
                e.printStackTrace();
                System.out.println("Please enter a valid input");
            }
        }
    }
}
