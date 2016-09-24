/**
 * 
 */
package de.chaos_butterfly.sm_combat.model.combat.ticks;

import de.chaos_butterfly.sm_combat.model.combat.CombatCharacterSM;

/**
 * @author Alti
 *
 */
public class TickEffectCharacterActs implements TickEffect {
	private CombatCharacterSM character;

	/**
	 * @return the character
	 */
	public CombatCharacterSM getCharacter() {
		return character;
	}

	/**
	 * @param character
	 *            the character to set
	 */
	public void setCharacter(CombatCharacterSM character) {
		this.character = character;
	}
}
