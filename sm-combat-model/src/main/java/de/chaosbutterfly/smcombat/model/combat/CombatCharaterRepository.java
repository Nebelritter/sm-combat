/**
 * 
 */
package de.chaosbutterfly.smcombat.model.combat;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.jboss.logging.Logger;

/**
 * @author Alti
 *
 */
@ApplicationScoped
public class CombatCharaterRepository {

    private final static Logger LOGGER = Logger.getLogger(CombatCharaterRepository.class);

    private EntityManager em;

    public CombatCharaterRepository() {
        super();
    }

    /**
     * @param log
     * @param em
     */
    @Inject
    public CombatCharaterRepository(EntityManager em) {
        super();
        this.em = em;
    }

    public CombatCharacterSM findById(Long id) {
        LOGGER.debug("Finding combatCharacter by id:" + id);
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
