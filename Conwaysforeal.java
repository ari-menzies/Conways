/**+
 * Write a description of class Conwaysforeal here.
 * This is the second attempt at the Java project 
 * @author (Ari)
 * @version (v.1)
 */
// my imports
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Scanner;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.InputMismatchException;

public class Conwaysforeal { // this is my main class that holds all the code 

    private class GridTile extends JButton { // GridTile is a private inner class that extends JButton, meaning each GridTile is a button on the grid.
        // the position of the tile the grid
        int rows;  
        int cols;
        boolean isAlive; // indicates weather the cell is alive or dead 

        public GridTile(int rows, int cols) {
            this.rows = rows;
            this.cols = cols;
            this.isAlive = true; // every cell starts alive 
            
            // this listens for a mouse click
            this.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent me) {
                    changeCell();
                }
            });
        }

        public void setIsAlive() { 
            if (randomCellGen.nextFloat() < chance) { // randomCellGen.nextFloat() generates a random float between 0.0 and 1.0
                this.isAlive = true;
            } else {
                this.isAlive = false;
            }
        }
        
        public void changeCell() {
            if (this.isAlive = !this.isAlive) { // the ! means that if it is true it will return false and reverse the outcome 
                    this.setText("⬛"); // if it isn't alive set to this
                } else {
                    this.setText("⬜"); // if it is alive set to dead
                }
            }
    }
        
    
    // Instance variables 
    int size = 20;
    JPanel cellsPanel = new JPanel();
    int[] makeCells;
    int width = 1000;
    int height = 1000;
    float chance = 0.5f; 
    int userInput;
    int currentGen;
    Random randomCellGen = new Random();
    JFrame window = new JFrame("ConwaysFoReal");
    JButton pause = new JButton("Pause Button");
    // creates grid
    GridTile[][] cells = new GridTile[size][size];
    
    public Conwaysforeal() {
        // Get user input for the number of generations
        this.userInput = getUserInput();
        this.currentGen = 0;
        //Jframe preferences
        window.setSize(width, height);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        drawCells();
        window.add(cellsPanel);
        window.setVisible(true);
        
        // Jbutton prefrences 
        pause.setBounds(10,10, 100, 30);
        pause.addMouseListener(new MouseAdapter() {
                public void pauseClicked(MouseEvent me) {
                    pauseButton();
                }
            });
        window.add(pause, BorderLayout.NORTH);
        
        
        //generation timer
        Timer timer = new Timer(1000, new ActionListener() {
            int currentGen = 0;

            public void actionPerformed(ActionEvent e) {
                if (currentGen < userInput) {
                    nextGen();
                    currentGen++;
                    System.out.print("\nGeneration " + currentGen);
                } else {
                    ((Timer) e.getSource()).stop(); // stops the simulation
                }
            }
        });
        timer.start();
        
        
    }
    
    public void pauseButton() {
        
    }
        
    public static int getUserInput () {
        Scanner scanner = new Scanner(System.in); // create a scanner 
        int userInput = -1;
        
        while (userInput <= 0) { // continues to ask until valid input 
            System.out.print("How Many Generations would you like the \n simulation to run?"); // ask the user
            try {
                userInput = scanner.nextInt();
                if (userInput <= 0) { // checks for negative number
                    System.out.print(" Please enter a positive number\n");
                }
            }catch (InputMismatchException e){ // catches the error of an invalid input (not an int)
                System.out.print(" Please enter a number\n");
                scanner.next(); // Clear the invalid input
            }
    }
         // take the interger 
        System.out.print("Running " + userInput + " generations"); // display int to user
        return userInput;
        
    }
    
    void draw() {
        //draws cells on grid
        drawCells();
    }

    void setup() {
        makeCells = new int[size]; // Create an array 'makeCells' with 'size' number of elements

        for (int i = 0; i < cells.length; i++) { // Loop through each cell in the cells array.
            if (randomCellGen.nextFloat() < chance) {
                makeCells[i] = 1; // Alive cell = 1
            } else {
                makeCells[i] = 0; // Dead cell = 0
            }
        }
    }

    void drawCells() {
        cellsPanel.setLayout(new GridLayout(size, size)); //Creates Jframe grid

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) { // loops through the rows and colums of the grid
                GridTile cell = new GridTile(i, j); // Actually creating the new tile
                cells[i][j] = cell;

                // Change the actions of the JButton cells
                cell.setFocusable(false);
                cell.setBorderPainted(true);
                cell.setContentAreaFilled(true);
                cell.setForeground(new Color(10, 10, 10));
                cell.setBackground(new Color(254, 255, 237));
                cell.setOpaque(true);
                cell.setIsAlive();
                cellsPanel.add(cell); // the configured GridTile object is added to the cellsPanel
            }
        }
    }

    void nextGen() {
        int[][] nextGeneration = new int[size][size]; //new array for next gen

        // Loop through every cell in the current generation
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int neighbors = checkNeighbors(i, j); // Count neighbors

                // Applying the rules of Conway's
                if (cells[i][j].isAlive) {
                    if (neighbors < 2 || neighbors > 3) {
                        nextGeneration[i][j] = 0; // The cell dies due to under or overpopulation
                    } else {
                        nextGeneration[i][j] = 1; // The cell does not die because the population is good
                    }
                } else {
                    if (neighbors == 3) {
                        nextGeneration[i][j] = 1; // Cell becomes alive due to the correct population
                    } else {
                        nextGeneration[i][j] = 0; // Cell remains dead
                        //System.out.print("Test");
                    }
                }
            }
        }

        // Update the main grid
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j].isAlive = (nextGeneration[i][j] == 1); // Updates the state of the cell in the main grid to reflect the state of the next generation.
            }
        }

        // Redraw the cells
        updateGrid();
    }

    void updateGrid() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) { // loops through the cells 
                GridTile cell = cells[i][j];
                if (cell.isAlive) {
                    cell.setText("⬛"); //alive
                } else {
                    cell.setText("⬜"); //dead
                }
            }
        }
        //reset the Jpanels
        cellsPanel.revalidate();
        cellsPanel.repaint();
    }
   
   
    
    int checkNeighbors(int i, int j) { // To see if neighboring cells are alive or dead (1 or 0)
        int neighborsAlive = 0;
        // This checks all cells around the cell
        for (int r = -1; r <= 1; r++) {
            for (int c = -1; c <= 1; c++) {
                if (r == 0 && c == 0) continue; // skips the middle cell

                int row = r + i;
                int col = c + j;

                if (row >= 0 && row < size && col >= 0 && col < size) { // ensures that the neighboring cell being checked is within the valid range of the grid.
                    if (cells[row][col].isAlive) {
                        neighborsAlive++; // is the cell is alive add yto neighborsAlive
                    }
                }
            }
        }
        //System.out.print(neighborsAlive);
        return neighborsAlive;
    }
}