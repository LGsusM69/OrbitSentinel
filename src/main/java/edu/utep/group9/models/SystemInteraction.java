package edu.utep.group9.models;

public class SystemInteraction {
    private User user;
    private String readFile;
    private String modified;
    private String action;
    
    public SystemInteraction(User user, String action) {
        this.user = user;
    }
    public String toString() {
        String interaction = "";
        return interaction;
    }
}
