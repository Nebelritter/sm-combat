/**
 * 
 */
package de.chaosbutterfly.smcombat.core.ticks;

/**
 * @author Alti
 *
 */
public interface TickEffect {

	public int getTick();

	public String getName();

	public Object execute();

}
