package edu.utep.group9.models;

import java.lang.reflect.Array;
import java.sql.Timestamp;

public class DebrisBuilder {
    private String recordID;
    
    public DebrisBuilder recordID(String recordID) {
        this.recordID = recordID;
        return this;
    }
}
