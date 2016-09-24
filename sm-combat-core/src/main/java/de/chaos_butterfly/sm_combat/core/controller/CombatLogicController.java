/**
 * 
 */
package de.chaos_butterfly.sm_combat.core.controller;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import de.chaos_butterfly.sm_combat.core.commands.CharacterActsCommand;
import de.chaos_butterfly.sm_combat.core.commands.CharacterActsCommandAttack;
import de.chaos_butterfly.sm_combat.core.ticks.TickCounter;
import de.chaos_butterfly.sm_combat.core.ticks.TickEffect;
import de.chaos_butterfly.sm_combat.core.ticks.TickEffectCharacterActs;
import de.chaos_butterfly.sm_combat.core.ui.UIGateway;

/**
 * @author Alti
 *
 */
@Stateless
public class CombatLogicController {

	@Inject
	private UIGateway uiGateway;

	@Inject
	private TickCounter tickCounter;

	boolean combatRunning = false;

	public Object execute(TickEffectCharacterActs tickEffectCharacterActs) {
		// ask UI for command
		CharacterActsCommand command = uiGateway.getCharacterActCommand(tickEffectCharacterActs.getCharacter());
		// execute command from UI
		return applyCommand(command);
	}

	private Object applyCommand(CharacterActsCommand command) {
		// should only be called for unknown command types
		return null;
	}

	private Object applyCommand(CharacterActsCommandAttack command) {
		// handle attack
		// shouldn't this be called if a correct command is returned? If not use
		// instance of in execute of TickEffectCharacterActs
		return null;
	}

	public void startCombatLoopIfAllSet() {
		while (combatRunning) {
			List<TickEffect> effects = tickCounter.advanceTickByOne();
			if (effects != null && !effects.isEmpty()) {
				for (TickEffect tickEffect : effects) {
					tickEffect.execute();
				}
			}
		}
	}
}
