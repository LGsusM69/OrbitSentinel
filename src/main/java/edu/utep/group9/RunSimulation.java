package edu.utep.group9;

import edu.utep.group9.view.ConsoleUI;

public class RunSimulation {
    public static void main(String[] args) {

        System.out.println("Initializing...");
        
        ConsoleUI ui = new ConsoleUI();
        ui.run();
    }
}