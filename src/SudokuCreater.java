import java.util.LinkedList;

/**
 * Used for creating new valid Sudokus.
 */
public class SudokuCreater {

    private int[][] sudoku = new int[9][9];

    private SudokuSolver solve = new SudokuSolver();

    private LinkedList<Integer> freeIndices = new LinkedList<>();

    public Sudoku createSudoku(){
        int listIndex, index;
        int currentDigit;
        int y,x, i;

        for(int n = 0; n < 81; n++)
            freeIndices.add(new Integer(n));

        for(i = 0; i < getDifficulty(); i++){
            currentDigit = SudokuHelper.randInt(1,9);
            listIndex = SudokuHelper.randInt(0,freeIndices.size()-1);
            index = freeIndices.remove(listIndex);
            y = index / 9;
            x = index % 9;
            while(!solve.isValidCell(y,x,currentDigit,sudoku)){
                currentDigit = (currentDigit % 9) + 1;
            }
            sudoku[y][x] = currentDigit;
        }
        System.out.printf("\nDifficulty: [%d]\n", i);
        return new Sudoku(sudoku);
    }

    private int getDifficulty(){
        return 17 + (int) (Math.random() * (81-17));
    }
}
