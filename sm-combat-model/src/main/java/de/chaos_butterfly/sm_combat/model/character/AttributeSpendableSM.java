/**
 * 
 */
package de.chaos_butterfly.sm_combat.model.character;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Stefan Alter
 *         for things like LP and Fokus (and Splitterpunkte)
 */
public class AttributeSpendableSM extends AttributeSM {
	
	private List<SpendableLevelDescriptor> levels;
	private int pointsPerLevel;

	private int channeled;
	private int spent;
	private int consumed;

	public AttributeSpendableSM() {
		super();
		levels = new ArrayList<SpendableLevelDescriptor>();
	}

	public AttributeSpendableSM(List<SpendableLevelDescriptor> levels, int pointsPerLevel) {
		super();
		this.levels = levels;
		this.pointsPerLevel = pointsPerLevel;
	}

	/**
	 * calculates the level descriptors reached by adding up channeled,spent and consumed
	 * 
	 * @return all reached levels
	 */
	private List<SpendableLevelDescriptor> calcLevels() {
		int allCombined = channeled + spent + consumed;
		int numberOfSteps = allCombined / pointsPerLevel;
		List<SpendableLevelDescriptor> result = new ArrayList<SpendableLevelDescriptor>(
				levels.subList(0, numberOfSteps));
		return result;
	}

	public List<SpendableLevelDescriptor> getLevels() {
		return new ArrayList<SpendableLevelDescriptor>(levels);
	}

	public List<SpendableLevelDescriptor> setLevels(List<SpendableLevelDescriptor> levels) {
		this.levels = levels;
		return calcLevels();
	}

	public int getPointsPerLevel() {
		return pointsPerLevel;
	}

	public List<SpendableLevelDescriptor> setPointsPerLevel(int pointsPerLevel) {
		this.pointsPerLevel = pointsPerLevel;
		return calcLevels();
	}

	public int getChanneled() {
		return channeled;
	}

	public List<SpendableLevelDescriptor> addChanneled(int channeled) {
		this.channeled = channeled;
		return calcLevels();
	}


	/**
	 * @param channeled
	 * @return
	 *         automatically adds to spent
	 */
	public List<SpendableLevelDescriptor> removeChanneled(int channeled) {
		this.channeled -= channeled;
		addSpent(channeled);
		return calcLevels();
	}

	public int getSpent() {
		return spent;
	}

	public List<SpendableLevelDescriptor> addSpent(int spent) {
		this.spent = spent;
		return calcLevels();
	}

	public List<SpendableLevelDescriptor> removeSpent(int spent) {
		this.spent = spent;
		return calcLevels();
	}

	public int getConsumed() {
		return consumed;
	}

	public List<SpendableLevelDescriptor> addConsumed(int consumed) {
		this.consumed = consumed;
		return calcLevels();
	}

	public List<SpendableLevelDescriptor> removeConsumed(int consumed) {
		this.consumed = consumed;
		return calcLevels();
	}
	
	

}
