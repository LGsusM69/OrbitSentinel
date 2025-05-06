package edu.utep.group9;

import edu.utep.group9.controllers.TrackingSystem;
import edu.utep.group9.controllers.UIController;
import edu.utep.group9.io.CSVReader;

import java.io.FileNotFoundException;
import java.io.IOException;

public class RunSimulation {
    public static void main(String[] args) {

        System.out.println("Loading data...");
        CSVReader reader = null;
        try {
            reader = new CSVReader();
        } catch (FileNotFoundException e) {
            //throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("Warning: data couldnt be loaded automatically.\n" +
                    "Check input files.");
            e.printStackTrace();
        }
        try{
            //CSVWriter.writeCSVFile(reader.getObjects(),"data/calamardo.csv");
        } catch(Exception e) {e.printStackTrace();}
        UIController uiControl = new UIController(new TrackingSystem(reader));
    }
}