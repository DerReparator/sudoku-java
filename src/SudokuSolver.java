/**
 * The base of my project. It is designed to solve given Sudokus and output those in different ways (e.g. to the console or a file).
 */
public class SudokuSolver {

    private int[][] cells = new int[9][9];
    /**
     * @param sudoku The Sudoku to solve
     * @param formatted false: outputs it to the file unformatted but readable for my programs. \n true: outputs it to the file formatted for better readability.
     *               Outputs the solution to a .txt file.
     */
    public void solveSudokuToFile(Sudoku sudoku, boolean formatted){

    }

    /**
     * @param sudoku The Sudoku to solve
     * @param formatted false: outputs it to the console unformatted but readable for my programs. \n true: outputs it to the console formatted for better readability.
     * Outputs the solution to the stdout.
     */
    public void solveSudokuConsole(Sudoku sudoku, boolean formatted){
        if(!formatted){
            // TODO
            System.out.println(solve(sudoku).toString());
        } else {
            Sudoku.printSudoku(solve(sudoku));
        }
    }

    /**
     * @param sudoku The Sudoku to solve
     * Creates a new Sudoku object which is the solved version of the input-Sudoku.
     */
    public Sudoku solveSudoku(Sudoku sudoku){
        return new Sudoku(solve(sudoku));
    }

    private int[][] solve(Sudoku s){
        cells = s.getSquares();
        solving(0, cells);
        return cells;
    }

    /**
     * Solve the Sudoku by backtracking and in-place.
     * @param index The current index in which the new Digits are tried to be filled in.
     * @param matrix In this version always the int[][] of this class.
     * @return true if cell could be filled or if out of bounds. false if there was no fitting value found.
     */
    private boolean solving(int index, int [][] matrix){//} int yC, int xC){
        int yC = index / 9;
        int xC = index % 9;

        if(yC >= 9){
            return true;
        }
        if(matrix[yC][xC] != 0)
            return solving(index+1, cells);

        for (int currentDigit = 1; currentDigit <= 9; currentDigit++){
            if(isValidCell(yC, xC,currentDigit, cells)){
                cells[yC][xC] = currentDigit;
                if(solving(index+1, cells))
                    return true;
            }
        }
        cells[yC][xC] = 0;
        return false;
    }

    /**
     * Mainly for debugging purposes. Enables to call the isValidCell() method from outside the class and
     * with only one index parameter.
     * @param index The linear index of the cell to be checked.
     * @param cells The grid into which the val should get inserted.
     * @return Whether this number is valid at this cell.
     */
    public boolean isValidCell(int index, int[][] cells){
        return this.isValidCell((index / 9), (index % 9), cells[index / 9][index % 9], cells);
    }

    /**
     * Tests if the new value is allowed at this position in the sudoku
     * @param xC x-Coordinate of the insertion.
     * @param yC y-Coordinate of the insertion.
     * @param cells The grid into which val should get inserted.
     * @return Whether this number is valid at this cell.
     */
    private boolean isValidCell(int yC, int xC, int currentDigit, int[][] cells){
        /* Go through the row. */
        for(int x = 0; x < 9; x++)
            if(currentDigit == cells[yC][x])
                return false;

        /* Go through the column. */
        for(int y = 0; y < 9; y++)
            if(currentDigit == cells[y][xC])
                return false;

        /* Calc block offset. */
        int block_xC = (xC/3)*3;
        int block_yC = (yC/3)*3;

        /* Go through the block. */
        for(int y = 0; y < 3; y++)
            for(int x = 0; x < 3; x++)
                if(currentDigit == cells[block_yC+y][block_xC+x])
                    return false;
        return true;
    }
}
