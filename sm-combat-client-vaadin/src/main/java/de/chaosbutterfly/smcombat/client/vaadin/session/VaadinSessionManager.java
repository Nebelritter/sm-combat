/**
 * 
 */
package de.chaosbutterfly.smcombat.client.vaadin.session;

import javax.inject.Inject;

import com.vaadin.cdi.UIScoped;

import de.chaosbutterfly.smcombat.core.combat.commands.CharacterActsCommand;
import de.chaosbutterfly.smcombat.core.combat.ui.UIClientImplementation;
import de.chaosbutterfly.smcombat.core.session.GameSessionManagement;
import de.chaosbutterfly.smcombat.core.session.UserSessionManagement;
import de.chaosbutterfly.smcombat.core.session.data.SMCombatSession;
import de.chaosbutterfly.smcombat.core.session.data.UserLogInData;
import de.chaosbutterfly.smcombat.core.session.data.UserSession;
import de.chaosbutterfly.smcombat.model.character.CharacterSM;
import de.chaosbutterfly.smcombat.model.combat.CombatCharacterSM;

/**
 * @author Alti
 * 
 *         manages the sessions from the clients, is a server component, maybe
 *         once in own module 'vaadin-connector'
 */
@UIScoped
public class VaadinSessionManager implements UIClientImplementation {

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

    public SMVaadinSession logIn(UserLogInData logInData) {
        UserSession userSession = usermanagement.logInUser(logInData);
        SMVaadinSession result = null;
        if (userSession != null) {
            result = new SMVaadinSession(userSession);
        }
        return result;
    }

    public boolean logOut(SMVaadinSession sessionToEnd) {
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
