
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

                cell.setForeground(new Color(10,10,10));
                cell.setBackground(new Color(255,255, 255));
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
}
