package edu.utep.group9.controllers;

import edu.utep.group9.io.UserManager;
import edu.utep.group9.models.user.Scientist;
import edu.utep.group9.models.user.*;

public class AdminController {
    
    public static User createUser(String username, String password, int type) {
        User user = UserFactory.getUser(username, password, type);
        if(UserManager.createUser(user))
        return user;
        return null;
    }
}
