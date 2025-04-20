package edu.utep.group9.models;

public class User {
    private String username;
    private String password; // In a real system, use hashed passwords
    private Role role;

    public enum Role {
        SCIENTIST,
        SPACE_AGENCY_REPRESENTATIVE,
        POLICYMAKER,
        ADMINISTRATOR
    }
    public User() {}

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    // Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }


}

