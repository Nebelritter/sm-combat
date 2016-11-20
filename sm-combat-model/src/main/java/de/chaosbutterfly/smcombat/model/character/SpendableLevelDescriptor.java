package de.chaosbutterfly.smcombat.model.character;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SpendableLevelDescriptor {

    @Id
    @GeneratedValue
    private Long id;

	String name;
	int value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
