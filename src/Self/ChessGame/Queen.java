package Self.ChessGame;

public class Queen extends Piece {
    public Queen(Colour colour) {
        super(colour);
    }

    @Override
    public boolean isValidMove(Move move) {
        return false;
    }

}
