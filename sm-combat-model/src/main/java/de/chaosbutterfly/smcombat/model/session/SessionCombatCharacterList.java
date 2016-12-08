/**
 * 
 */
package de.chaosbutterfly.smcombat.model.session;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import de.chaosbutterfly.smcombat.model.combat.CombatCharacterSM;

/**
 * @author alters
 *
 */
@Entity
public class SessionCombatCharacterList {

    @Id
    @GeneratedValue
    private long id;

    @OneToMany
    private List<CombatCharacterSM> characterList;

    public SessionCombatCharacterList() {
        super();
        characterList = new ArrayList<>();
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the characterList
     */
    public List<CombatCharacterSM> getCharacterList() {
        return characterList;
    }

    /**
     * @param characterList
     *            the characterList to set
     */
    public void setCharacterList(List<CombatCharacterSM> characterList) {
        this.characterList = characterList;
    }

}
