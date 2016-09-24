package de.chaos_butterfly.sm_combat.core.ui;

import javax.ejb.Stateless;

import de.chaos_butterfly.sm_combat.core.commands.CharacterActsCommand;
import de.chaos_butterfly.sm_combat.model.combat.CombatCharacterSM;

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
