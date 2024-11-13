# Puissance4

Un jeu de Puissance 4 en ligne construit avec un back-end Spring Boot et un front-end React. Les joueurs jouent tour à tour en déposant des jetons dans des colonnes pour aligner quatre jetons d'affilée, que ce soit verticalement, horizontalement ou en diagonale.

## Structure du Projet

Back-end : Spring Boot (Java) pour gérer la logique du jeu et les points de terminaison de l'API.  
Front-end : React (JavaScript) pour l'interface utilisateur du jeu et les interactions.  
Communication : Axios est utilisé côté front-end pour envoyer des requêtes HTTP à l'API Spring Boot.  

## Fonctionnalités

Déposer des jetons : Les joueurs peuvent cliquer sur une colonne pour y déposer leur jeton.  
Détection de victoire : La logique vérifie si un joueur a aligné quatre jetons.  
Réinitialiser le jeu : Permet de redémarrer le jeu à tout moment.  
Détection d'égalité : Le jeu détecte si la grille est pleine sans qu'aucun joueur n'ait gagné.  

## Technologies Utilisées

Back-end : Spring Boot, Maven  
Front-end : React, Axios  
Outils de build : Maven pour le back-end, npm pour le front-end  

## Mise en Route

### Prérequis

Java 17 ou version ultérieure  
Maven (pour construire le projet Spring Boot)  
Node.js et bun (pour le front-end React)  

### Exécution de l'Application

Clonez le dépôt :
```bash
git clone https://github.com/votreutilisateur/puissance4-jeu.git
cd puissance4-jeu
```

Démarrez le serveur Spring Boot (back-end) :

Ouvrez un terminal, allez dans le dossier backend, et construisez le projet avec Maven :
```bash
cd backend
mvn clean install
```

Lancez le serveur :
```bash
mvn spring-boot:run
```
    Le back-end sera accessible sur http://localhost:8080.

Démarrez l'application React (front-end) :

Ouvrez un autre terminal, allez dans le dossier frontend, et installez les dépendances :
```bash
cd ../frontend
npm install
```

Lancez le front-end :
```bash
npm start
```

Le front-end sera accessible sur http://localhost:3000.

## Instructions de Jeu

Ouvrez le jeu dans votre navigateur à http://localhost:3000.  
Les joueurs jouent chacun leur tour en cliquant sur une colonne pour y déposer leur jeton (Rouge et Jaune).  
Le jeu affiche le tour du joueur actuel et indique quand un joueur a gagné ou s’il y a égalité.  
Cliquez sur "Réinitialiser la partie" pour commencer une nouvelle partie.  
