package Self.TicTacToe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
    private final int size;
    List<List<String>> boardArray;
    public Board(int n) {
        this.size = n;
        boardArray = new ArrayList<>();
        for(int i=0; i<n; i++){
            boardArray.add(Arrays.asList("_", "_", "_"));
        }

        System.out.println(this.toString());
    }

    @Override
    public String toString(){
        StringBuilder boardToString = new StringBuilder();
        for(List<String> row : boardArray){
            boardToString.append(row.toString()).append("\n");
        }
        return boardToString.toString();
    }

    public void set(int row, int col, String symbol) {
        boardArray.get(row).set(col, symbol);
    }

    public boolean validInput(int row, int col) {
        if(row<0 || row>=size || col<0 || col>=size) return false;
        return "_".equals(boardArray.get(row).get(col));
    }

//    public boolean isGameFinished() {
//        for(int i=0; i<size; i++){
//            if("_".equalsIgnoreCase(boardArray.get(i).get(0))) return false;
//            if("_".equalsIgnoreCase(boardArray.get(0).get(i))) return false;
//
//            for(int j=1; j<size; j++){
//                if(!boardArray.get(i).get(j).equalsIgnoreCase(boardArray.get(i).get(0))) return false;
//            }
//
//            for(int j=1; j<size; j++){
//                if(!boardArray.get(j).get(i).equalsIgnoreCase(boardArray.get(0).get(i))) return false;
//            }
//        }
//
//        for(int i=1; i<size; i++){
//            if(!boardArray.get(0).get(0).equalsIgnoreCase(boardArray.get(i).get(i))) return false;
//            if(!boardArray.get(0).get(size-1).equalsIgnoreCase(boardArray.get(i).get(size-1-i))) return false;
//        }
//
//        return true;
//    }

    public boolean[] isGameFinished() {
        // Check rows and columns for a win
        for (int i = 0; i < size; i++) {
            // Check row
            if (!"_".equals(boardArray.get(i).get(0))) {
                boolean rowWin = true;
                for (int j = 1; j < size; j++) {
                    if (!boardArray.get(i).get(j).equals(boardArray.get(i).get(0))) {
                        rowWin = false;
                        break;
                    }
                }
                if (rowWin) return new boolean[]{true, true};
            }

            // Check column
            if (!"_".equals(boardArray.get(0).get(i))) {
                boolean colWin = true;
                for (int j = 1; j < size; j++) {
                    if (!boardArray.get(j).get(i).equals(boardArray.get(0).get(i))) {
                        colWin = false;
                        break;
                    }
                }
                if (colWin) return new boolean[]{true, true};
            }
        }

        // Check diagonals for a win
        if (!"_".equals(boardArray.get(0).get(0))) {
            boolean diagonalWin1 = true;
            for (int i = 1; i < size; i++) {
                if (!boardArray.get(i).get(i).equals(boardArray.get(0).get(0))) {
                    diagonalWin1 = false;
                    break;
                }
            }
            if (diagonalWin1) return new boolean[]{true, true};
        }

        if (!"_".equals(boardArray.get(0).get(size - 1))) {
            boolean diagonalWin2 = true;
            for (int i = 1; i < size; i++) {
                if (!boardArray.get(i).get(size - 1 - i).equals(boardArray.get(0).get(size - 1))) {
                    diagonalWin2 = false;
                    break;
                }
            }
            if (diagonalWin2) return new boolean[]{true, true};
        }

        // Check for draw condition (all cells filled)
        for (List<String> row : boardArray) {
            for (String cell : row) {
                if ("_".equals(cell)) {
                    return new boolean[]{false, false}; // Game is not finished
                }
            }
        }

        // If no win and all cells are filled, it's a draw
        return new boolean[]{true, false};
    }
}
