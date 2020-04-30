package game;

public class Board {
    public int [][] board;
    public int size;
    public int n;
    public int emptyCells;

    public Board(int size) {
        this.size = size;
        n = (int)Math.sqrt(size);
        this.board = new int[size][size];
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] b) {
        for(int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = b[i][j];
            }
        }
    }
    public void printBoard(){
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {

        Board solvedPuzzle = new Board(9);

        SudokuGenerator sg = new SudokuGenerator(solvedPuzzle);

        Board unSolvedPuzzle = new Board(9);

        unSolvedPuzzle.setBoard(solvedPuzzle.board);

        SudokuPuzzle sp = new SudokuPuzzle(unSolvedPuzzle,25);

        solvedPuzzle.printBoard();

        unSolvedPuzzle.printBoard();




    }


}
