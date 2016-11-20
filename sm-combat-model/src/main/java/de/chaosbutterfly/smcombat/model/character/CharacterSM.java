/**
 * 
 */
package de.chaosbutterfly.smcombat.model.character;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import de.chaosbutterfly.smcombat.model.modifiers.ModifieableSM;

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

    @OneToOne
	private AttributeSM charisma;
    @OneToOne
	private AttributeSM agility;
    @OneToOne
	private AttributeSM intuition;
    @OneToOne
	private AttributeSM constitution;
    @OneToOne
	private AttributeSM mystic;
    @OneToOne
	private AttributeSM strength;
    @OneToOne
	private AttributeSM mind;
    @OneToOne
	private AttributeSM willpower;

    @OneToOne
	private AttributeSM gk;
    @OneToOne
	private AttributeSM gsw;
    @OneToOne
	private AttributeSM ini;
    @OneToOne
	private AttributeSM lp;
    @OneToOne
	private AttributeSM fokus;
    @OneToOne
	private AttributeSM vtd;
    @OneToOne
	private AttributeSM gw;
    @OneToOne
	private AttributeSM kw;

    @OneToOne
	private AttributeSpendableSM splitterPoints;

    @OneToOne
	private AttributeSpendableSM lifeValue;
    @OneToOne
	private AttributeSpendableSM focusValue;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
