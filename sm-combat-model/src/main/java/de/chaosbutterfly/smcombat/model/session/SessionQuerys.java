/**
 * 
 */
package de.chaosbutterfly.smcombat.model.session;

/**
 * @author alters
 *
 */
public class SessionQuerys {
    public static final String NAME_QUERY_LOAD_USER_SESSION_BY_USERNAME = "LoadUserSessionByUserName";
    public static final String QUERY_LOAD_USER_SESSION_BY_USERNAME = "SELECT u from UserSession u WHERE u.userName = :userName";

    public static final String NAME_QUERY_LOAD_GAME_SESSION_BY_GM_NAME = "LoadGameSessionByGmName";
    public static final String QUERY_LOAD_GAME_SESSION_BY_GM_NAME = "SELECT u from SMCombatSession u WHERE u.gmUserName = :gmUserName";
}
