package de.chaosbutterfly.smcombat.core.ui;

import javax.ejb.Stateless;

import de.chaosbutterfly.smcombat.core.commands.CharacterActsCommand;
import de.chaosbutterfly.smcombat.model.combat.CombatCharacterSM;

/**
 * 
 * should call UI and provide
 * 
 * @author Alti
 *
 */

@Stateless
public class UIGateway {

	public CharacterActsCommand getCharacterActCommand(CombatCharacterSM character) {
		// TODO Auto-generated method stub
		return null;
	}

}
