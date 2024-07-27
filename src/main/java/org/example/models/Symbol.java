package org.example.models;

public class Symbol {
    private Character symbol;
    private String color;

    public Symbol(Character symbol) {
        this.symbol = symbol;
        this.color="black";
    }
    public Character getSymbol() {
        return symbol;
    }

    public void setSymbol(Character symbol) {
        this.symbol = symbol;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
