/**
 * 
 */
package de.chaos_butterfly.sm_combat.model.combat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

import de.chaos_butterfly.sm_combat.model.character.CharacterSM;

/**
 * @author Alti
 *
 */
@Entity
@XmlRootElement
@Table(name = "CombatCharacter", uniqueConstraints = @UniqueConstraint(columnNames = "id"))

public class CombatCharacterSM {

	@OneToOne
	private CharacterSM character;

	@Id
	@GeneratedValue
	private Long id;

	private int tick;

	public CombatCharacterSM(CharacterSM character) {
		super();
		this.character = character;
		if (character == null) {
			throw new IllegalArgumentException("For combat character a character must always be provided");
		}
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the tick
	 */
	public int getTick() {
		return tick;
	}

	/**
	 * @param tick
	 *            the tick to set
	 */
	public void setTick(int tick) {
		this.tick = tick;
	}

}
