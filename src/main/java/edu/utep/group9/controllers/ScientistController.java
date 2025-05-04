package edu.utep.group9.controllers;

import edu.utep.group9.io.CSVReader;
import edu.utep.group9.io.CSVWriter;
import edu.utep.group9.io.Logger;
import edu.utep.group9.io.TXTReportWriter;
import edu.utep.group9.models.SpaceObject;
import edu.utep.group9.models.User;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.util.*;

public class ScientistController {
    CSVReader reader;
    List<String> trackFields;
    
    public ScientistController() {
        trackFields = new ArrayList<>(Arrays.asList(
                "record_id",
                "satellite_name",
                "country",
                "approximate_orbit_type",
                "launch_year",
                "launch_site",
                "longitude",
                "avg_longitude",
                "geohash",
                "days_old"
        ));
    }
    /** constructor. takes a CSVReader object*/
    public ScientistController(CSVReader reader) {
        System.out.println("");
        this.reader = reader;
    }
    /**Function to track objects in LEO*/
    public String track(String objectType, String orbit) {
        HashSet<SpaceObject> objects = reader.getObjects();
        if(objects == null) {
            try {
                //System.out.println("Loading data..."); ... delegate to ui layer.
                reader.buildData();
                objects = reader.getObjects();
            } catch(IOException e) {
                // this code is UI responsability
               // System.out.println(
                    //"Error: data couldnt be loaded. Aborting operation\n" + e.toString());
            }
        }
        filterObjects(objects, objectType, orbit);  //remove objects not in LEO or not specified object type.
        // System.out.println("tracking: " + objectType + " " + orbit); move to UI controller
        Logger.update(new User(), "track");     // update log file
        return toString(objects);
    }
    
    /** Helper function. removes objects not matching criteria and
     * returns them as a separate HashSet
     * @param objects the set of object to operate on.
     * @param objectType object type to keep in the set
     * @param orbit objects in this orbit stay in the set
     * @return set of objects removed from the set.
     */
    private HashSet<SpaceObject> filterObjects(HashSet<SpaceObject> objects, String objectType, String orbit) {
        HashSet<SpaceObject> filteredOut = new HashSet<>();                             //set of objects to be removed
        objects.forEach((object) -> {
            if((orbit.equals("LEO") && !object.getAproximateOrbitType().equals("LEO"))
                    ||(orbit.equals("INORBIT") && (object.getAproximateOrbitType().equals("")
                    || object.getAproximateOrbitType().equals("Unknown Orbit Category")))
                    ||(!objectType.equals("ALL") && !object.getObjectType().equalsIgnoreCase(objectType))) {
                filteredOut.add(object);

            }
        });
        filteredOut.forEach(objects::remove);                       //removing objects from the original set

        return filteredOut;
    }
    
    /**parses the data in a set of SpaceObjects and derives a string from it.
     *
     * @param objects set of objects to operat  e on.
     * @return string of data.
     */
    private String toString(Set<SpaceObject> objects) {
        StringBuilder sb = new StringBuilder();
        sb.append("Record ID, Satellite Name, Country, Orbit Type, Launch Year, Launch Site, ");
        sb.append("Longitude, Avg. Longitude, Geohash, Days Old\n");
        for (SpaceObject obj : objects) {
            sb.append(obj.toString()).append("\n");
        }
        return sb.toString();
    }
    
    /**builds a set of SpaceObject matching the specified criteria.
     * creates or updates a txt and a csv file.
     */
    public void assessDebre() {
        HashSet<SpaceObject> objects = reader.getObjects();
        if(objects == null) {                                   //null check block
            try {
                //System.out.println("Loading data...");...delegate to UIController
                reader.buildData();
                objects = reader.getObjects();
            } catch(IOException e) {
                //System.out.println(
                    //"Error: data couldnt be loaded. Aborting operation\n" + e.toString());
            }
        }
        Set<SpaceObject> satellites = filterObjects(objects, "DEBRIS", "INORBIT"); //not of interest to this operation.

        HashSet <SpaceObject> notInOrbit = new HashSet<>(); //these get a false value for "still_in_orbit"
        for(SpaceObject object : objects) {                 //categorize each object into still in orbit or not, asess risk level.
            if(object.getLongitude() < -180.0
                    || object.getLongitude() > 180.0
                    || object.getDaysOld() < 15000
                    || object.getConjunctionCount() >= 1) {
                object.isStillInOrbit(false);
                notInOrbit.add(object);
            }
            else{
                object.isStillInOrbit(true);
                double orbitalDrift = Math.abs(
                        object.getLongitude() - object.getAvgLongitude()
                );
                String riskLevel = "LOW RISK";
                if(orbitalDrift > 10.00) riskLevel = "MODERATE RISK";
                if(orbitalDrift > 50.00) riskLevel = "HIGH RISK";
                object.riskLevel(riskLevel);
            }
        }
        for(SpaceObject object : notInOrbit) objects.remove(object);                            //object are separated from original set
        TXTReportWriter.writeReport(objects, notInOrbit, "data/orbit-statuss-report.txt"); //generate txt report
        for(SpaceObject object : notInOrbit) objects.add(object); // adding back objects removed for operation
        for(SpaceObject object : satellites) objects.add(object); //adding back satellites
        CSVWriter.writeCSVFile(objects, "data/data-out.csv");       //generate new csv data
        Logger.update(new User(), "track");                     //update log
    }
}
