/**
 * 
 */
package de.chaosbutterfly.smcombat.core.session.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import de.chaosbutterfly.smcombat.model.user.KnownUser;
import de.chaosbutterfly.smcombat.model.user.KnownUserDAO;

/**
 * @author Alti
 *
 */
@Stateless
public class KnownUserAdminServiceImpl implements KnownUserAdminService, Serializable {

    private static final long serialVersionUID = 1L;

    private KnownUserDAO userDao;

    /**
     * @param userDao
     */
    @Inject
    protected KnownUserAdminServiceImpl(KnownUserDAO userDao) {
        super();
        this.userDao = userDao;
    }

    @Override
    public KnownUser addUser(String userName, String password, Boolean isAdmin) {
        KnownUser newUser = new KnownUser();
        newUser.setPassword(password);
        newUser.setUserName(userName);
        newUser.setIsAdmin(isAdmin);
        return userDao.addUser(newUser);
    }

    @Override
    public void editUser(long oldUserId, KnownUser editedUser) {
        KnownUser oldUser = userDao.loadUser(oldUserId);
        oldUser.setUserName(editedUser.getUserName());
        oldUser.setPassword(editedUser.getPassword());
        oldUser.setIsAdmin(editedUser.getIsAdmin());
        userDao.saveUser(oldUser);
    }

    @Override
    public boolean removeUser(long userId) {
        return userDao.removeUser(userId);
    }

    @Override
    public KnownUser getUser(String userName) {
        return userDao.loadUser(userName);
    }

    @Override
    public List<KnownUser> getAllKnownUsers() {
        return new ArrayList<>(userDao.getAllUsers());
    }
}
