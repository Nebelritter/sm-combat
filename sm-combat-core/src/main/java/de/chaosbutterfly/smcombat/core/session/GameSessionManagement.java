/**
 * 
 */
package de.chaosbutterfly.smcombat.core.session;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;

import de.chaosbutterfly.smcombat.core.session.data.SMCombatSession;
import de.chaosbutterfly.smcombat.core.session.data.UserLogInData;
import de.chaosbutterfly.smcombat.core.session.data.UserSession;
import de.chaosbutterfly.smcombat.model.character.CharacterSM;
import de.chaosbutterfly.smcombat.model.combat.CombatCharacterSM;

/**
 * @author Alti
 *
 */
@ApplicationScoped
public class GameSessionManagement {
    private Set<SMCombatSession> gameSessions;

    public GameSessionManagement() {
        super();
        gameSessions = new HashSet<>();
    }

    public SMCombatSession openNewPlaySession(UserLogInData gmLogInData) {
        SMCombatSession gameSession = checkGameSessionActive(gmLogInData);
        if (gameSession == null) {
            gameSession = new SMCombatSession();
            gameSession.setGmLogInData(gmLogInData);
            gameSessions.add(gameSession);
        }
        return gameSession;
    }

    private SMCombatSession checkGameSessionActive(UserLogInData gmLogInData) {
        for (SMCombatSession gameSession : gameSessions) {
            if (gmLogInData.equals(gameSession.getGmLogInData())) {
                return gameSession;
            }
        }
        return null;
    }

    public boolean addCharacterToGameSession(SMCombatSession playSession, UserSession userSession,
            CharacterSM character) {
        SMCombatSession existingSession = null;
        for (SMCombatSession smCombatSession : gameSessions) {
            if (smCombatSession.equals(playSession)) {
                existingSession = smCombatSession;
            }
        }
        if (existingSession == null) {
            return false;
        }
        //check if character already exists in that session
        List<CombatCharacterSM> charactersInSession = existingSession.getCharactersByUser(userSession);
        CombatCharacterSM existingCharacter = getExistingCharacter(charactersInSession, character);
        if (existingCharacter == null) {
            CombatCharacterSM newCombatCharacter = new CombatCharacterSM(character);
            return existingSession.addCharacter4User(userSession, newCombatCharacter);
        } else {
            return false;
        }
    }

    private CombatCharacterSM getExistingCharacter(List<CombatCharacterSM> charactersInSession, CharacterSM character) {
        if (charactersInSession == null) {
            return null;
        }
        for (CombatCharacterSM combatCharacterSM : charactersInSession) {
            if (character.equals(combatCharacterSM.getCharacter())) {
                return combatCharacterSM;
            }
        }
        return null;
    }

    public void removeCharacterFromGameSession(SMCombatSession gameSession) {

    }
}
