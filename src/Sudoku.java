import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.*;

/**
 * The reprensentation of a Sudoku.
 */
public class Sudoku {

    private final int[][] squares;
    /**
     * Used for determining an input-file.
     */
    private String selected = "";
    public boolean weitermachen = false;
    String ls = System.getProperty("line.separator");

    /**
     * In this early version a FileSelectionDialog is displayed to select a file from which sudokus are randomly chosen.
     */
    public Sudoku() {
        squares = new int[9][9];
        fillFromFile();
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
    public boolean saveSudokuToFile(String pathname) {

        try {
            Writer w = new FileWriter(pathname);
            w.write(SudokuHelper.arrayToString(squares));
            w.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    /**
     * Gets a random sudoku from easy50.txt and parses it into a 2D-byte-array.
     */
    private void fillFromFile(){
        String fileString = null;
        // TODO get the number of lines from the parsed file.
        int lines = 50;

        String fileLoc = "easy50.txt";

        try {
            fileString = readFile(fileLoc);//readFile(showFileSelection());
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
     * Shows a window with the possible files.
     * @return The pathname of the chosen file.
     * @throws IOException
     */
    private String showFileSelection() throws IOException {
        JDialog selcFrame = new JDialog();
        selcFrame.setTitle("Select a file");
        JPanel panel = new JPanel();
        JComboBox<File> selection = new JComboBox<>();


        File dir = new File(".");
        FilenameFilter filter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".txt");
            }
        };
        File[] files = dir.listFiles(filter);

        for(File f : files){
            if(f.isFile())
                selection.addItem(f);
        }



        selcFrame.setLayout(new BorderLayout());
        selcFrame.add(panel, BorderLayout.CENTER);
        panel.add(selection);
        //selcFrame.pack();
        selcFrame.setMinimumSize(new Dimension(300,100));
        selcFrame.setLocationRelativeTo(null);
        selcFrame.setVisible(true);

        selection.addActionListener(e -> {
            this.selected = ((File) selection.getSelectedItem()).getAbsolutePath();
            weitermachen = true;
            selcFrame.dispose();
        });
        while(!weitermachen);

        return selected;
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