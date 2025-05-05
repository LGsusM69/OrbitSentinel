package edu.utep.group9.models.user;

/**This class builds a subtype of User.
 *
 */
public class UserFactory {
    
    public static User getUser(int type) {
        switch(type) {
            case 1:
                return new Scientist();
            case 2:
                return new SpaceAgencyRepresentative();
            case 3:
                return new PolicyMaker();
            case 4:
                return new Administrator();
            default:
                return new Scientist();
        }
    }
}