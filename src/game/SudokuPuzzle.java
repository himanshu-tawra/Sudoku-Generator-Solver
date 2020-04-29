package game;

public class SudokuPuzzle {


    public int size;
    public int myArray[][];

    public SudokuPuzzle(int size) {
        SudokuGenerator sudoku = new SudokuGenerator(size);
        this.size = size;
        sudoku.fillValues();
        this.myArray = new int [size][size];
        for(int i = 0; i < sudoku.size; i++){
            for(int j = 0; j < sudoku.size; j++){
                myArray[i][j] = sudoku.board[i][j];
            }
        }
    }
    public static void main(String[] args) {
        SudokuPuzzle s = new SudokuPuzzle(9);
        for(int i = 0; i < s.size; i++){
            for(int j = 0; j < s.size; j++){
                System.out.print(s.myArray[i][j] + " ");

            }
            System.out.println();
        }
    }
}
