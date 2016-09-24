/**
 * 
 */
package de.chaos_butterfly.sm_combat.core.ticks;

import javax.inject.Inject;

import de.chaos_butterfly.sm_combat.core.controller.CombatLogicController;
import de.chaos_butterfly.sm_combat.model.combat.CombatCharacterSM;

/**
 * @author Alti
 *
 */

public class TickEffectCharacterActs implements TickEffect {

	private int tick;
	private CombatCharacterSM character;

	@Inject
	private CombatLogicController combatLogicController;

	public TickEffectCharacterActs(int tick, CombatCharacterSM character) {
		super();
		this.tick = tick;
		this.character = character;
	}

	@Override
	public Object execute() {
		return combatLogicController.execute(this);
	}

	public String getName() {
		return character.getCharacter().getName() + " acts at " + tick;
	}

	public int getTick() {
		return tick;
	}

	public void setTick(int tick) {
		this.tick = tick;
	}

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
