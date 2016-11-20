/**
 * 
 */
package de.chaosbutterfly.smcombat.core.ticks;

import javax.inject.Inject;

import de.chaosbutterfly.smcombat.core.controller.CombatLogicController;
import de.chaosbutterfly.smcombat.model.combat.CombatCharacterSM;

/**
 * @author Alti
 *
 */

public class TickEffectCharacterActs implements TickEffect {

	private int tick;

	private CombatCharacterSM character;

	private CombatLogicController combatLogicController;

	@Inject
    public TickEffectCharacterActs(CombatLogicController combatLogicController) {
        super();
		this.combatLogicController = combatLogicController;
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
