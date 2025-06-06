package edu.utep.group9.models.user;

/** This model represents a user in the system.
 * Holds basic user information.
 */
public abstract class User {
    private String username;
    private String password; // In a real system, use hashed passwords
    private Type type;
    private String name;
    private String lastName;

    public enum Type {
        SCIENTIST,
        SPACE_AGENCY_REPRESENTATIVE,
        POLICYMAKER,
        ADMINISTRATOR
    }
    public User() {}

    public User(String username, String password, Type type) {
        this.username = username;
        this.password = password;
        this.type = type;
    }
    public boolean comparePasswords(String password) {
        return password.equals(this.password);
    }
    public String toString() {
        return username + "," + type.toString();
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Type getType() {
        return type;
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

    public void setRole(Type type) {
        this.type = type;
    }

}

