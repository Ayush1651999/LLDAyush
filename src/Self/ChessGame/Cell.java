package Self.ChessGame;

public class Cell {
    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    private final int row;
    private final int col;
    private Piece piece;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }
}
