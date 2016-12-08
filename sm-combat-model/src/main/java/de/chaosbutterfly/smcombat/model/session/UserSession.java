/**
 * 
 */
package de.chaosbutterfly.smcombat.model.session;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 * @author Alti
 *
 */
@Entity
@NamedQuery(name = SessionQuerys.NAME_QUERY_LOAD_USER_SESSION_BY_USERNAME, query = SessionQuerys.QUERY_LOAD_USER_SESSION_BY_USERNAME)
public class UserSession {

    @Id
    @GeneratedValue
    private long id;

    private String userName;

    private Instant established;

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
     * @return the established
     */
    public Instant getEstablished() {
        return established;
    }

    /**
     * @param established
     *            the established to set
     */
    public void setEstablished(Instant established) {
        this.established = established;
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

}
