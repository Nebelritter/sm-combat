/**
 * 
 */
package de.chaos_butterfly.sm_combat.model.character;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Alti
 *
 */
@Entity
@XmlRootElement
@Table(name = "CombatCharacter", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class CombatCharacterSM extends CharacterSM {

	@Id
	@GeneratedValue
	private Long id;

}
