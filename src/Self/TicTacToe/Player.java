package Self.TicTacToe;

public class Player {
    String name;
    String symbol;

    public Player(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }
}
