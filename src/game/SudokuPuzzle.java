package game;

public class SudokuPuzzle {


    public int size;
    public int emptyCells;
    public int myArray[][];

    public SudokuPuzzle(int size,int emptyCells) {
        SudokuGenerator sudoku = new SudokuGenerator(size);
        this.size = size;
        this.emptyCells = emptyCells;
        sudoku.fillValues();
        this.myArray = new int [size][size];
        for(int i = 0; i < sudoku.size; i++){
            for(int j = 0; j < sudoku.size; j++){
                myArray[i][j] = sudoku.board[i][j];
            }
        }
    }
    public int randomNumber(int num){
        return (int)Math.floor((Math.random()*num)+1);
    }

    public void removeCells(){
        int count = emptyCells;

        while(count != 0){

            int cellId = randomNumber(size*size);

            int i = cellId/size;
            int j = cellId%size;
            if(j != 0){
                j = j - 1;
            }
            if(myArray[i][j] != 0){
                count--;
                myArray[i][j] = 0;
            }

        }
    }
    public static void main(String[] args) {
        SudokuPuzzle s = new SudokuPuzzle(9,25);
        s.removeCells();
        for(int i = 0; i < s.size; i++){
            for(int j = 0; j < s.size; j++){
                System.out.print(s.myArray[i][j] + " ");

            }
            System.out.println();
        }
    }
}
