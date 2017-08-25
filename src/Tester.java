import java.util.Scanner;

public class Tester {

    public static final int[][] test_case = {{0,0,0,0,0,0,7,8,9},
            {0,0,0,0,0,0,1,2,3},
            {0,0,0,0,2,3,4,5,6},
            {0,0,0,5,6,7,8,9,1},
            {0,0,0,8,9,1,2,3,4},
            {0,0,1,2,3,4,5,6,7},
            {3,4,5,6,7,0,0,0,0},
            {6,7,8,9,1,2,0,0,0},
            {9,1,2,0,0,0,0,0,0}};
    public static final int[][] test_new = {{3,0,0,1,2,0,5,0,4},
            {0,0,0,0,0,0,0,0,9},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},};

    public static void main(String[] args){
        // new Tester().test_random_solving();
        //new Tester().test_solving();
        //new Tester().test_isValidCell();
        new Tester().test_createSudoku();
    }

    /**
     * Written during the implementation of SudokuCreater.
     */
    private void test_createSudoku() {
        SudokuHelper.printSudoku(new SudokuCreater().createSudoku().getSquares());
    }

    /**
     * Written during implementation of SudokuSolver.solving().
     */
    private void test_solving(){
        Sudoku s = new Sudoku(test_case);
        SudokuSolver master = new SudokuSolver();
        master.solveSudokuConsole(s, true);
    }

    /**
     * Written during implementation of SudokuSolver.solving().
     */
    private void test_random_solving(){
        Sudoku s = new Sudoku();
        System.out.println("Solving:");
        SudokuHelper.printSudoku(s.getSquares());
        SudokuSolver master = new SudokuSolver();
        master.solveSudokuConsole(s, true);
    }

    /**
     * Written during implementation of SudokuSolver.isValidCell().
     */
    private void test_isValidCell(){
        Sudoku s = new Sudoku();
        SudokuSolver master = new SudokuSolver();
        Scanner scan = new Scanner(System.in);
        while(true){
            System.out.println("Enter a index: ");
            int input = scan.nextInt();
            System.out.println(master.isValidCell(input, s.getSquares()));
        }
    }
}
