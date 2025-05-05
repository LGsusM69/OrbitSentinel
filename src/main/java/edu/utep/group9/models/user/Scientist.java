package edu.utep.group9.models.user;

/**This class extends the user class.
 * It models a scientist, which is one of
 * the users
 */
public class Scientist extends User {

    public Scientist(String username, String password, Type type) {
        super(username, password, type);
    }
}
