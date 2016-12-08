/**
 * 
 */
package de.chaosbutterfly.smcombat.model.session;

import java.io.Serializable;

import javax.ejb.Local;

import de.chaosbutterfly.smcombat.model.character.CharacterSM;
import de.chaosbutterfly.smcombat.model.user.KnownUser;

/**
 * @author alters
 *
 */
@Local
public interface SessionsDAO extends Serializable {

    UserSession createNewUserSession(KnownUser knownUser);

    UserSession isAlreadyLoggedIn(String userName);

    boolean logOut(UserSession session);

    SMCombatSession openNewGameSession(String gmUserName);

    SMCombatSession checkGameSessionActive(String gmUserName);

    boolean addCharacterToGameSession(SMCombatSession gameSession, UserSession userSession, CharacterSM character);

    void removeCharacterFromGameSession(SMCombatSession gameSession);

}
