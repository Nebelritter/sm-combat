/**
 * 
 */
package de.chaosbutterfly.smcombat.model.user;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

/**
 * @author alters
 *
 */
@Stateless
public class KnownUsersDAOImpl implements KnownUserDAO {
    private static final Logger LOGGER = Logger.getLogger(KnownUsersDAOImpl.class.getName());

    private EntityManager em;

    @Inject
    private KnownUsersDAOImpl(EntityManager em) {
        super();
        this.em = em;
    }

    @PostConstruct
    private void addTestUser() {
        KnownUser newUser = new KnownUser();
        newUser.setUserName("Test");
        newUser.setPassword("passw0rd");
        addUser(newUser);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public boolean addUser(KnownUser newUser) {
        em.persist(newUser);
        return true;
    }

    @Override
    public boolean isUserValid(String userName, String password) {
        KnownUser userFromDB = loadUser(userName);
        if (userFromDB != null) {
            if (password.equals(userFromDB.getPassword())) {
                return true;
            } else {
                LOGGER.warning("Wrong password provided:" + password);
            }
        }
        LOGGER.info("User not found:" + userName);
        return false;
    }

    private KnownUser loadUser(String userName) {
        TypedQuery<KnownUser> query = em.createNamedQuery(UserQuerys.NAME_QUERY_LOAD_USER_BY_USERNAME, KnownUser.class);
        query.setParameter("userName", userName);
        KnownUser result = null;
        try {
            result = query.getSingleResult();
        } catch (NoResultException | NonUniqueResultException e) {
            LOGGER.log(Level.SEVERE, "Exception in loading user:" + userName);
        }
        return result;
    }

    @Override
    public boolean removeUser(KnownUser userToDelete) {
        em.remove(userToDelete);
        return true;
    }
}
