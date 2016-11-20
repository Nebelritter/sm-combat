/**
 * 
 */
package de.chaosbutterfly.smcombat.model.character;

import javax.persistence.DiscriminatorValue;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import de.chaosbutterfly.smcombat.model.modifiers.ModifieableSM;

/**
 * @author Stefan Alter
 *
 */
@javax.persistence.Entity
@DiscriminatorValue(value = "A")
@Table(name = "T_ATTRIBUTE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class AttributeSM extends ModifieableSM {

    @Id
    @GeneratedValue
    private Long id;

	String name;
	Integer value;
}
