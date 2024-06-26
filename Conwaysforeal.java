/**
 * Write a description of class Conwaysforeal here.
 * This is the second attempt at the Java project 
 * @author (Ari)
 * @version (v.1)
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Scanner;

public class Conwaysforeal {

    private class GridTile extends JButton {
        int rows;
        int cols;
        boolean isAlive;

        public GridTile(int rows, int cols) {
            this.rows = rows;
            this.cols = cols;
            this.isAlive = true;
        }

        public void setIsAlive() {
            if (randomCellGen.nextFloat() < chance) {
                this.isAlive = true;
            } else {
                this.isAlive = false;
            }
        }
    }

    // Instance variables
    int size = 20;
    JPanel cellsPanel = new JPanel();
    int[] makeCells;
    int width = 1000;
    int height = 1000;
    float chance = 0.5f; // Change number of spawn
    Random randomCellGen = new Random();
    JFrame window = new JFrame("ConwaysFoReal");

    GridTile[][] cells = new GridTile[size][size];

    public Conwaysforeal() {
        window.setSize(width, height);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        drawCells();
        window.add(cellsPanel);
        window.setVisible(true);

        // start the generations 
        Timer timer = new Timer(500,new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                nextGen();
            }
        });
        timer.start();
    }

    public void draw() {
        drawCells();
    }

    void setup() {
        makeCells = new int[size];

        for (int i = 0; i < cells.length; i++) {
            if (randomCellGen.nextFloat() < chance) {
                makeCells[i] = 1; // Alive cell = 1
            } else {
                makeCells[i] = 0; // Dead cell = 0
            }
        }
    }

    void drawCells() {
        cellsPanel.setLayout(new GridLayout(size, size));

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                GridTile cell = new GridTile(i, j); // Actually creating the new tile
                cells[i][j] = cell;

                // Change the actions of the JButtons
                cell.setFocusable(false);
                cell.setBorderPainted(true);
                cell.setContentAreaFilled(true);
                cell.setForeground(new Color(10, 10, 10));
                cell.setBackground(new Color(224, 255, 237));
                cell.setOpaque(true);
                cell.setIsAlive();



                cell.setFont(new Font ("Arial", Font.BOLD, 18));
                

                cellsPanel.add(cell);
            }
        }
    }

    public void nextGen() {
        int[][] nextGeneration = new int[size][size];

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
                    }
                }
            }
        }

        // Update the main grid
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j].isAlive = (nextGeneration[i][j] == 1);
            }
        }

        // Redraw the cells
        updateGrid();
    }

    void updateGrid() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                GridTile cell = cells[i][j];
                if (cell.isAlive) {
                    cell.setText("1");
                } else {
                    cell.setText("⬜");
                }
            }
        }
        cellsPanel.revalidate();
        cellsPanel.repaint();
    }
    
    public static int getUserInput () {
        Scanner scanner = new Scanner(System.in);
        System.out.print("How Many Generations would you like the simulation to run?");
        int userGen = scanner.nextInt();
        return userGen;
    }
    
    int checkNeighbors(int i, int j) { // To see if neighboring cells are alive or dead (1 or 0)
        int neighborsAlive = 0;
        // This checks all cells around the cell
        for (int r = -1; r <= 1; r++) {
            for (int c = -1; c <= 1; c++) {
                if (r == 0 && c == 0) continue;

                int row = r + i;
                int col = c + j;

                if (row >= 0 && row < size && col >= 0 && col < size) {
                    if (cells[row][col].isAlive) {
                        neighborsAlive++;
                    }
                }
            }
        }
        //System.out.print(neighborsAlive);
        return neighborsAlive;
    }
    
    public static void main(String[] args) {
        new Conwaysforeal();
    }
}