/**
 * 
 */
package de.chaos_butterfly.sm_combat.model.character;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import de.chaos_butterfly.sm_combat.model.modifiers.ModifieableSM;

/**
 * @author Stefan Alter
 *
 */
@Entity
public class CharacterSM extends ModifieableSM {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private AttributeSM charisma;
	private AttributeSM agility;
	private AttributeSM intuition;
	private AttributeSM constitution;
	private AttributeSM mystic;
	private AttributeSM strength;
	private AttributeSM mind;
	private AttributeSM willpower;

	private AttributeSM gk;
	private AttributeSM gsw;
	private AttributeSM ini;
	private AttributeSM lp;
	private AttributeSM fokus;
	private AttributeSM vtd;
	private AttributeSM gw;
	private AttributeSM kw;
	private AttributeSpendableSM splitterPoints;

	private AttributeSpendableSM lifeValue;
	private AttributeSpendableSM focusValue;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
