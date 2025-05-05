package edu.utep.group9.io;

import edu.utep.group9.models.user.User;

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
}
