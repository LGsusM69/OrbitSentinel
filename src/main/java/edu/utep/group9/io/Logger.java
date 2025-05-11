package edu.utep.group9.io;

import edu.utep.group9.models.spaceObject.SpaceObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

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
    
    }
    
    private static void writeLog(String log) {
        try(BufferedWriter writer = new BufferedWriter(
                new FileWriter("data/system-log.txt", true)
        )){
            writer.write(log);
            
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void login(String user) {
        Timestamp time = new Timestamp(new Date().getTime());
        String log = "[" + time + "] " + user + " logedin to the system.\n";
        writeLog(log);
    }
    
    public static void logout(String user) {
        Timestamp time = new Timestamp(new Date().getTime());
        String log = "[" + time + "] " + user + " loged out of the system.\n";
        writeLog(log);
    }
    public static void readFile(String user, String filename) {
        Timestamp time = new Timestamp(new Date().getTime());
        String log = "[" + time + "] " + user + " read file [" + filename + "].\n";
        writeLog(log);
    }
    public static void queryData(String user, String object, String orbit) {
        Timestamp time = new Timestamp(new Date().getTime());
        String log = "[" + time + "] " + user +" requested space object data." +
                "object type: " + object + "orbit type: " + orbit + "\n";
        writeLog(log);
    }
    public static void generateData(String user, String filename) {
        Timestamp time = new Timestamp(new Date().getTime());
        String log = "[" + time + "] " + user + " wrote file [" + filename + "].\n";
        writeLog(log);
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
