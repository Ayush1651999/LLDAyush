package Self.ChessGame;

public class Bishop extends Piece {
    public Bishop(Colour colour) {
        super(colour);
    }

    @Override
    public boolean isValidMove(Move move) {
        return false;
    }
}
