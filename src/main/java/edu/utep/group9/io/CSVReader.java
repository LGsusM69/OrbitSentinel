package edu.utep.group9.io;

import java.io.*;

public class CSVReader {
    
    
    /* karl create objects like this:
    SpaceObject object = new DebrisBuilder()
    .recordID(1234)
    .noradCatID(bla bla)
    .satelliteName("bla bla bla")
    .....

     */
    private File file;
    private BufferedReader reader;

    public CSVReader() {
        try {
            file = new File("data/rso_metrics.csv");
            this.reader = new BufferedReader(new FileReader(file));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
