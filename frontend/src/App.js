import React, { useState, useEffect } from 'react';
import axios from 'axios';

function App() {
  const [status, setStatus] = useState ('');

  useEffect(() => {
    axios.get('/puissance4/status')
      .then(response => {
        setStatus(response.data)
      })
      .catch(error => {
        console.error('Erreur lors de la récupération du statut du jeu', error);
      });
  }, []);
  
  return (
    <div>
      <h1>Puissance 4</h1>
      <p>Status du jeu: {status}</p>
    </div>
  );
}

export default App;
