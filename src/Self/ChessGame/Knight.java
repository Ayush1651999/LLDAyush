package Self.ChessGame;

public class Knight extends Piece {
    public Knight(Colour colour) {
        super(colour);
    }

    @Override
    public boolean isValidMove(Move move) {
        return false;
    }
}
