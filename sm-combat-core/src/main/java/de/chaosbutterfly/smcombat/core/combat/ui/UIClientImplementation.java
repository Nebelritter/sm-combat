/**
 * 
 */
package de.chaosbutterfly.smcombat.core.combat.ui;

import de.chaosbutterfly.smcombat.core.combat.commands.CharacterActsCommand;
import de.chaosbutterfly.smcombat.model.combat.CombatCharacterSM;

/**
 * @author Alti
 *
 */
public interface UIClientImplementation {

    public CharacterActsCommand pullCharacterActCommand(CombatCharacterSM character);

}
