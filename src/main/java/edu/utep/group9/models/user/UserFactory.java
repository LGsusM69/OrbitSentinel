package edu.utep.group9.models.user;

import edu.utep.group9.models.user.User.Type;

import static edu.utep.group9.models.user.User.Type.*;

/**This class builds a subtype of User.
 *
 */
public class UserFactory {
    
    public static User getUser(String username, String password, int type) {
        switch(type) {
            case 1:
                return new Scientist(username, password, SCIENTIST);
            case 2:
                return new SpaceAgencyRepresentative(username, password, SPACE_AGENCY_REPRESENTATIVE);
            case 3:
                return new PolicyMaker(username, password, POLICYMAKER);
            case 4:
                return new Administrator(username, password, ADMINISTRATOR);
            default:
                return new Scientist(username, password, SCIENTIST);
        }
    }
}