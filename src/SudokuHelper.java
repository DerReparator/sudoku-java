import java.util.Random;

/**
 * Contains useful static helper methods.
 */
public final class SudokuHelper {

    /**
     * Output a 2D-array to the console.
     */
    public static void printSudoku(int[][] cells){
        for(int y = 0; y < cells.length; y++) {
            for (int x = 0; x < cells[y].length; x++)
                System.out.print("|" + cells[y][x]);
            System.out.println("|");
        }
        System.out.println();
    }

    public static String arrayToString(int[][] cells){
        String output = "";
        for(int y = 0; y < cells.length; y++) {
            for (int x = 0; x < cells[y].length; x++) {
                output += cells[y][x];
            }
        }
        return output;
    }

    public static int[][] stringToArray(String input){
        char[] sudoku = input.toCharArray();
        int[][] output = new int[9][9];
        if (sudoku.length != 81){
            System.err.println("Problem with input format! Has to be 81 digits!");
        } else {
            for (int y = 0; y < 9; y++)
                for (int x = 0; x < 9; x++)
                    output[y][x] = Character.getNumericValue(sudoku[y*9+x]);
        }
        return output;
    }

    /**
     * Generate a random integer with both given boundaries inclusive.
     * @param min Minimum value.
     * @param max Maximum value.
     * @return Random integer between the two arguments.
     */
    public static int randInt(int min, int max){
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }
}
