/**
 * 
 */
package de.chaosbutterfly.smcombat.model.modifiers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Stefan Alter
 *
 */
public class ModifierSM {

    private String text;
    private int value;
    private Set<String> types;

    /**
     * modifiers that are not active are for information purposes only
     */
    private boolean active;

    public ModifierSM(String text, int value, List<String> types, boolean active) {
        super();
    }

    public boolean hasType(String type) {
        return types.contains(type);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Set<String> getTypes() {
        return new HashSet<>(types);
    }

    public boolean addType(String type) {
        return types.add(type);
    }

    public boolean removeType(String type) {
        return types.remove(type);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
