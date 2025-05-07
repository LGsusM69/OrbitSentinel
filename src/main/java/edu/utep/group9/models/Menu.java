package edu.utep.group9.models;

/**
 * This object represent an option in the menu of the UI.
 */
public class Menu {
    /**Name of the menu option*/
    private String label;
    /** The value of the option in the UI.*/
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