package edu.utep.group9.io;

import edu.utep.group9.models.spaceObject.SpaceObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

public class TXTReportWriter {
    public static void writeReport(HashSet<SpaceObject> inOrbit, HashSet<SpaceObject> notInOrbit, String path) {
        try(BufferedWriter writer =
                new BufferedWriter(new FileWriter(path))) {
            writer.write("\n" + inOrbit.size() + " objects still in orbit:\n\n");
            for (SpaceObject object : inOrbit) {
                writer.write(object.toStringWithOrbitAssessment() + "\n");
            }
            writer.write("\n");
            writer.write(notInOrbit.size() + "objects exited the orbit:\n\n");
            for(SpaceObject object : notInOrbit) {
                writer.write(object.toStringWithOrbitAssessment() + "\n");
            }
            
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
