package Self.ChessGame;

public abstract class Piece {
    Colour colour;

    public Piece(Colour colour){
        this.colour = colour;
    }

    public abstract boolean isValidMove(Move move);

    public Colour getColour() {
        return colour;
    }
}
