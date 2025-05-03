package edu.utep.group9.io;

import edu.utep.group9.models.SpaceObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

public class TXTReportWriter {
    public static void writeReport(HashSet<SpaceObject> inOrbit,
                                    HashSet<SpaceObject> notInOrbit, String path) {
        try(BufferedWriter writer =
                new BufferedWriter(new FileWriter(path))) {
            writer.write("Objects still in orbit: " + inOrbit.size() + "\n");
            writer.write("Objects exited the orbit: " + notInOrbit.size() + "\n");
            writer.write("Objects not in orbit:\n");
            for(SpaceObject object : notInOrbit) {
                writer.write(object.toString() + "\n");
            }
            
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
