package edu.utep.group9.controllers;

import edu.utep.group9.models.user.Scientist;
import edu.utep.group9.models.user.*;

public class AdminController {
    
    public void createUser(String username, String password, int input) {
        User user;
        switch (input) {
            case 1:
                user = new Scientist();
                break;
            case 2:
                user = new SpaceAgencyRepresentative();
                break;
            case 3:
                user = new PolicyMaker();
            case 4:
                user = new Administrator();
                
        }
    }
}
