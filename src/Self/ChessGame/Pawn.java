package Self.ChessGame;

public class Pawn extends Piece {
    public Pawn(Colour colour) {
        super(colour);
    }

    @Override
    public boolean isValidMove(Move move) {
        return false;
    }
}
