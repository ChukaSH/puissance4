package com.example.puissance4.model;
import com.fasterxml.jackson.annotation.JsonValue;

public sealed interface Cell permits Filled, Empty {
    @JsonValue
    Player player();
}
record Filled(Player color) implements Cell {
    public Player player() {
        return color;
    }
}
enum Empty implements Cell { 
    EMPTY;

    public Player player() {
        return null;
    }
}