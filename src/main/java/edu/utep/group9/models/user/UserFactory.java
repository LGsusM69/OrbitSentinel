package edu.utep.group9.models.user;

import edu.utep.group9.models.user.User.Type;

import static edu.utep.group9.models.user.User.Type.*;

/**This class builds a subtype of User.
 * It returns the required user.
 */
public class UserFactory {
    
    public static User getUser(String username, String password, Type type) {
        switch(type) {
            case SCIENTIST:
                return new Scientist(username, password, SCIENTIST);
            case SPACE_AGENCY_REPRESENTATIVE:
                return new SpaceAgencyRepresentative(username, password, SPACE_AGENCY_REPRESENTATIVE);
            case POLICYMAKER:
                return new PolicyMaker(username, password, POLICYMAKER);
            case ADMINISTRATOR:
                return new Administrator(username, password, ADMINISTRATOR);
            default:
                return new Scientist(username, password, SCIENTIST);
        }
    }
}