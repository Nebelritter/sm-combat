/**
 * 
 */
package de.chaosbutterfly.smcombat.core.session;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import de.chaosbutterfly.smcombat.core.session.data.UserLogInData;
import de.chaosbutterfly.smcombat.core.session.data.UserSession;
import de.chaosbutterfly.smcombat.model.user.KnownUserDAO;

/**
 * @author Alti
 *
 */
@ApplicationScoped
public class UserSessionManagement {

	Logger LOGGER = Logger.getLogger(UserSessionManagement.class.getName());

    private Set<UserSession> loggedUsers;

    private KnownUserDAO userDAO;

    protected UserSessionManagement() {
    }

    @Inject
    public UserSessionManagement(KnownUserDAO userDAO) {
        super();
        this.userDAO = userDAO;
        loggedUsers = new HashSet<>();
    }

    public UserSession logInUser(UserLogInData logInData) {
        //check loginData
        boolean valid = userDAO.isUserValid(logInData.getUserName(), logInData.getPassword());
        if (!valid) {
            return null;
        }
        //check already logged in 
        UserSession session = isAlreadyLoggedIn(logInData);
        if (session == null) {
            session = new UserSession();
            session.setLogInData(logInData);
            loggedUsers.add(session);
			LOGGER.log(Level.INFO, "User logged in:" + logInData.getUserName());
            //TODO broadcast to other sessions that user logged in?

        } else {
            LOGGER.log(Level.INFO, "User(" + logInData.getUserName() + ") already logged in. Session:" + session);
        }
        return session;
    }

    public UserSession isAlreadyLoggedIn(UserLogInData logInData) {
        for (UserSession userSession : loggedUsers) {
            if (logInData.equals(userSession.getLogInData())) {
                return userSession;
            }
        }
        return null;
    }

    public boolean logOut(UserSession session) {
        //check loginData
		boolean success = loggedUsers.remove(session);
		if (success) {
			String userName = session.getLogInData().getUserName();
			LOGGER.log(Level.INFO, "User logged out:" + userName);
		}
		return success;
    }
}
