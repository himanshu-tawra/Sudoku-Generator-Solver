package game;

public class SudokuPuzzle {


    public int size;

    public int emptyCells;

    public int myArray[][];



    public SudokuPuzzle(Board unSolvedPuzzle,int emptyCells) {

        this.size = unSolvedPuzzle.size;

        this.emptyCells = emptyCells;

        this.myArray = unSolvedPuzzle.getBoard();

        removeCells();

        unSolvedPuzzle.setBoard(myArray);

    }

    public int randomNumber(int num){
        return (int)Math.floor((Math.random()*num)+1);
    }

    public void removeCells(){

        int count = emptyCells;

        while(count != 0){

            int cellId = randomNumber(size*size-1);

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

}


