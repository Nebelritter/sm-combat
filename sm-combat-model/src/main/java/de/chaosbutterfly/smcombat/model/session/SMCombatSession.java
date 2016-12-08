/**
 * 
 */
package de.chaosbutterfly.smcombat.model.session;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import de.chaosbutterfly.smcombat.model.combat.CombatCharacterSM;

/**
 * @author Alti
 *
 */
@Entity
@NamedQuery(name = SessionQuerys.NAME_QUERY_LOAD_GAME_SESSION_BY_GM_NAME, query = SessionQuerys.QUERY_LOAD_GAME_SESSION_BY_GM_NAME)
public class SMCombatSession {

    @Id
    @GeneratedValue
    private long id;

    private String gmUserName;

    @OneToMany
    private Map<UserSession, SessionCombatCharacterList> usersCharacters;

    public List<CombatCharacterSM> getCharactersByUser(UserSession userSession) {
        SessionCombatCharacterList characterList = usersCharacters.get(userSession);
        if (characterList != null) {
            return new ArrayList<>(characterList.getCharacterList());
        } else {
            return new ArrayList<>();
        }
    }

    public boolean addCharacter4User(UserSession userSession, CombatCharacterSM combatCharacter) {
        SessionCombatCharacterList characterList = usersCharacters.get(userSession);
        List<CombatCharacterSM> characters = characterList.getCharacterList();
        if (characters == null) {
            characters = new ArrayList<>();
            characters.add(combatCharacter);
            usersCharacters.put(userSession, characterList);
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the gmLogUserName
     */
    public String getGmUserName() {
        return gmUserName;
    }

    /**
     * @param gmUserName
     *            the gmLogUserName to set
     */
    public void setGmUserName(String gmUserName) {
        this.gmUserName = gmUserName;
    }

}
