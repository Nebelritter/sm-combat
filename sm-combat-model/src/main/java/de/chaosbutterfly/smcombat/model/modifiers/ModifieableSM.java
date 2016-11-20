/**
 * 
 */
package de.chaosbutterfly.smcombat.model.modifiers;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author Stefan Alter
 *
 */

public class ModifieableSM {


	List<ModifierSM> modifiers = new ArrayList<ModifierSM>();

	boolean addModifier(ModifierSM modifier) {
		return modifiers.add(modifier);
	}

	boolean removeModifier(ModifierSM modifier) {
		return modifiers.add(modifier);
	}

	List<ModifierSM> getAllModifiers() {
		return new ArrayList<ModifierSM>(modifiers);
	}

	List<ModifierSM> getAllModifiersByType(final String type) {
		Predicate<ModifierSM> filter = mod -> !mod.hasType(type); // lambda predicate takes argument and returns
																	// boolean, works because type is final
		ArrayList<ModifierSM> typedModifiers = new ArrayList<ModifierSM>(modifiers);
		typedModifiers.removeIf(filter);
		return typedModifiers;
	}
}
