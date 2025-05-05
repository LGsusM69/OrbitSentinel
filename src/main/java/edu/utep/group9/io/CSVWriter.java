package edu.utep.group9.io;

import edu.utep.group9.models.spaceObject.SpaceObject;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

public class CSVWriter {
    
    public static void writeCSVFile(HashSet<SpaceObject> objects, String path) {
        try (FileWriter writer = new FileWriter(path)) {
            writer.write(
                    "record_id,norad_cat_id,satellite_name,country," +
                            "approximate_orbit_type,object_type,launch_year," +
                            "launch_site,longitude,avg_longitude,geohash," +
                            "HRR_Category,is_nominated,nominated_at," +
                            "has_dossier,last_updated_at,justification," +
                            "focused_analysis,days_old,conjunction_count," +
                            "is_unk_object,still_in_orbit,risk_level\n");
            
            for (SpaceObject object : objects) {
                writer.write(String.join(",",
                        object.getRecordId(),
                        object.getNoradCatID(),
                        object.getSatelliteName(),
                        object.getCountry(),
                        object.getAproximateOrbitType(),
                        object.getObjectType(),
                        Integer.toString(object.getLaunchYear()),
                        object.getLaunchSite(),
                        Double.toString(object.getLongitude()),
                        Double.toString(object.getAvgLongitude()),
                        escapeForCSV(object.getGeoHash()),
                        escapeForCSV(object.getHrrCategory()),
                        String.valueOf(object.isNominated()),
                        (object.getNominatedAt() != null) ?
                                object.getNominatedAt().toString() : "",
                        Boolean.toString(object.getHasDosier()),
                        (object.getLastUpdatedAt() != null) ?
                                object.getLastUpdatedAt().toString() : "",
                        object.getJustification(),
                        object.getFocusedAnalysis(),
                        Integer.toString(object.getDaysOld()),
                        Long.toString(object.getConjunctionCount()),
                        Boolean.toString(object.isUnkObject()),
                        Boolean.toString(object.isStillInOrbit()),
                        object.getRiskLevel()
                ) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace(); // optional, but recommended
        }
    }
    
    public static String escapeForCSV(String value) {
        if (value == null) return "";
        boolean mustQuote = value.contains(",") || value.contains("\"") || value.contains("\n");
        if (mustQuote) {
            value = value.replace("\"", "\"\""); // escape inner quotes
            return "\"" + value + "\"";          // wrap in double quotes
        }
        return value;
    }
}
