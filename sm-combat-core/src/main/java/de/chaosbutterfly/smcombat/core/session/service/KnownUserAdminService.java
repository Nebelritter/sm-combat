/**
 * 
 */
package de.chaosbutterfly.smcombat.core.session.service;

import javax.ejb.Local;

import de.chaosbutterfly.smcombat.model.user.KnownUser;

/**
 * @author alters
 *
 */
@Local
public interface KnownUserAdminService {

    KnownUser addUser(String userName, String password, Boolean isAdmin);

    void editUser(long oldUserId, KnownUser editedUser);

    boolean removeUser(long userId);

    KnownUser getUser(String userName);

}
