package edu.utep.group9.models;

public class Menu {
    private String label;
    private int value;

    public String getLabel() { return label; }
    public int getValue() { return value; }

    // Jackson needs a no-arg constructor
    public Menu() {}

    public Menu(String label, int value) {
        this.label = label;
        this.value = value;
    }
}