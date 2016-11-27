/**
 * 
 */
package de.chaosbutterfly.smcombat.model.user;

import javax.ejb.Local;

/**
 * @author alters
 *
 */
@Local
public interface KnownUserDAO {

    boolean removeUser(KnownUser userToDelete);

    boolean isUserValid(String userName, String password);

    boolean addUser(KnownUser newUser);

}
