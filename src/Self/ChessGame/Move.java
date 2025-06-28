package Self.ChessGame;

public class Move {
    private final Cell src;
    private final Cell dest;
    public Move(Cell src, Cell dest) {
        this.src = src;
        this.dest = dest;
    }

    public Cell getSrc() {
        return src;
    }

    public Cell getDest(){
        return dest;
    }
}
