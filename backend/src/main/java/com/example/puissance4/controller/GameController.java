package com.example.puissance4.controller;

import com.example.puissance4.model.Game;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/puissance4")
@CrossOrigin(origins = "http://localhost:3000") // Pour autoriser le front React à accéder à l'API
public class GameController {
    
    private Game game = new Game();

    @GetMapping("/status") // Récupérer l'état actuel du jeu
    public Game getGameStatus() {
        return game;
    }

    @PostMapping("/reset") // Recommencer un nouveau jeu
    public Game resetGame() {
        this.game = new Game();
        return game;
    }

    @PostMapping("/play/{columnIndex}")
    public Game playToken(@PathVariable int columnIndex) {
        boolean success = game.dropToken(columnIndex);

        if (!success) {
            throw new IllegalArgumentException("Invalid move or column full");
        }
        
        return game;
    }
}
