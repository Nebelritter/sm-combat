/**
 * 
 */
package de.chaosbutterfly.smcombat.model.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
    @GeneratedValue
    private long id;

    private String userName;
    private String password;

    private Boolean isAdmin;


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

    /**
     * @return the isAdmin
     */
    public Boolean getIsAdmin() {
        return isAdmin;
    }

    /**
     * @param isAdmin
     *            the isAdmin to set
     */
    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "KnownUser [id=" + id + ", userName=" + userName + ", password=" + password + ", isAdmin=" + isAdmin
                + "]";
    }

}
