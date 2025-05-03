package edu.utep.group9.io;

import edu.utep.group9.models.User;

import java.sql.Timestamp;

public class Logger {
    
    final String track = "TRACK";
    
    public static void  update(User user, String action) {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        String log = "[" + time.toString() + "]";
        
        switch(action) {
            case "TRACK":
                log += " " + /*user.getUsername()*/"[user]" + " Queried space objects of type [type]";
                break;
            
        }
    }
}
