import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class Tester extends JFrame{

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

    public static final String test_string_case = "500162000800000540300800000000000094030410020016000700009201000080074009003000075";

    public static final int TIMES = 1;

    protected JCheckBox createSudoku;
    protected JCheckBox solveOwnSudoku;
    protected JCheckBox outputToFile;
    protected JCheckBox solving;
    protected JCheckBox random_solving;
    protected JCheckBox isValidCell;
    protected JButton buttonOk;

    public static void main(String[] args){
        new Tester();
    }

    public Tester() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sudoku Tester");
        //setMinimumSize(new Dimension(200,150));
        setLayout(new GridLayout(3,3));

        createSudoku = new JCheckBox("Creation of a Sudoku");
        createSudoku.setToolTipText("Prints the Sudoku on the console.");
        add(createSudoku);

        solveOwnSudoku = new JCheckBox("Create and solve Sudoku");
        solveOwnSudoku.setToolTipText("Prints generated Sudoku and its solution on the console.");
        add(solveOwnSudoku);

        outputToFile = new JCheckBox("Create and store Sudoku in a file");
        outputToFile.setToolTipText("Outputs a new Sudoku to a staticly named file.");
        add(outputToFile);

        solving = new JCheckBox("Test the solving algorithm with a set Sudoku");
        solving.setToolTipText("Prints the solution on the console.");
        add(solving);

        random_solving = new JCheckBox("Test the solving algorithm with a random Sudoku");
        random_solving.setToolTipText("Prints the solution on the console.");
        add(random_solving);

        isValidCell = new JCheckBox("Test the isValidCell() function");
        isValidCell.setToolTipText("Asks the user for a index to check.");
        add(isValidCell);

        buttonOk = new JButton("Ok");
        buttonOk.addActionListener(e -> runTests());
        add(buttonOk);

        pack();
        setVisible(true);
    }

    private void runTests() {
            if(createSudoku.isSelected())
                this.test_createSudoku();
            if(this.solveOwnSudoku.isSelected())
                test_solveOwnSudoku();
            if(this.outputToFile.isSelected())
                test_outputToFile();
            if(this.solving.isSelected())
                test_solving();
            if(this.random_solving.isSelected())
                test_random_solving();
            if(this.isValidCell.isSelected())
                test_isValidCell();
            System.out.println("\n============TESTING FINISHED============");
    }

    /**
     * Written during the implementation of SudokuCreater.
     */
    private void test_createSudoku() {
        SudokuHelper.printSudoku(new SudokuCreater().createSudoku().getSquares());
    }

    /**
     * Written during the implementation of SudokuCreater.
     */
    private void test_solveOwnSudoku() {
        Sudoku generatedSudoku = new SudokuCreater().createSudoku();
        System.out.println("The Sudoku:");
        SudokuHelper.printSudoku(generatedSudoku.getSquares());
        System.out.println("The solution:");
        SudokuHelper.printSudoku(new SudokuSolver().solveSudoku(generatedSudoku).getSquares());
    }

    private void test_outputToFile(){
        Sudoku generatedSudoku = new SudokuCreater().createSudoku();
        generatedSudoku.saveSudokuToFile("my-Sudoku.mysu");
    }

    /**
     * Written during implementation of SudokuSolver.solving().
     */
    private void test_solving(){
        System.out.printf("Testing the algorithm %d times!\n", TIMES);
        long start = System.nanoTime();
        int t = 0;
        while(t < TIMES) {
            Sudoku s = new Sudoku(SudokuHelper.stringToArray(test_string_case));
            SudokuSolver master = new SudokuSolver();
            master.solveSudokuConsole(s, true);
            t++;
        }
        System.out.printf("Time: %d ms", (System.nanoTime() - start) / 1000000);
    }

    /**
     * Written during implementation of SudokuSolver.solving().
     */
    private void test_random_solving(){
        long start = System.nanoTime();
        Sudoku s = new Sudoku();
        System.out.println("Solving:");
        SudokuHelper.printSudoku(s.getSquares());
        SudokuSolver master = new SudokuSolver();
        master.solveSudokuConsole(s, true);
        System.out.printf("Time: %d ms", (System.nanoTime()-start)/1000000);
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
