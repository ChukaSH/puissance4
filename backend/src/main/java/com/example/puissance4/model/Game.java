package com.example.puissance4.model;

import java.util.Arrays;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Game {
    
    public static final int COLUMNS = 7;
    public static final int ROWS = 6;

    private String[][] grid; // Grille du jeu (7 colonnes, 6 lignes)
    private String currentPlayer; // Joueur actuel ("red" ou "yellow")
    private boolean gameWon;

    public Game() {
        this.grid = new String[ROWS][COLUMNS];
        for (String[] row : grid) {
            Arrays.fill(row, null);
        }
        this.currentPlayer = "red";
        this.gameWon = false;
    }

    public boolean dropToken (int columnIndex) {
        for (int rowIndex = ROWS - 1; rowIndex >= 0; rowIndex--) {
            if (grid[rowIndex][columnIndex] == null) {
                grid[rowIndex][columnIndex] = currentPlayer;
            }
            
            if (checkWin(rowIndex, columnIndex)) {
                gameWon = true;
            }

            currentPlayer = currentPlayer.equals("red") ? "yellow" : "red";
            return true;
        }
        
        return false;
    }
    
    private boolean checkWin (int row, int col) {
        //TODO
        return false;
    }
}
