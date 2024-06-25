 
/**
 * Write a description of class Conwaysforeal here.
 * this is the second attempt at the java project 
 * @author (Ari)
 * @version (v.1)
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

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
    JFrame window = new JFrame("ConwaysFoReal");
    
    GridTile[][] cells = new GridTile[size][size];
    public Conwaysforeal(){
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
                
                
                
                //Change the actions of the jbuttons
                cell.setFocusable(false);
                cell.setBorderPainted(true);
                cell.setContentAreaFilled(true);
                cell.setForeground(new Color(10, 10, 10));
                cell.setBackground(new Color(255, 255, 255));
                cell.setOpaque(true);
                cell.setIsAlive();
                cell.setFont(new Font ("Arial", Font.BOLD, 18));

                if (cell.isAlive == true) {
                    cell.setText("⬛");
                } else {
                    cell.setText("0");
                }
                
                cellsPanel.add(cell);
            }
        }
    }
    
    void nextGen() {
        int[][] nextGeneration = new int[size][size];
        
        //loop through every cell in the current gen
        for (int i = 0; i<size; i++) {
            for (int j = 0; j<size; j++) {
                int neighbors = checkNeighbors (i, j); //counts neighbors
                
                // applying the rules of conways
                if (cells[i][j].isAlive) {
                    if (neighbors < 2 || neighbors > 3) {
                        nextGeneration[i][j] = 0; //the cells dies due to under or overpoulaiton 
                    }else{
                        nextGeneration[i][j] = 1; // the cells does not die because the popuylation is good
                    }
                }else{
                    if(neighbors == 3) {
                        nextGeneration[i][j] = 1; // cell becomes alive due to the correct population
                    }else{
                        nextGeneration[i][j] = 0; // cell remains dead 
                        }
                }
            }
        }
        
        //Now update the main grid
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                cells[i][j].isAlive = (nextGeneration[i][j] == 1);
            }
        }
        
        //and redraw the cells 
        updateGrid();
    }
    
        void updateGrid() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                GridTile cell = cells[i][j];
                if (cell.isAlive) {
                    cell.setText("⬛");
                } else {
                    cell.setText("0");
                }
            }
        }
        cellsPanel.revalidate();
        cellsPanel.repaint();
    }
    
    int checkNeighbors(int i, int j){ // to see if neighboring cells are alive or dead (1 or 0) 
        int neighborsAlive = 0;
        // this checks all cells around the cell
        for (int r=-1; r<=1; r++){ 
            for (int c=-1; c<=1; c++){
                if (r==0 && c==0) continue;
             
            int row = r + i;
            int col = c + j;
                
            if (row >= 0 && row < size && col >=0 && col < size) {
                if (cells[row][col].isAlive) {
                    neighborsAlive ++;
                }
            }
        }
        }
        System.out.print(neighborsAlive);
        return neighborsAlive; 
    }


    public static void main(String[] args) {
        new Conwaysforeal();
    }
}
