import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * The reprensentation of a Sudoku.
 */
public class Sudoku {

    private final int[][] squares;
    /** This should be deleted in a final version. It stores the String-Form of a scanned Sudoku. **/
    String debugSudoku = null;
    String ls = System.getProperty("line.separator");

    /**
     * In this early version a random Sudoku from 'easy50.txt' is loaded and stored in this Sudoku object.
     */
    public Sudoku() {
        squares = new int[9][9];
        fillSudoku();
    }

    /**
     * @param values A 2-D int-array that gets attached to the new Sudoku-object.
     * Constructor primarily used for storing the solution of a Sudoku in a new Sudoku-object.
     */
    public Sudoku(int[][] values) {
        System.out.println(values.length);
        if(values.length == 9){
            this.squares = values;
        } else {
            squares = new int[9][9];
            System.err.println("The passed array must be 9*9 large!");
        }
    }

    public int[][] getSquares(){
        return squares;
    }

    /**
     * Static helper method to output a 2D-array to the console.
     */
    public static void printSudoku(int[][] cells){
        for(int y = 0; y < cells.length; y++) {
            for (int x = 0; x < cells[y].length; x++)
                System.out.print("|" + cells[y][x]);
            System.out.println("|");
        }
        System.out.println();
    }

    /**
     * Gets a random sudoku from easy50.txt and parses it into a 2D-byte-array.
     */
    private void fillSudoku(){
        String fileString = null;
        try {
            fileString = readFile("easy50.txt");
        } catch (IOException e) {
            System.err.println("Oops! Something went wrong while reading from file!");
        }

        int lineNr = (int) (Math.random() * 50);
        int i = 0;
        while (i < lineNr) {
            fileString = fileString.substring(fileString.indexOf(ls) + 1);
            i++;
        }

        debugSudoku = (fileString.substring(1, fileString.indexOf(ls)));
        char[] sudokuString = debugSudoku.toCharArray();
        if (sudokuString.length != 81){
            System.err.println("Problem with input format! Has to be 81 digits!");
            System.out.println("length: " + sudokuString.length + " = " + fileString.substring(0, fileString.indexOf(ls)));
        } else {
            for (int y = 0; y < 9; y++)
                for (int x = 0; x < 9; x++)
                    squares[y][x] = Character.getNumericValue(sudokuString[y*9+x]);
        }
    }

    private String readFile(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();

        try {
            while((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }
            return stringBuilder.toString();
        } finally {
            try {
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}