package edu.utep.group9.io;

import edu.utep.group9.models.user.User;
import edu.utep.group9.models.user.UserFactory;

import java.io.*;

public class UserManager {

    public static boolean createUser(User user) {
        File file = new File("data/users.csv");
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
                                                    //check for duplicates. if username found do not create new.
            while((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if(fields.length > 0 && fields[0].equals(user.getUsername())) {
                    return false;
                }
            }
        } catch (FileNotFoundException e) {
            //handle exception
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
                    //if no duplicates, write new user to file.
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.write(user.getUsername() + "," +
                    user.getPassword() + "," +
                    user.getType().toString());
            bw.newLine();
            return true;
        } catch(IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateUser(String data, String field, String newValue) {
        File file = new File("data/users.csv");
        File tempFile = new File("data/users_temp.csv");

        try (BufferedReader br = new BufferedReader(new FileReader(file));
             BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = br.readLine()) != null) {
                if (line.equals(data)) {
                    String[] parts = line.split(",");
                    if (parts.length < 3) return false;

                    switch (field) {
                        case "username":
                            line = newValue + "," + parts[1] + "," + parts[2];
                            break;
                        case "password":
                            line = parts[0] + "," + newValue + "," + parts[2];
                            break;
                        case "type":
                            line = parts[0] + "," + parts[1] + "," + newValue.toUpperCase();
                            break;
                        default:
                            return false;
                    }
                }
                bw.write(line);
                bw.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        // Replace original file with updated file
        if (!file.delete() || !tempFile.renameTo(file)) {
            return false;
        }

        return true;
    }

    public static boolean deleteUser(String data) {
        File file = new File("data/users.csv");
        File tempFile = new File("data/users_temp.csv");

        boolean deleted = false;

        try (BufferedReader br = new BufferedReader(new FileReader(file));
             BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = br.readLine()) != null) {
                if (line.equals(data)) {
                    deleted = true; // Skip writing this line
                    continue;
                }
                bw.write(line);
                bw.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        if (!file.delete() || !tempFile.renameTo(file)) {
            return false;
        }

        return deleted;
    }



    public static User getByUsername(String username) {
        File file = new File("data/users.csv");

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length >= 3 && fields[0].equals(username)) {
                    String password = fields[1];
                    String typeStr = fields[2].toUpperCase();

                    // Convert string to User.Type enum
                    User.Type type = User.Type.valueOf(typeStr);

                    // Use UserFactory to build the correct subclass
                    return UserFactory.getUser(username, password, type);
                }
            }
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }

        return null; // user not found
    }
    public static String authenticate(String username, String password, String type) {
        User user = getByUsername(username);
        if(user == null) return "Invalid username.";
        if(!user.getType().toString().equalsIgnoreCase(type))return "Wrong user type.";
        if(!user.comparePasswords(password)) return "Username and password do not match.";
        return user.toString(); // returns "username,type"
    }
}
