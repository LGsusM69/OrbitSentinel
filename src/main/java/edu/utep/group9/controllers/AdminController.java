package edu.utep.group9.controllers;

import edu.utep.group9.io.UserManager;
import edu.utep.group9.models.user.*;
import edu.utep.group9.models.user.User.Type;

public class AdminController {
    
    public static User createUser(String username, String password, int type) {
        User user = UserFactory.getUser(username, password, Type.values()[type-1]);
        if(UserManager.createUser(user))
        return user;
        return null;
    }
    public static String validateUsername(String username) {
        User user = UserManager.getByUsername(username);
        return user != null ? user.toString() : "";

    }
    public static String update(String data, String field, String newValue) {
        String[] fields = data.split(",");
        if (fields.length < 3) return "Invalid input data format.";

        if (field.equals("username")) {
            if (validateUsername(newValue).equals("")) {
                boolean success = UserManager.updateUser(data, field, newValue);
                return success ? "Username updated." : "Update failed.";
            } else {
                return "Failed. Username is already in use.";
            }
        }

        if (field.equals("password")) {
            boolean success = UserManager.updateUser(data, field, newValue);
            return success ? "User " + field + " updated." : "Update failed.";
        }

        return "Invalid field.";
    }
    public static String updateType(String data, int type) {
        if (type < 1 || type > User.Type.values().length) {
            return "Invalid type selection.";
        }

        String[] fields = data.split(",");
        if (fields.length < 3) return "Invalid input data format.";

        String newType = User.Type.values()[type - 1].toString(); // 1-based to 0-based
        boolean success = UserManager.updateUser(data, "type", newType);
        return success ? "User type updated." : "Update failed.";
    }


}
