/**
 * 
 */
package de.chaosbutterfly.smcombat.core.ticks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Alti
 *
 */

public class TickCounter {

	private Map<Integer, List<TickEffect>> tickPositions;

	private int currentTick;

	public TickCounter() {
		super();
		tickPositions = new HashMap<Integer, List<TickEffect>>();
	}

	/**
	 * advances the ticks by one
	 * 
	 * @return the CharacterList of the characters that are due to doing
	 *         something null - if nothing happens
	 * 
	 */
	public List<TickEffect> advanceTickByOne() {
		// tidy up last tick
		// remove tick and list from map to free some mem
		tickPositions.remove(currentTick);
		// increase current tick
		currentTick++;
		// examine map for new character action possibilities
		List<TickEffect> charactersThatCanAct = tickPositions.get(currentTick);
		return charactersThatCanAct;
	}

	public boolean addCharacterAtTick(TickEffect character, int tick) {
		List<TickEffect> list = tickPositions.get(tick);
		if (list == null) {
			list = new ArrayList<TickEffect>();
			tickPositions.put(tick, list);
		}
		return list.add(character);
	}

	public boolean removeCharacterAtTick(TickEffect character, int tick) {
		List<TickEffect> list = tickPositions.get(tick);
		if (list == null) {
			return false; // no list means character is not contained, so not
							// found at this position
		}
		return list.remove(character);
	}
}
