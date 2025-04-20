package edu.utep.group9.models;

public class SpaceObjectFactory {
    
    public static SpaceObject getSpaceObject(String objectType) {
        switch(objectType.toUpperCase()) {
            case "PAYLOAD":
                return new Satellite();
            case "DEBRIS":
                return new Debris();
            case "ROCKET BODY":
                return new Debris();
            default:
                return new Debris();
        }
    }
}
