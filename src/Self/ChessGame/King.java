package Self.ChessGame;

public class King extends Piece {
    public King(Colour colour) {
        super(colour);
    }

    @Override
    public boolean isValidMove(Move move) {
        return false;
    }
}
