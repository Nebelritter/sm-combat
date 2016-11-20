/**
 * 
 */
package de.chaosbutterfly.smcombat.model.modifiers;

import java.util.List;

/**
 * @author Stefan Alter
 *
 */
public interface IModifieable {

	List<ModifierSM> getAllModifiers();

	List<ModifierSM> getAllModifiersByType(final String type);
}
