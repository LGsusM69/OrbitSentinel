package edu.utep.group9.controllers;

import edu.utep.group9.io.CSVReader;
import edu.utep.group9.models.SpaceObject;
import org.apache.commons.csv.CSVRecord;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        List<SpaceObject> objects = reader.getObjects();
        filterObjects(objects, objectType, orbit);
        System.out.println("tracking: " + objectType + " " + orbit);
        return toString(objects);
    }
    private List<SpaceObject> filterObjects(List<SpaceObject> objects, String objectType, String orbit) {
        objects.removeIf(object ->
            (orbit.equals("LEO") && !object.getAproximateOrbitType().equals("LEO"))
            ||(orbit.equals("INORBIT") && object.getAproximateOrbitType().equals(("")))
            ||(!objectType.equals("ALL") && !object.getObjectType().equalsIgnoreCase(objectType))
        );
        return objects;
    }
    
    private String toString(List<SpaceObject> objects) {
        StringBuilder sb = new StringBuilder();
        sb.append("Record ID, Satellite Name, Country, Orbit Type, Launch Year, Launch Site, ");
        sb.append("Longitude, Avg. Longitude, Geohash, Days Old\n");
        for (SpaceObject obj : objects) {
            sb.append(obj.toString()).append("\n");
        }
        return sb.toString();
    }
    
    public void assessDebre() {
        System.out.println("assessing if debre is on orbit...");
        String objects = track("ALL", "INORBIT");
        System.out.println(objects);
    }
}
