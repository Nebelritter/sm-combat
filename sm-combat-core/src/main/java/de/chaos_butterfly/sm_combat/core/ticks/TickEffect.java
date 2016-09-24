/**
 * 
 */
package de.chaos_butterfly.sm_combat.core.ticks;

/**
 * @author Alti
 *
 */
public interface TickEffect {

	public int getTick();

	public String getName();

	public Object execute();

}
