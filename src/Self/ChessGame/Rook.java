package Self.ChessGame;

public class Rook extends Piece {
    public Rook(Colour colour) {
        super(colour);
    }

    @Override
    public boolean isValidMove(Move move) {
        Cell src = move.getSrc();
        Cell dest = move.getSrc();

        return (src.getRow()==dest.getRow() || src.getCol()==dest.getCol());
    }
}
