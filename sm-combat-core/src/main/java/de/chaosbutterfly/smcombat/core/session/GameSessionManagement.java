/**
 * 
 */
package de.chaosbutterfly.smcombat.core.session;

import javax.ejb.Stateless;
import javax.inject.Inject;

import de.chaosbutterfly.smcombat.model.character.CharacterSM;
import de.chaosbutterfly.smcombat.model.session.SMCombatSession;
import de.chaosbutterfly.smcombat.model.session.SessionsDAO;
import de.chaosbutterfly.smcombat.model.session.UserSession;

/**
 * @author Alti
 *
 */
@Stateless
public class GameSessionManagement {

    private SessionsDAO sessionsDAO;

    protected GameSessionManagement() {
        super();
    }

    @Inject
    public GameSessionManagement(SessionsDAO sessionsDAO) {
        super();
        this.sessionsDAO = sessionsDAO;
    }

    public SMCombatSession openNewGameSession(String gmUserName) {
        SMCombatSession gameSession = sessionsDAO.checkGameSessionActive(gmUserName);
        if (gameSession == null) {
            sessionsDAO.openNewGameSession(gmUserName);
        }
        return gameSession;
    }

    public SMCombatSession checkGameSessionActive(String gmUserName) {
        return sessionsDAO.checkGameSessionActive(gmUserName);
    }

    public boolean addCharacterToGameSession(SMCombatSession gameSession, UserSession userSession,
            CharacterSM character) {
        return sessionsDAO.addCharacterToGameSession(gameSession, userSession, character);
    }


    public void removeCharacterFromGameSession(SMCombatSession gameSession) {
        sessionsDAO.removeCharacterFromGameSession(gameSession);
    }
}
