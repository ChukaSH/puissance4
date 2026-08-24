package com.example.puissance4.model;

import java.util.Arrays;
import lombok.Getter;

@Getter
public class Game {
    
    public static final int COLUMNS = 7;
    public static final int ROWS = 6;

    private Cell[][] grid; // Grille du jeu (7 colonnes, 6 lignes)
    private Player currentPlayer; // Joueur actuel (RED ou YELLOW)
    private GameState state;

    public Game() {
        this.grid = new Cell[ROWS][COLUMNS];
        for (Cell[] row : grid) {
            Arrays.fill(row, Empty.EMPTY);
        }
        this.currentPlayer = Player.RED;
        this.state = Status.IN_PROGRESS;
    }

    public boolean dropToken(int columnIndex) {
        if (columnIndex < 0 || columnIndex >= COLUMNS || isGameWon() || isGameDraw()) {
            return false; 
        }
    
        for (int rowIndex = ROWS - 1; rowIndex >= 0; rowIndex--) {
            if (grid[rowIndex][columnIndex] instanceof Empty) {
                grid[rowIndex][columnIndex] = new Filled(currentPlayer);

                // Vérifie si le joueur a gagné avec ce mouvement
                if (checkWin(rowIndex, columnIndex, currentPlayer)) {
                    state = new Won(currentPlayer);
                } else if (isDraw()) {
                    state = Status.DRAW;
                }
    
                // Changement de joueur uniquement si le mouvement est valide
                currentPlayer = currentPlayer.opponent();
                return true;
            }
        }
        return false;
    }
    
    private boolean checkWin(int row, int col, Player player) {
        return checkDirection(row, col, 1, 0, player) ||  // Verticale
               checkDirection(row, col, 0, 1, player) ||  // Horizontale
               checkDirection(row, col, 1, 1, player) ||  // Diagonale \
               checkDirection(row, col, 1, -1, player);            // Diagonale /
    }
  
    private boolean checkDirection(int row, int col, int rowDelta, int colDelta, Player player) {
        int count = 0;
    
        // Vérifie dans une direction (ex : droite, bas, diagonale) sur 4 cases maximum
        for (int i = -3; i <= 3; i++) {
            int r = row + i * rowDelta;
            int c = col + i * colDelta;
    
            if (r >= 0 && r < ROWS && c >= 0 && c < COLUMNS && grid[r][c] instanceof Filled(Player p) && p == player) {
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
                if (grid[row][col] == Empty.EMPTY) {
                    return false; // Si une cellule est vide, il n'y a pas de jeu nul
                }
            }
        }
        return true; // Toutes les cellules sont pleines, jeu nul
    }

    public boolean isGameWon() {
        return state instanceof Won;
    }

    public boolean isGameDraw() {
        return state == Status.DRAW;
    }
}
