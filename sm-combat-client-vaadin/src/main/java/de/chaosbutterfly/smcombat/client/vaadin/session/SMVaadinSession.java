/**
 * 
 */
package de.chaosbutterfly.smcombat.client.vaadin.session;

import de.chaosbutterfly.smcombat.core.session.data.SMCombatSession;
import de.chaosbutterfly.smcombat.core.session.data.UserSession;

/**
 * @author Alti
 *
 *         User session with added vaadin information
 *
 */
public class SMVaadinSession {

    private UserSession userSession;

    private SMCombatSession gameSession;

    public SMVaadinSession(UserSession userSession) {
        super();
        this.userSession = userSession;
    }

    public UserSession getUserSession() {
        return userSession;
    }

    public SMCombatSession getGameSession() {
        return gameSession;
    }

    public void setGameSession(SMCombatSession gameSession) {
        this.gameSession = gameSession;
    }
}
