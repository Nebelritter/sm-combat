/**
 * 
 */
package de.chaosbutterfly.smcombat.core.session;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.chaosbutterfly.smcombat.model.combat.CombatCharacterSM;

/**
 * @author Alti
 *
 */
public class SMCombatSession {

    private UserLogInData gmLogInData;

    private Map<UserSession, List<CombatCharacterSM>> usersCharacters;

    public UserLogInData getGmLogInData() {
        return gmLogInData;
    }

    public void setGmLogInData(UserLogInData gmLogInData) {
        this.gmLogInData = gmLogInData;
    }

    public List<CombatCharacterSM> getCharactersByUser(UserSession userSession) {
        return new ArrayList<>(usersCharacters.get(userSession));
    }

    public boolean addCharacter4User(UserSession userSession, CombatCharacterSM combatCharacter) {
        List<CombatCharacterSM> characters = usersCharacters.get(userSession);
        if (characters == null) {
            characters = new ArrayList<>();
            characters.add(combatCharacter);
            usersCharacters.put(userSession, characters);
            return true;
        } else {
            for (CombatCharacterSM combatCharacterSM : characters) {
                if (combatCharacter.equals(combatCharacterSM.getCharacter())) {
                    return false;
                }
            }
            return characters.add(combatCharacter);
        }
    }

}
