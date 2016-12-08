/**
 * 
 */
package de.chaosbutterfly.smcombat.model.session;

import java.time.Instant;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import de.chaosbutterfly.smcombat.model.character.CharacterSM;
import de.chaosbutterfly.smcombat.model.combat.CombatCharacterSM;
import de.chaosbutterfly.smcombat.model.user.KnownUser;

/**
 * @author alters
 *
 */
@Stateless
public class SessionsDAOImpl implements SessionsDAO {

    private static final Logger LOGGER = Logger.getLogger(SessionsDAOImpl.class.getName());

    @PersistenceContext(unitName = "sm-combat-model")
    private EntityManager em;

    private SessionsDAOImpl() {
        super();
    }

    public UserSession isAlreadyLoggedIn(String userName) {
        TypedQuery<UserSession> query = em.createNamedQuery(SessionQuerys.NAME_QUERY_LOAD_USER_SESSION_BY_USERNAME,
                UserSession.class);
        query.setParameter("userName", userName);
        UserSession result = null;
        try {
            result = query.getSingleResult();
        } catch (NoResultException e) {
            //do nothing as no result might be expected
            LOGGER.log(Level.FINEST, "user session is not existing yet for user:" + userName);
        } catch (NonUniqueResultException e) {
            LOGGER.log(Level.WARNING, "Exception in loading user session:" + userName);
        }
        return result;
    }

    @Override
    public UserSession createNewUserSession(KnownUser user) {
        UserSession newSession = new UserSession();
        newSession.setUserName(user.getUserName());
        Instant established = Instant.now();
        newSession.setEstablished(established);
        newSession.setIsAdmin(user.getIsAdmin());
        em.persist(newSession);
        return newSession;
    }

    @Override
    public boolean logOut(UserSession session) {
        UserSession alreadySession = isAlreadyLoggedIn(session.getUserName());
        em.remove(alreadySession);
        LOGGER.log(Level.INFO, "User logged out:" + alreadySession.getUserName());
        return true;
    }

    @Override
    public SMCombatSession openNewGameSession(String gmUserName) {
        SMCombatSession gameSession = new SMCombatSession();
        gameSession.setGmUserName(gmUserName);
        em.persist(gameSession);
        return gameSession;
    }


    @Override
    public SMCombatSession checkGameSessionActive(String gmUserName) {
        TypedQuery<SMCombatSession> query = em.createNamedQuery(SessionQuerys.NAME_QUERY_LOAD_GAME_SESSION_BY_GM_NAME,
                SMCombatSession.class);
        query.setParameter("gmUserName", gmUserName);
        SMCombatSession result = null;
        try {
            result = query.getSingleResult();
        } catch (NoResultException | NonUniqueResultException e) {
            LOGGER.log(Level.WARNING, "Exception in loading game session:" + gmUserName);
        }
        return result;
    }

    @Override
    public boolean addCharacterToGameSession(SMCombatSession gameSession, UserSession userSession, CharacterSM character) {
        SMCombatSession existingSession = checkGameSessionActive(gameSession.getGmUserName());
        if (existingSession == null) {
            return false;
        }
        //check if character already exists in that session
        List<CombatCharacterSM> charactersInSession = existingSession.getCharactersByUser(userSession);
        CombatCharacterSM existingCharacter = getExistingCharacter(charactersInSession, character);
        if (existingCharacter == null) {
            CombatCharacterSM newCombatCharacter = new CombatCharacterSM(character);
            boolean result = existingSession.addCharacter4User(userSession, newCombatCharacter);
            em.merge(existingSession);
            return result;
        } else {
            return false;
        }
    }

    private CombatCharacterSM getExistingCharacter(List<CombatCharacterSM> charactersInSession, CharacterSM character) {
        if (charactersInSession == null) {
            return null;
        }
        for (CombatCharacterSM combatCharacterSM : charactersInSession) {
            if (character.equals(combatCharacterSM.getCharacter())) {
                return combatCharacterSM;
            }
        }
        return null;
    }

    @Override
    public void removeCharacterFromGameSession(SMCombatSession gameSession) {
        //TODO implement
    }

}
