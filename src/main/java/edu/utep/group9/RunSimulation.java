package edu.utep.group9;

import edu.utep.group9.controllers.ScientistController;
import edu.utep.group9.controllers.UIController;
import edu.utep.group9.io.CSVReader;
import edu.utep.group9.io.CSVWriter;
import edu.utep.group9.models.SpaceObject;
import edu.utep.group9.util.MenuOptionsLoader;
import edu.utep.group9.view.ConsoleUI;

import java.io.FileNotFoundException;
import java.util.Map;

public class RunSimulation {
    public static void main(String[] args) {

        System.out.println("Loading data...");
        CSVReader reader = null;
        try {
            reader = new CSVReader();
        } catch (FileNotFoundException e) {
            //throw new RuntimeException(e);
        }
        try{
            reader.buildData();
            CSVWriter.writeCSVFile(reader.getObjects(),"data/calamardo.csv");
        } catch(Exception e) {e.printStackTrace();}
        UIController uiControl = new UIController(new ScientistController(reader));
    }
}