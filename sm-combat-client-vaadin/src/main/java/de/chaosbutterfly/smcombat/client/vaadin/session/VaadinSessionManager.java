/**
 * 
 */
package de.chaosbutterfly.smcombat.client.vaadin.session;

import java.io.Serializable;

import javax.inject.Inject;

import com.vaadin.cdi.UIScoped;

import de.chaosbutterfly.smcombat.core.combat.commands.CharacterActsCommand;
import de.chaosbutterfly.smcombat.core.combat.ui.UIClientImplementation;
import de.chaosbutterfly.smcombat.core.session.GameSessionManagement;
import de.chaosbutterfly.smcombat.core.session.UserSessionManagement;
import de.chaosbutterfly.smcombat.model.character.CharacterSM;
import de.chaosbutterfly.smcombat.model.combat.CombatCharacterSM;
import de.chaosbutterfly.smcombat.model.session.SMCombatSession;
import de.chaosbutterfly.smcombat.model.session.UserSession;

/**
 * @author Alti
 * 
 *         manages the sessions from the clients, is a server component, maybe
 *         once in own module 'vaadin-connector'
 */
@UIScoped
public class VaadinSessionManager implements UIClientImplementation, Serializable {

    private static final long serialVersionUID = 1L;

    private UserSessionManagement usermanagement;
    private GameSessionManagement gameSessionManagement;

    public VaadinSessionManager() {
    }

    @Inject
    public VaadinSessionManager(UserSessionManagement usermanagement, GameSessionManagement gameSessionManagement) {
        super();
        this.usermanagement = usermanagement;
        this.gameSessionManagement = gameSessionManagement;
    }

    public SMVaadinSession logIn(String userName, String password) {
        UserSession userSession = usermanagement.logInUser(userName, password);
        SMVaadinSession result = null;
        if (userSession != null) {
            result = new SMVaadinSession(userSession);
        }
        return result;
    }

    public boolean logOut(SMVaadinSession sessionToEnd) {
        if (sessionToEnd == null) {
            return false;
        }
        return usermanagement.logOut(sessionToEnd.getUserSession());
    }

    public SMVaadinSession logInToGameSession(SMVaadinSession vaadinSession, CharacterSM character,
            SMCombatSession gameSession) {
        boolean success = gameSessionManagement.addCharacterToGameSession(gameSession, vaadinSession.getUserSession(),
                character);
        if (success) {
            vaadinSession.setGameSession(gameSession);
        }
        return vaadinSession;
    }

    public boolean logOutOfgameSession(SMVaadinSession sessionToEnd) {
        gameSessionManagement.removeCharacterFromGameSession(sessionToEnd.getGameSession());
        return true; //if session is closed successfully
    }

    @Override
    public CharacterActsCommand pullCharacterActCommand(CombatCharacterSM character) {
        //get gamesession via character
        //message session to use dialog

        return null;
    }
}
