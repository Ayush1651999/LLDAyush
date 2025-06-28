package Self.ChessGame;

import java.util.ArrayList;
import java.util.List;

public class Board {
    Cell[][] cells;

    public Board(){
        cells = new Cell[8][8];
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                cells[i][j] = new Cell(i, j);
            }
        }

        setupPieces();
    }

    private void setupPieces() {
        for(int i=0; i<8; i++){
            cells[1][i].setPiece(new Pawn(Colour.WHITE));
            cells[6][i].setPiece(new Pawn(Colour.BLACK));
        }

        cells[0][0].setPiece(new Rook(Colour.WHITE));
        cells[0][1].setPiece(new Knight(Colour.WHITE));
        cells[0][2].setPiece(new Bishop(Colour.WHITE));
        cells[0][3].setPiece(new Queen(Colour.WHITE));
        cells[0][4].setPiece(new King(Colour.WHITE));
        cells[0][5].setPiece(new Bishop(Colour.WHITE));
        cells[0][6].setPiece(new Knight(Colour.WHITE));
        cells[0][7].setPiece(new Rook(Colour.WHITE));

        // Initialize black pieces
        cells[7][0].setPiece(new Rook(Colour.BLACK));
        cells[7][1].setPiece(new Knight(Colour.BLACK));
        cells[7][2].setPiece(new Bishop(Colour.BLACK));
        cells[7][3].setPiece(new Queen(Colour.BLACK));
        cells[7][4].setPiece(new King(Colour.BLACK));
        cells[7][5].setPiece(new Bishop(Colour.BLACK));
        cells[7][6].setPiece(new Knight(Colour.BLACK));
        cells[7][7].setPiece(new Rook(Colour.BLACK));
    }

    public boolean isCheckmate(Colour color) {
        // TODO
        return false;
    }

    public boolean isStalemate(Colour color) {
        // TODO
        return false;
    }

    public Cell getCell(Integer row, Integer col) {
        return cells[row][col];
    }

    public void makeMove(Move move) {
        Cell src = move.getSrc();
        Cell dest = move.getDest();
        Piece piece = src.getPiece();

        src.setPiece(null);
        dest.setPiece(piece);
    }
}
