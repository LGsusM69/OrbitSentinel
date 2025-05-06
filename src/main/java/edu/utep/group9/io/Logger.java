package edu.utep.group9.io;

import edu.utep.group9.models.spaceObject.SpaceObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;

public class Logger {
    public enum Action {
        LOGIN,
        LOGOUT,
        READ_FILE,
        UPDATE_FILE,
        GENERATE_REPORT,
        CREATE_USER,
        UPDATE_USER,
        DELETE_USER
    }
    
    public Logger() {
        try{
                BufferedWriter writer = new BufferedWriter(
                        new FileWriter("data/system-log.txt")
                );
            
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void  update(Action action, String username) {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        String log = time.toString() + " ";
        
        switch(action) {
            case LOGIN:
                log = "[user] Loged into the system as [type]";
                break;
            case LOGOUT:
                log = "[user] Logged out of the system.";
                break;
            case READ_FILE:
                log = "[user] read data from [file] ";
                break;
            case UPDATE_FILE:
                log = "[user] updated [file]";
                break;
            case GENERATE_REPORT:
                log = "[user] created a new report: [file]";
                break;
            case CREATE_USER:
                log = "[user] created a new user [username]";
                break;
            case UPDATE_USER:
                log = "[user] updated user: [username]";
                break;
            case DELETE_USER:
                log = "[user] deleted user: [username]";
                break;
        }
    }
}
