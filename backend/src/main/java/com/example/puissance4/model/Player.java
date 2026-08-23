package com.example.puissance4.model;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum Player {
    @JsonProperty("red")
    RED,
    @JsonProperty("yellow")
    YELLOW;

    public Player opponent() {
        return this == RED ? YELLOW : RED;
    }
}
