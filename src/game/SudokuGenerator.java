package game;

public class SudokuGenerator{
    public int size;
    public int board[][];
    public int n;

    public SudokuGenerator(Board solvedPuzzle) {
        this.size = solvedPuzzle.size;
        this.board = solvedPuzzle.getBoard();
        n = (int)Math.sqrt(size);
        fillValues();
        solvedPuzzle.setBoard(this.board);

    }

    public void fillValues(){
        fillDiagonal();
        fillRemainingValues(0,n);
    }

    //1st
    //check for row and column
    boolean checkRowCol(int i,int j,int num){
        for(int k = 0; k < size; k++){
            if(board[i][k] == num || board[k][j] == num){
                return false;
            }
        }
        return true;
    }

    //2nd check for the small box
    boolean unUsedInBox(int rowStart, int colStart, int num)
    {

        for (int i = 0; i<n; i++)
            for (int j = 0; j<n; j++)
                if (board[rowStart+i][colStart+j]==num)
                    return false;

        return true;
    }

    //3rd check row col and small box
    boolean checkEverything(int i ,int j ,int num){
        return unUsedInBox(i-i%n,j-j%n,num) && checkRowCol(i,j,num);
    }

    //4th fill diagonal
    void fillDiagonal(){
        for(int i = 0; i < size; i+=n){
            fillBox(i,i);
        }
    }

    //5th fill box
    void fillBox(int row,int col){
        int num;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                do{
                    num = randomNumber(size);
                }
                while(!unUsedInBox(row,col,num));
                board[row+i][col+j] = num;
            }
        }
    }
    //6th randomNumber
    public int randomNumber(int num){
        return (int)Math.floor((Math.random()*num)+1);
    }

    // fill other values
    boolean fillRemainingValues(int i , int j){

        if(i >= size && j >= size){
            return true;
        }
        if(j >= size && i < size-1){
            j = 0;
            i = i+1;
        }
        if(i < n){
            if(j < n){
                j = n;
            }
        }
        if(i < n){
            if(j < n){
                j = n;
            }
        }
        else if(i < size - n){
            if(j == (int)((i/n)*n)){
                j = j + n;
            }
        }
        else {
            if(j == size - n){
                i = i + 1;
                j = 0;
                if(i >= size){
                    return true;
                }
            }
        }

        for(int num = 1; num <= size; num++){
            if(checkEverything(i,j,num)){
                board[i][j] = num;
                if(fillRemainingValues(i,j+1)){
                    return true;
                }
                board[i][j] = 0;
            }
        }
        return false;
    }
    //print function
    public void printSudoku()
    {
        for (int i = 0; i<size; i++)
        {
            for (int j = 0; j<size; j++)
                System.out.print(board[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }
}

