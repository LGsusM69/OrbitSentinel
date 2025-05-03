package edu.utep.group9.controllers;

import edu.utep.group9.io.CSVReader;
import edu.utep.group9.io.Logger;
import edu.utep.group9.io.TXTReportWriter;
import edu.utep.group9.models.SpaceObject;
import edu.utep.group9.models.User;
import org.apache.commons.csv.CSVRecord;

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
    public ScientistController(CSVReader reader) {
        this.reader = reader;
    }

    public String track(String objectType, String orbit) {
        HashSet<SpaceObject> objects = reader.getObjects();
        filterObjects(objects, objectType, orbit);
        System.out.println("tracking: " + objectType + " " + orbit);
        Logger.update(new User(), "track");
        return toString(objects);
    }
    private HashSet<SpaceObject> filterObjects(HashSet<SpaceObject> objects, String objectType, String orbit) {
        objects.removeIf(object ->
            (orbit.equals("LEO") && !object.getAproximateOrbitType().equals("LEO"))
            ||(orbit.equals("INORBIT") && (object.getAproximateOrbitType().equals("")
                                            || object.getAproximateOrbitType().equals("Unknown Orbit Category")))
            ||(!objectType.equals("ALL") && !object.getObjectType().equalsIgnoreCase(objectType))
        );
        return objects;
    }
    
    private String toString(Set<SpaceObject> objects) {
        StringBuilder sb = new StringBuilder();
        sb.append("Record ID, Satellite Name, Country, Orbit Type, Launch Year, Launch Site, ");
        sb.append("Longitude, Avg. Longitude, Geohash, Days Old\n");
        for (SpaceObject obj : objects) {
            sb.append(obj.toString()).append("\n");
        }
        return sb.toString();
    }
    
    public void assessDebre() {
        HashSet<SpaceObject> objects = reader.getObjects();
        filterObjects(objects, "DEBRIS", "INORBIT");
        HashSet <SpaceObject> notInOrbit = new HashSet<>();
        for(SpaceObject object : objects) {
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
        for(SpaceObject object : notInOrbit) objects.remove(object);
        TXTReportWriter.writeReport(objects, notInOrbit, "data/orbit-statuss-report.txt");
        Logger.update(new User(), "track");
    }
}
