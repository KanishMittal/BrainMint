import java.util.Scanner;

public class TangoGame {
    private static final int SIZE = 6;
    private char[][] grid;
    private char[][] solution;

    public TangoGame() {
        grid = new char[SIZE][SIZE];
        solution = new char[SIZE][SIZE];
        generatePuzzle();
    }

    public static void main(String[] args) {
        TangoGame game = new TangoGame();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Tango Game!");
        System.out.println("You need to fill the grid with 'a' and 'b' according to the rules");
        System.out.println("Rules:-");
        System.out.println("Fill the grid so that each cell contains either 'a' or 'b'  \n" + //
                        "No more than 2 'a' or 'b' may be next to each other, either vertically or horizontally \n" + //
                        "'a' 'a' -- 'Allowed' \n" + //
                        "'a' 'a' 'a' -- 'Not Allowed' \n" + //
                        "Each row (and column) must contain the same number of 'a' and  'b' \n ");
        game.printGrid();
        
        boolean isSolved = false;
        while (!isSolved) {
            System.out.println("Enter the row (0-5), column (0-5), and the value ('a' or 'b') to fill");
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            char value = scanner.next().charAt(0);
            
            if (game.isValidMove(row, col, value)) {
                game.grid[row][col] = value;
                game.printGrid();
            } else {
                System.out.println("Invalid move! Try again");
            }

            isSolved = game.isPuzzleSolved();
        }
        
        System.out.println("Congratulations! You've solved the puzzle");
        scanner.close();
    }

    private void generatePuzzle() {
        String[] solutionGrid = {
            "ababaX",
            "babaXb",
            "ababaX",
            "bXbaXb",
            "ababaX",
            "babaXb"
        };

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                solution[i][j] = solutionGrid[i].charAt(j);
                grid[i][j] = '.';
            }
        }
    }

    private void printGrid() {
        System.out.println("Current grid:");
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    private boolean isValidMove(int row, int col, char value) {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
            return false;
        }

        if (grid[row][col] != '.') {
            return false;
        }

        int rowA = 0, rowB = 0, colA = 0, colB = 0;
        for (int i = 0; i < SIZE; i++) {
            if (grid[row][i] == 'a') rowA++;
            if (grid[row][i] == 'b') rowB++;
            if (grid[i][col] == 'a') colA++;
            if (grid[i][col] == 'b') colB++;
        }
        if ((rowA + (value == 'a' ? 1 : 0) > SIZE / 2) || 
            (rowB + (value == 'b' ? 1 : 0) > SIZE / 2) || 
            (colA + (value == 'a' ? 1 : 0) > SIZE / 2) || 
            (colB + (value == 'b' ? 1 : 0) > SIZE / 2)) {
            return false;
        }

        if (checkConsecutive(row, col, value)) {
            return false;
        }

        return true;
    }

    private boolean checkConsecutive(int row, int col, char value) {
        if (col > 1 && grid[row][col-1] == value && grid[row][col-2] == value) return true;
        if (col < SIZE - 2 && grid[row][col+1] == value && grid[row][col+2] == value) return true;
        
        if (row > 1 && grid[row-1][col] == value && grid[row-2][col] == value) return true;
        if (row < SIZE - 2 && grid[row+1][col] == value && grid[row+2][col] == value) return true;
        
        return false;
    }

    private boolean isPuzzleSolved() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] != solution[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}