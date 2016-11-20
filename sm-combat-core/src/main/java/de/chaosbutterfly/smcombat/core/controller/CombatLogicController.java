/**
 * 
 */
package de.chaosbutterfly.smcombat.core.controller;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import de.chaosbutterfly.smcombat.core.commands.CharacterActsCommand;
import de.chaosbutterfly.smcombat.core.commands.CharacterActsCommandAttack;
import de.chaosbutterfly.smcombat.core.ticks.TickCounter;
import de.chaosbutterfly.smcombat.core.ticks.TickEffect;
import de.chaosbutterfly.smcombat.core.ticks.TickEffectCharacterActs;
import de.chaosbutterfly.smcombat.core.ui.UIGateway;

/**
 * @author Alti
 *
 */
@Stateless
public class CombatLogicController {

    private UIGateway uiGateway;

    private TickCounter tickCounter;

    boolean combatRunning = false;

    /**
     * only for EJB spec
     */
    public CombatLogicController() {
        super();
    }

    /**
     * @param uiGateway
     * @param tickCounter
     */
    @Inject
    public CombatLogicController(UIGateway uiGateway, TickCounter tickCounter) {
        super();
        this.uiGateway = uiGateway;
        this.tickCounter = tickCounter;
    }

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
