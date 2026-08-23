package com.example.puissance4.model;

public sealed interface GameState permits Won, Status {}
record Won(Player winner) implements GameState {}
enum Status implements GameState { IN_PROGRESS, DRAW }
