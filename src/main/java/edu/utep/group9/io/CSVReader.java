package edu.utep.group9.io;

import edu.utep.group9.models.SpaceObject;
import edu.utep.group9.models.SpaceObjectFactory;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    
    
    /* karl create objects like this:
    SpaceObject object = new DebrisBuilder()
    .recordID(1234)
    .noradCatID(bla bla)
    .satelliteName("bla bla bla")
    .....

     */
    private File file;
    private Reader reader;

    public CSVReader() {
        try {
            file = new File("data/rso_metrics.csv");
            this.reader = new FileReader(file);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public List<SpaceObject> buildData() throws IOException{
        List<SpaceObject> data = new ArrayList<>();
        Iterable<CSVRecord> records = CSVFormat.DEFAULT
                .withFirstRecordAsHeader()
                .withTrim()
                .parse(reader);
        for(CSVRecord record : records) {
            SpaceObject object = SpaceObjectFactory.getSpaceObject(record.get("object_type"));
            object.recordId(record.get("record_id"))
                    .noradCatID(record.get("norad_cat_id"))
                    .satelliteName(record.get("satellite_name"))
                    .country(record.get("country"))
                    .aproximateOrbitType(record.get("approximate_orbit_type"))
                    .objectType(record.get("object_type"))
                    .launchYear(Integer.parseInt(record.get("launch_year")))
                    .launchSite(record.get("launch_site"))
                    .longitude(Double.parseDouble(record.get("longitude")))
                    .avgLongitude(Double.parseDouble(record.get("avg_longitude")))
                    .geoHash(record.get("geohash"))
                    .hrrCategory(record.get("HRR_Category"))
                    .isNominated(Boolean.getBoolean(record.get("is_nominated")))
                    .nominatedAt(record.get("nominated_at").isBlank() ? null : Timestamp.valueOf(record.get("nominated_at")))
                    .hasDosier(Boolean.getBoolean(record.get("has_dossier")))
                    .lastUpdatedAt(record.get("last_updated_at").isBlank() ?
                            null : Timestamp.valueOf(record.get("last_updated_at")))
                    .justification(record.get("justification"))
                    .focusedAnalysis(record.get("focused_analysis"))
                    .daysOld(Integer.parseInt(record.get("days_old")))
                    .conjunctionCount(Long.parseLong(record.get("conjunction_count")))
                    .isUnkObject(Boolean.getBoolean(record.get("is_unk_object")));
            data.add(object);
        }
        return data;
    }
}
