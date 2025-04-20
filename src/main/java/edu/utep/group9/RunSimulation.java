package edu.utep.group9;

import edu.utep.group9.controllers.ScientistController;
import edu.utep.group9.controllers.UIController;
import edu.utep.group9.io.CSVReader;
import edu.utep.group9.util.MenuOptionsLoader;
import edu.utep.group9.view.ConsoleUI;

import java.util.Map;

public class RunSimulation {
    public static void main(String[] args) {

        //System.out.println("Initializing...");
        CSVReader reader = new CSVReader();
        try{reader.buildData();} catch(Exception e) {e.printStackTrace();}

        //ScientistController scientistController = new ScientistController();
        //UIController uiControl = new UIController(scientistController);
    }
}