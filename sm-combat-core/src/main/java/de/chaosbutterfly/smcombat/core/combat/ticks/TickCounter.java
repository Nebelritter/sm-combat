/**
 * 
 */
package de.chaosbutterfly.smcombat.core.combat.ticks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.chaosbutterfly.smcombat.core.combat.ticks.effects.TickEffect;

/**
 * @author Alti
 *
 */
public class TickCounter {

    private Map<Integer, List<TickEffect>> tickPositions;

    private int currentTick;

    public TickCounter() {
        super();
        tickPositions = new HashMap<>();
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
        // remove tick and list from map to free some memory
        tickPositions.remove(currentTick);
        // increase current tick
        currentTick++;
        // examine map for new character action possibilities
        return tickPositions.get(currentTick);
    }

    public boolean addTickEffectAtTick(TickEffect effect, int tick) {
        List<TickEffect> list = tickPositions.get(tick);
        if (list == null) {
            list = new ArrayList<>();
            tickPositions.put(tick, list);
        }
        return list.add(effect);
    }

    public boolean removeTickEffectAtTick(TickEffect effect, int tick) {
        List<TickEffect> list = tickPositions.get(tick);
        if (list == null) {
            return false; // no list means character is not contained, so not
                          // found at this position
        }
        return list.remove(effect);
    }
}
