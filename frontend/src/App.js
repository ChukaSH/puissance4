import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './App.css';

function App() {
  const [grid, setGrid] = useState([]);
  const [currentPlayer, setCurrentPlayer] = useState('');
  const [gameWon, setGameWon] = useState(false);

  // Initialisation du jeu
  useEffect(() => {
    fetchGameStatus();
  }, []);

  // Récupérer l'état du jeu depuis l'API
  const fetchGameStatus = async () => {
    try {
      const response = await axios.get('http://localhost:8080/puissance4/status');
      const { grid, currentPlayer, gameWon } = response.data;
      setGrid(grid);
      setCurrentPlayer(currentPlayer);
      setGameWon(gameWon);
    } catch (error) {
      console.error("Erreur lors de la récupération de l'état du jeu :", error);
    }
  };

  // Jouer un coup dans une colonne
  const playMove = async (columnIndex) => {
    if (gameWon) {
      alert("La partie est terminée. Veuillez réinitialiser pour jouer une nouvelle partie.");
      return;
    }

    try {
      await axios.post(`http://localhost:8080/puissance4/play/${columnIndex}`);
      fetchGameStatus();
    } catch (error) {
      console.error("Erreur lors de la tentative de jouer :", error);
      alert("Impossible de jouer dans cette colonne. Elle est peut-être pleine.");
    }
  };

  // Réinitialiser le jeu
  const resetGame = async () => {
    try {
      await axios.post('http://localhost:8080/puissance4/reset');
      fetchGameStatus();
    } catch (error) {
      console.error("Erreur lors de la réinitialisation du jeu :", error);
    }
  };

  return (
    <div className="App">
      <h1>Puissance 4</h1>
      <div className="grid">
        {grid.map((row, rowIndex) => (
          <div key={rowIndex} className="row">
            {row.map((cell, columnIndex) => (
              <div
                key={columnIndex}
                className={`cell ${cell || ''}`} // Ajoute la classe 'red' ou 'yellow' pour la couleur du joueur
                onClick={() => playMove(columnIndex)}
              >
                {cell && <div className={`token ${cell}`}></div>}
              </div>
            ))}
          </div>
        ))}
      </div>
      <h2>Tour du joueur: {currentPlayer === 'red' ? 'Rouge' : 'Jaune'}</h2>
      {gameWon && <h2>Le joueur {currentPlayer === 'red' ? 'Jaune' : 'Rouge'} a gagné!</h2>}
      <button onClick={resetGame}>Réinitialiser la partie</button>
    </div>
  );
}

export default App;
