package edu.utep.group9.models;

import java.lang.reflect.Array;
import java.sql.Timestamp;

public abstract class SpaceObject {
    private String recordId;
    private String noradCatID;
    private String satelliteName;
    private String country;
    private String aproximateOrbitType;
    private String objectType;
    private int launchYear;
    private String launchSite;
    private double longitude;
    private double averageLongitude;
    private String geoHash;
    private String hrrCategory;
    private boolean isNominated;
    private Timestamp nominatedAt;
    private Boolean hasDosier;
    private Timestamp lastUpdatedAt;
    private String justification;
    private Array focusedAnalysis;
    private int daysOld;
    private long conjunctionCount;
    private boolean isUnkObject;

    public SpaceObject() {

    }

}
