/**
 * 
 */
package de.chaosbutterfly.smcombat.core.session;

import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author Alti
 *
 */
@ApplicationScoped
public class UserSessionManagement {

    private Set<UserSession> loggedUsers;

    public UserSessionManagement() {
        super();
        loggedUsers = new HashSet<>();
    }

    public UserSession logInUser(UserLogInData logInData) {
        //check loginData
        UserSession session = checkSessionAlreadyActive(logInData);
        if (session == null) {
            session = new UserSession();
            session.setLogInData(logInData);
            loggedUsers.add(session);
        }
        return session;
    }

    private UserSession checkSessionAlreadyActive(UserLogInData logInData) {
        for (UserSession userSession : loggedUsers) {
            if (logInData.equals(userSession.getLogInData())) {
                return userSession;
            }
        }
        return null;
    }

    public boolean logOut(UserSession session) {
        //check loginData
        return loggedUsers.remove(session);
    }
}
