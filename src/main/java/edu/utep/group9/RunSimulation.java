package edu.utep.group9;

import edu.utep.group9.controllers.ScientistController;
import edu.utep.group9.controllers.UIController;
import edu.utep.group9.view.ConsoleUI;

public class RunSimulation {
    public static void main(String[] args) {

        System.out.println("Initializing...");

        ScientistController scientistController = new ScientistController();
        UIController uiControl = new UIController(scientistController);
    }
}