import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * The reprensentation of a Sudoku.
 */
public class Sudoku {

    private final int[][] squares;
    String ls = System.getProperty("line.separator");

    /**
     * In this early version a random Sudoku from 'easy50.txt' is loaded and stored in this Sudoku object.
     */
    public Sudoku() {
        squares = new int[9][9];
        fillFromFile("easy50.txt");
    }

    /**
     * @param values A 2-D int-array that gets attached to the new Sudoku-object.
     * Constructor primarily used for storing the solution of a Sudoku in a new Sudoku-object.
     */
    public Sudoku(int[][] values) {
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
     * Save this Sudoku to a file. (Currently you're only able to store one Sudoku per file.)
     * @param pathname The name of the file.
     * @return True if successful.
     */
    public boolean saveSudokuToFile(String pathname){
        return false;
    }

    /**
     * Gets a random sudoku from easy50.txt and parses it into a 2D-byte-array.
     * @param file Specifies the file in which Sudokus are stored.
     */
    private void fillFromFile(String file){
        String fileString = null;
        // TODO get the number of lines from the parsed file.
        int lines = 50;
        try {
            fileString = readFile(file);
        } catch (IOException e) {
            System.err.println("Oops! Something went wrong while reading from file!");
        }

        int lineNr = (int) (Math.random() * lines);
        int i = 0;
        while (i < lineNr) {
            fileString = fileString.substring(fileString.indexOf(ls) + 1);
            i++;
        }

        char[] sudoku = (fileString.substring(1, fileString.indexOf(ls))).toCharArray();
        if (sudoku.length != 81){
            System.err.println("Problem with input format! Has to be 81 digits!");
            System.out.println("length: " + sudoku.length + " = " + fileString.substring(0, fileString.indexOf(ls)));
        } else {
            for (int y = 0; y < 9; y++)
                for (int x = 0; x < 9; x++)
                    squares[y][x] = Character.getNumericValue(sudoku[y*9+x]);
        }
    }

    /**
     * Returns a String representation of all Sudokus contained in the specified file.
     * @param file The filepath.
     * @return Sudokus in String representation seperated by system-specific newline char.
     * @throws IOException
     */
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