
/**
 * Write a description of class Conwaysforeal here.
 * this is the second attempt at the java project 
 * @author (Ari)
 * @version (v.1)
 */

import javax.swing.*;
import java.util.*;
import java.awt.Color;
import java.awt.GridLayout;

public class Conwaysforeal
{
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
    // instance variables - replace the example below with your own
    int size = 20;
    JPanel cellsPanel = new JPanel();
    int makeCells[];
    int width = 1000;
    int height = 1000;
    float chance = 0.5f; // change number of spawn
    Random randomCellGen = new Random();
    JFrame window = new JFrame("conwaysFoReal");
    
    GridTile[][] cells = new GridTile[size][size];
    public Conwaysforeal(){
        window.setSize(width, height);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        drawCells();
        window.add(cellsPanel);
        window.setVisible(true);
    }
    
    void draw() {
        drawCells();
        cellRules();
    }
    
    void setup() {
        makeCells = new int[size];

        for (int i = 0; i < cells.length; i++) {
            if (randomCellGen.nextFloat() < chance) {
                makeCells[i] = 1; // alive cell = 1
            } else {
                makeCells[i] = 0; // dead cell = 0
            }
        }
    }
    
    void drawCells(){
        cellsPanel.setLayout(new GridLayout(size, size));
        
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                GridTile cell = new GridTile(i, j); // Actually creating the new tile
                cells[i][j] = cell;
                
                cell.setFocusable(false);
                
                //Change the style of the buttons
                cell.setBorderPainted(true);
                cell.setContentAreaFilled(true);

                cell.setForeground(new Color(255,255, 255));
                cell.setBackground(new Color(10, 10, 10));
                cell.setOpaque(true);
                cell.setIsAlive();

                if (cell.isAlive == true) {
                    cell.setText("1");
                } else {
                    cell.setText("O");
                }
                
                cellsPanel.add(cell);
            }
        }
    }
    
        void cellRules(int rows, int cols){
        // Create a new 2D array to store the next generation state
        boolean[][] nextGeneration = new boolean[size][size];
        
        //go through each cell
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                //apply the rules to the cells
                if () {
                    
                }
        }
    }
    }
}


/*
 * // Loop through each cell
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int liveNeighbors = countLiveNeighbors(i, j);
        
                // Apply Conway's rules
                if (cells[i][j].isAlive) {
                    if (liveNeighbors < 2 || liveNeighbors > 3) {
                        nextGeneration[i][j] = false; // Cell dies
                    } else {
                        nextGeneration[i][j] = true; // Cell survives
                    }
                } else {
                    if (liveNeighbors == 3) {
                        nextGeneration[i][j] = true; // Cell is born
                    } else {
                        nextGeneration[i][j] = false; // Cell remains dead
                    }
                }
            }
        }

        // Update the state of cells based on the next generation
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j].isAlive = nextGeneration[i][j];
            }
        }
    }

    // Helper method to count live neighbors of a cell
    int countLiveNeighbors(int x, int y) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int newX = x + i;
                int newY = y + j;
                // Skip the cell itself
                if (i == 0 && j == 0) {
                    continue;
                }
                // Check boundaries
                if (newX >= 0 && newX < size && newY >= 0 && newY < size) {
                    if (cells[newX][newY].isAlive) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
 * 
 */
