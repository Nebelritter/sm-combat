/**
 * 
 */
package de.chaosbutterfly.smcombat.core.data;

import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import de.chaosbutterfly.smcombat.model.combat.CombatCharacterSM;

/**
 * @author Alti
 *
 */
@ApplicationScoped
public class CombatCharaterRepository {

	private Logger log;

	private EntityManager em;

	/**
	 * @param log
	 * @param em
	 */
	@Inject
	public CombatCharaterRepository(Logger log, EntityManager em) {
		super();
		this.log = log;
		this.em = em;
	}

	public CombatCharacterSM findById(Long id) {
		return em.find(CombatCharacterSM.class, id);
	}

	public List<CombatCharacterSM> findAllOrderedByTick() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<CombatCharacterSM> criteria = cb.createQuery(CombatCharacterSM.class);
		Root<CombatCharacterSM> combatCharacter = criteria.from(CombatCharacterSM.class);
		// Swap criteria statements if you would like to try out type-safe
		// criteria queries, a new
		// feature in JPA 2.0
		// criteria.select(member).orderBy(cb.asc(member.get(Member_.name)));
		criteria.select(combatCharacter).orderBy(cb.asc(combatCharacter.get("name")));
		return em.createQuery(criteria).getResultList();
	}
}
