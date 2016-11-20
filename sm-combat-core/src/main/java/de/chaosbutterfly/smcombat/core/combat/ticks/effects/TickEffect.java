/**
 * 
 */
package de.chaosbutterfly.smcombat.core.combat.ticks.effects;

/**
 * @author Alti
 *
 */
public interface TickEffect {

	public int getTick();

	public String getName();

	public Object execute();

}
