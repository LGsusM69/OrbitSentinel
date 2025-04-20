package edu.utep.group9.models;

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
    private double avgLongitude;
    private String geoHash;
    private String hrrCategory;
    private boolean isNominated;
    private Timestamp nominatedAt;
    private Boolean hasDosier;
    private Timestamp lastUpdatedAt;
    private String justification;
    private String focusedAnalysis;
    private int daysOld;
    private long conjunctionCount;
    private boolean isUnkObject;

    public SpaceObject() {

    }
    public SpaceObject noradCatID(String noradCatID) {
        this.noradCatID = noradCatID;
        return this;
    }
    
    public SpaceObject recordId(String recordId) {
        this.recordId = recordId;
        return this;
    }
    
    
    public SpaceObject satelliteName(String satelliteName) {
        this.satelliteName = satelliteName;
        return this;
    }
    
    public SpaceObject country(String country) {
        this.country = country;
        return this;
    }
    
    public SpaceObject aproximateOrbitType(String aproximateOrbitType) {
        this.aproximateOrbitType = aproximateOrbitType;
        return this;
    }
    
    public SpaceObject objectType(String objectType) {
        this.objectType = objectType;
        return this;
    }
    
    public SpaceObject launchYear(int launchYear) {
        this.launchYear = launchYear;
        return this;
    }
    
    public SpaceObject launchSite(String launchSite) {
        this.launchSite = launchSite;
        return this;
    }
    
    public SpaceObject longitude(double longitude) {
        this.longitude = longitude;
        return this;
    }
    
    public SpaceObject avgLongitude(double avgLongitude) {
        this.avgLongitude = avgLongitude;
        return this;
    }
    
    public SpaceObject geoHash(String geoHash) {
        this.geoHash = geoHash;
        return this;
    }
    
    public SpaceObject hrrCategory(String hrrCategory) {
        this.hrrCategory = hrrCategory;
        return this;
    }
    
    public SpaceObject isNominated(boolean isNominated) {
        this.isNominated = isNominated;
        return this;
    }
    
    public SpaceObject nominatedAt(Timestamp nominatedAt) {
        this.nominatedAt = nominatedAt;
        return this;
    }
    
    public SpaceObject hasDosier(Boolean hasDosier) {
        this.hasDosier = hasDosier;
        return this;
    }
    
    public SpaceObject lastUpdatedAt(Timestamp lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
        return this;
    }
    
    public SpaceObject justification(String justification) {
        this.justification = justification;
        return this;
    }
    
    public SpaceObject focusedAnalysis(String focusedAnalysis) {
        this.focusedAnalysis = focusedAnalysis;
        return this;
    }
    
    public SpaceObject daysOld(int daysOld) {
        this.daysOld = daysOld;
        return this;
    }
    
    public SpaceObject conjunctionCount(long conjunctionCount) {
        this.conjunctionCount = conjunctionCount;
        return this;
    }
    
    public SpaceObject isUnkObject(boolean isUnkObject) {
        this.isUnkObject = isUnkObject;
        return this;
    }
    
    
    
}
