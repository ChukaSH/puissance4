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
    private boolean gameDraw;

    public Game() {
        this.grid = new String[ROWS][COLUMNS];
        for (String[] row : grid) {
            Arrays.fill(row, null);
        }
        this.currentPlayer = "red";
        this.gameWon = false;
        this.gameDraw = false;
    }

    public boolean dropToken(int columnIndex) {
        if (columnIndex < 0 || columnIndex >= COLUMNS || gameWon || gameDraw) {
            return false; // Colonne invalide ou partie déjà finie
        }
    
        for (int rowIndex = ROWS - 1; rowIndex >= 0; rowIndex--) {
            if (grid[rowIndex][columnIndex] == null) {
                grid[rowIndex][columnIndex] = currentPlayer;
                
                // Vérifie si le joueur a gagné avec ce mouvement
                if (checkWin(rowIndex, columnIndex)) {
                    gameWon = true;
                } else if (isDraw()) {
                    gameDraw = true;
                }
    
                // Changement de joueur uniquement si le mouvement est valide
                currentPlayer = currentPlayer.equals("red") ? "yellow" : "red";
                return true;
            }
        }
        return false;
    }
    
    private boolean checkWin(int row, int col) {
        String player = grid[row][col];
        return checkDirection(row, col, 1, 0, player) ||  // Verticale
               checkDirection(row, col, 0, 1, player) ||  // Horizontale
               checkDirection(row, col, 1, 1, player) ||  // Diagonale \
               checkDirection(row, col, 1, -1, player);            // Diagonale /
    }
  
    private boolean checkDirection(int row, int col, int rowDelta, int colDelta, String player) {
        int count = 0;
    
        // Vérifie dans une direction (ex : droite, bas, diagonale) sur 4 cases maximum
        for (int i = -3; i <= 3; i++) {
            int r = row + i * rowDelta;
            int c = col + i * colDelta;
    
            if (r >= 0 && r < ROWS && c >= 0 && c < COLUMNS && grid[r][c] != null && grid[r][c].equals(player)) {
                count++;
                if (count == 4) {
                    return true;
                }
            } else {
                count = 0; // Réinitialise si la séquence est interrompue
            }
        }
        return false;
    }

    private boolean isDraw() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                if (grid[row][col] == null) {
                    return false; // Si une cellule est vide, il n'y a pas de jeu nul
                }
            }
        }
        return true; // Toutes les cellules sont pleines, jeu nul
    }
    // // Test de check de direction via stream - OBSOLETE bug parfois
    // private boolean checkDirection(int row, int col, int rowDelta, int colDelta, String player) {
    //     return java.util.stream.IntStream.rangeClosed(-3, 3)
    //         .mapToObj(i -> new int[]{row + i * rowDelta, col + i * colDelta})
    //         .filter(pos -> isWithinBounds(pos[0], pos[1]) && player.equals(grid[pos[0]][pos[1]]))
    //         .map(pos -> 1) 
    //         .collect(java.util.stream.Collectors.summingInt(Integer::intValue)) >= 4;
    // }
    
    // private boolean isWithinBounds(int row, int col) {
    //     return row >= 0 && row < ROWS && col >= 0 && col < COLUMNS;
    // }
}
