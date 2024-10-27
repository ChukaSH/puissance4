import React, { useState } from 'react';
import './App.css';  // Pour styliser la grille

// Dimensions de la grille de Puissance 4
const COLUMNS = 7;
const ROWS = 6;

function App() {
  const [grid, setGrid] = useState(Array(ROWS).fill(Array(COLUMNS)));
  const [currentPlayer, setCurrentPlayer] = useState('red');

  const dropToken = (columnIndex) => {
    // Copie de la grille
    const newGrid = grid.map(row => [...row]);

    //
    for (let rowIndex = ROWS - 1; rowIndex >= 0; rowIndex--) {
      if (newGrid[rowIndex][columnIndex] === null) {
        // Ajouter le jeton du joueur actuel
        newGrid[rowIndex][columnIndex] = currentPlayer;
        setGrid(newGrid);

        if (checkWin(newGrid, currentPlayer)) {
          alert(`Le joueur ${currentPlayer === 'red' ? 'Rouge' : 'Jaune'} a gagné!`);
          return;
        }

        // Changer de joueur au prochain tour;
        setCurrentPlayer(currentPlayer === 'red' ? 'yellow' : 'red')
        break;
      }
    }
  };

  const checkWin = (grid, currentPlayer) => {
    // Vérification horizontale
    for (let row = 0; row < ROWS; row++) {
      for (let col = 0; col < COLUMNS - 3; col++) {
        if (grid[row][col] === currentPlayer &&
            grid[row][col + 1] === currentPlayer &&
            grid[row][col + 2] === currentPlayer &&
            grid[row][col + 3] === currentPlayer) {
          return true;
        }
      }
    }
  
    // Vérification verticale
    for (let col = 0; col < COLUMNS; col++) {
      for (let row = 0; row < ROWS - 3; row++) {
        if (grid[row][col] === currentPlayer &&
            grid[row + 1][col] === currentPlayer &&
            grid[row + 2][col] === currentPlayer &&
            grid[row + 3][col] === currentPlayer) {
          return true;
        }
      }
    }
  
    // Vérification diagonale (de gauche à droite)
    for (let row = 0; row < ROWS - 3; row++) {
      for (let col = 0; col < COLUMNS - 3; col++) {
        if (grid[row][col] === currentPlayer &&
            grid[row + 1][col + 1] === currentPlayer &&
            grid[row + 2][col + 2] === currentPlayer &&
            grid[row + 3][col + 3] === currentPlayer) {
          return true;
        }
      }
    }
  
    // Vérification diagonale (de droite à gauche)
    for (let row = 0; row < ROWS - 3; row++) {
      for (let col = 3; col < COLUMNS; col++) {
        if (grid[row][col] === currentPlayer &&
            grid[row + 1][col - 1] === currentPlayer &&
            grid[row + 2][col - 2] === currentPlayer &&
            grid[row + 3][col - 3] === currentPlayer) {
          return true;
        }
      }
    }
  
    return false;
  };

  // Afficher la grille sous forme de tableau
  return (
    <div className="App">
      <h1>Puissance 4</h1>
      <div className="grid">
        {grid.map((row, rowIndex) => (
          <div key={rowIndex} className="row">
            {row.map((cell, columnIndex) => (
              <div
                key={columnIndex}
                className={`cell ${cell}`} // Ajoute la classe 'red' ou 'yellow' pour la couleur du joueur
                onClick={() => dropToken(columnIndex)}
              >
                {/* Affiche un jeton si la cellule n'est pas vide */}
                {cell && <div className={`token ${cell}`}></div>}
              </div>
            ))}
          </div>
        ))}
      </div>
      <h2>Tour du joueur: {currentPlayer === 'red' ? 'Rouge' : 'Jaune'}</h2>
    </div>
  );
}

export default App;
