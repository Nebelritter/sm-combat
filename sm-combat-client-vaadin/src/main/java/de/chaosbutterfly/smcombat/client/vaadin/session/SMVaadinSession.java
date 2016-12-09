/**
 * 
 */
package de.chaosbutterfly.smcombat.client.vaadin.session;

import java.io.Serializable;

import de.chaosbutterfly.smcombat.model.session.SMCombatSession;
import de.chaosbutterfly.smcombat.model.session.UserSession;

/**
 * @author Alti
 *
 *         User session with added vaadin information
 *
 */
public class SMVaadinSession implements Serializable {

	private static final long serialVersionUID = 1L;

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
