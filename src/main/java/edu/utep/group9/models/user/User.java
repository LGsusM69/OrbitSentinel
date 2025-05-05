package edu.utep.group9.models.user;

public abstract class User {
    private String username;
    private String password; // In a real system, use hashed passwords
    private Role role;
    private String name;
    private String lastName;

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
    
    public String getName() {return name;}
    public String getLastName() {return lastName;}

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

