/**
 * 
 */
package de.chaosbutterfly.smcombat.model.user;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 * @author alters
 *
 */
@Entity
@NamedQuery(name = UserQuerys.NAME_QUERY_LOAD_USER_BY_USERNAME, query = UserQuerys.QUERY_LOAD_USER_BY_USERNAME)
public class KnownUser {

    @Id
    private long id;

    private String userName;
    private String password;

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     *            the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     *            the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }


}
