/**
 * 
 */
package de.chaosbutterfly.smcombat.client.vaadin.session;

import de.chaosbutterfly.smcombat.core.session.SMCombatSession;
import de.chaosbutterfly.smcombat.core.session.UserSession;

/**
 * @author Alti
 *
 *         User session with added vaadin information
 *
 */
public class VaadinSession {

    private UserSession userSession;

    private SMCombatSession playSession;

    public VaadinSession(UserSession userSession) {
        super();
        this.userSession = userSession;
    }

    public UserSession getUserSession() {
        return userSession;
    }

    public SMCombatSession getPlaySession() {
        return playSession;
    }

    public void setPlaySession(SMCombatSession playSession) {
        this.playSession = playSession;
    }

}
