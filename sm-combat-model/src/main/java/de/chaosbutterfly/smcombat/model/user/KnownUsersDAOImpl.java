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
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * @author alters
 *
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class KnownUsersDAOImpl implements KnownUserDAO {
    private static final Logger LOGGER = Logger.getLogger(KnownUsersDAOImpl.class.getName());

    @PersistenceContext(unitName = "sm-combat-model")
    private EntityManager em;

    private KnownUsersDAOImpl() {
        super();
    }

    @PostConstruct
    private void addTestUsers() {
        //gets called multiple time, only add if not present
        if (loadUser("Test") == null) {
            String testUserName = "Test";
            String password = "passw0rd";
            KnownUser testUser = new KnownUser();
            testUser.setUserName(testUserName);
            testUser.setPassword(password);
            addUser(testUser);
        }
        if (loadUser("Admin") == null) {
            String adminUserName = "Admin";
            String adminPassword = "passw0rd";
            KnownUser adminUser = new KnownUser();
            adminUser.setUserName(adminUserName);
            adminUser.setPassword(adminPassword);
            adminUser.setIsAdmin(true);
            addUser(adminUser);
        }
    }

    @Override
    public KnownUser addUser(KnownUser newUser) {
        em.persist(newUser);
        LOGGER.info("User added: " + newUser);
        return newUser;//TODO will this return with id set?
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

    @Override
    public KnownUser loadUser(String userName) {
        TypedQuery<KnownUser> query = em.createNamedQuery(UserQuerys.NAME_QUERY_LOAD_USER_BY_USERNAME, KnownUser.class);
        query.setParameter("userName", userName);
        KnownUser result = null;
        try {
            result = query.getSingleResult();
        } catch (NoResultException | NonUniqueResultException e) {
            LOGGER.log(Level.SEVERE, "Exception in loading user:" + userName + ":" + e.getLocalizedMessage());
        }
        return result;
    }

    @Override
    public boolean removeUser(KnownUser userToDelete) {
        em.remove(userToDelete);
        LOGGER.info("User removed: " + userToDelete);
        return true;
    }

    @Override
    public void saveUser(KnownUser user) {
        em.merge(user);
        LOGGER.info("User saved: " + user);
    }

    @Override
    public KnownUser loadUser(long oldUserId) {
        return em.find(KnownUser.class, oldUserId);
    }

    @Override
    public boolean removeUser(long userId) {
        KnownUser oldUser = loadUser(userId);
        em.remove(oldUser);
        return true;
    }
}
