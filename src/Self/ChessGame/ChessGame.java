package Self.ChessGame;

import java.util.Scanner;

public class ChessGame {
    Board board;
    Player black;
    Player white;
    Player currentPlayer;

    public ChessGame(){
        board = new Board();
        black = new Player(Colour.BLACK);
        white = new Player(Colour.WHITE);
        currentPlayer = white;
    }

    public void start(){
        while(!isGameOver()){
            System.out.println("Turn of player -> " + currentPlayer.getColor());

            Move move = getNextMove();

            if(isValidMove(move)){
                board.makeMove(move);
                movePlayer();
            } else {
                System.out.println("Move not allowed");
            }
        }
    }

    private void movePlayer() {
        currentPlayer = (currentPlayer == black ? white : black);
    }

    private boolean isValidMove(Move move) {
        Cell start = move.getSrc();
        Piece piece = start.getPiece();

        if(piece == null) return false;
        if(piece.getColour() != currentPlayer.getColor()) return false;

        return piece.isValidMove(move);
    }

    private Move getNextMove() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter 4 coordinates ->");
        String[] coordinates = scanner.nextLine().split(" ");

        Cell src = board.getCell(Integer.valueOf(coordinates[0]), Integer.valueOf(coordinates[1]));
        Cell dest = board.getCell(Integer.valueOf(coordinates[2]), Integer.valueOf(coordinates[3]));

        return new Move(src, dest);
    }

    private boolean isGameOver() {
        return board.isCheckmate(white.getColor()) || board.isCheckmate(black.getColor()) ||
                board.isStalemate(white.getColor()) || board.isStalemate(black.getColor());
    }
}
