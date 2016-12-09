/**
 * 
 */
package de.chaosbutterfly.smcombat.model.user;

/**
 * @author alters
 *
 */
public class UserQuerys {
    public static final String NAME_QUERY_LOAD_USER_BY_USERNAME = "LoadUserByUserName";
    public static final String QUERY_LOAD_USER_BY_USERNAME = "SELECT u from KnownUser u WHERE u.userName = :userName";

    public static final String NAME_QUERY_LOAD_ALL_USERS = "LoadAllUsers";
    public static final String QUERY_LOAD_ALL_USERS = "SELECT u from KnownUser u";
}
