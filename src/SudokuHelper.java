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
