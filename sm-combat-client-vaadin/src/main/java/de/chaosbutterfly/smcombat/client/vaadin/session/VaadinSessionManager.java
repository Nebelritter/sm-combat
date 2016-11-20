/**
 * 
 */
package de.chaosbutterfly.smcombat.client.vaadin.session;

import javax.ejb.Stateless;
import javax.inject.Inject;

import de.chaosbutterfly.smcombat.core.combat.commands.CharacterActsCommand;
import de.chaosbutterfly.smcombat.core.combat.ui.UIClientImplementation;
import de.chaosbutterfly.smcombat.core.session.PlaySessionManagement;
import de.chaosbutterfly.smcombat.core.session.SMCombatSession;
import de.chaosbutterfly.smcombat.core.session.UserLogInData;
import de.chaosbutterfly.smcombat.core.session.UserSession;
import de.chaosbutterfly.smcombat.core.session.UserSessionManagement;
import de.chaosbutterfly.smcombat.model.character.CharacterSM;
import de.chaosbutterfly.smcombat.model.combat.CombatCharacterSM;

/**
 * @author Alti
 * 
 *         manages the sessions from the clients, is a server component, maybe
 *         once in own module 'vaadin-connector'
 */
@Stateless
public class VaadinSessionManager implements UIClientImplementation {

    private UserSessionManagement usermanagement;
    private PlaySessionManagement playSessionManagement;

    @Inject
    public VaadinSessionManager(UserSessionManagement usermanagement, PlaySessionManagement playSessionManagement) {
        super();
        this.usermanagement = usermanagement;
        this.playSessionManagement = playSessionManagement;
    }

    public VaadinSession logIn(UserLogInData logInData) {
        UserSession userSession = usermanagement.logInUser(logInData);
        VaadinSession vaadinSession = new VaadinSession(userSession);
        //add vaadin data
        return vaadinSession;
    }

    public VaadinSession logInToPlaySession(VaadinSession vaadinSession, CharacterSM character,
            SMCombatSession playSession) {
        boolean success = playSessionManagement.addCharacterToPlaySession(playSession, vaadinSession.getUserSession(),
                character);
        if (success) {
            vaadinSession.setPlaySession(playSession);
        }
        return vaadinSession;
    }

    public boolean logOut(VaadinSession sessionToEnd) {
        usermanagement.logOut(sessionToEnd.getUserSession());

        return true; //if session is closed successfully
    }

    @Override
    public CharacterActsCommand pullCharacterActCommand(CombatCharacterSM character) {
        //broadcast to all playsessions

        return null;
    }
}
