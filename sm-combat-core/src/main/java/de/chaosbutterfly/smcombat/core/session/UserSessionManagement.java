/**
 * 
 */
package de.chaosbutterfly.smcombat.core.session;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import de.chaosbutterfly.smcombat.model.session.SessionsDAO;
import de.chaosbutterfly.smcombat.model.session.UserSession;
import de.chaosbutterfly.smcombat.model.user.KnownUser;
import de.chaosbutterfly.smcombat.model.user.KnownUserDAO;

/**
 * @author Alti
 *
 */
@Stateless
public class UserSessionManagement implements Serializable {
    private static final long serialVersionUID = 1L;

    private static Logger LOGGER = Logger.getLogger(UserSessionManagement.class.getName());

    private KnownUserDAO userDAO;

    private SessionsDAO sessionsDAO;

    protected UserSessionManagement() {
    }

    @Inject
    public UserSessionManagement(KnownUserDAO userDAO, SessionsDAO sessionsDAO) {
        super();
        this.userDAO = userDAO;
        this.sessionsDAO = sessionsDAO;
    }

    public UserSession logInUser(String userName, String password) {
        KnownUser knownUser = userDAO.loadUser(userName);
        //check loginData
        boolean valid = isUserValid(userName, password, knownUser);
        if (!valid) {
            return null;
        }
        //check already logged in
        UserSession session = sessionsDAO.isAlreadyLoggedIn(userName);
        if (session == null) {
            session = sessionsDAO.createNewUserSession(knownUser);
            LOGGER.log(Level.INFO, "User logged in:" + userName);
            //TODO broadcast to other sessions that user logged in?
        } else {
            LOGGER.log(Level.INFO, "User(" + userName + ") already logged in. Session:" + session);
        }
        return session;
    }

    private boolean isUserValid(String userName, String password, KnownUser knownUser) {
        if (knownUser != null) {
            if (password.equals(knownUser.getPassword())) {
                return true;
            } else {
                LOGGER.warning("Wrong password provided:" + password);
            }
        }
        LOGGER.info("User not found:" + userName);
        return false;
    }

    public UserSession isUserAlreadyLoggedIn(String userName) {
        return sessionsDAO.isAlreadyLoggedIn(userName);
    }

    public boolean logOut(UserSession userSession) {
        return sessionsDAO.logOut(userSession);
    }
}
