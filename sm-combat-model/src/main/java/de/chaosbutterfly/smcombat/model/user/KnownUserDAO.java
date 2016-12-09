/**
 * 
 */
package de.chaosbutterfly.smcombat.model.user;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;

/**
 * @author alters
 *
 */
@Local
public interface KnownUserDAO extends Serializable {

    boolean removeUser(KnownUser userToDelete);

    boolean isUserValid(String userName, String password);

    KnownUser addUser(KnownUser newUser);

    KnownUser loadUser(String userName);

    void saveUser(KnownUser oldUser);

    KnownUser loadUser(long oldUserId);

    boolean removeUser(long userId);

    List<KnownUser> getAllUsers();

}
