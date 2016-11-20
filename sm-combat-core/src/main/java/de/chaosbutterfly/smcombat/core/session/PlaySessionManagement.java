/**
 * 
 */
package de.chaosbutterfly.smcombat.core.session;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.ApplicationScoped;

import de.chaosbutterfly.smcombat.model.character.CharacterSM;
import de.chaosbutterfly.smcombat.model.combat.CombatCharacterSM;

/**
 * @author Alti
 *
 */
@ApplicationScoped
public class PlaySessionManagement {
    private Set<SMCombatSession> playSessions;

    public PlaySessionManagement() {
        super();
        playSessions = new HashSet<>();
    }

    public SMCombatSession openNewPlaySession(UserLogInData gmLogInData) {
        SMCombatSession playSession = checkPlaySessionActive(gmLogInData);
        if (playSession == null) {
            playSession = new SMCombatSession();
            playSession.setGmLogInData(gmLogInData);
            playSessions.add(playSession);
        }
        return playSession;
    }

    private SMCombatSession checkPlaySessionActive(UserLogInData gmLogInData) {
        for (SMCombatSession playSession : playSessions) {
            if (gmLogInData.equals(playSession.getGmLogInData())) {
                return playSession;
            }
        }
        return null;
    }

    public boolean addCharacterToPlaySession(SMCombatSession playSession, UserSession userSession,
            CharacterSM character) {
        SMCombatSession existingSession = null;
        for (SMCombatSession smCombatSession : playSessions) {
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
}
