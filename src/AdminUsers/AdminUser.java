package AdminUsers;

import Data.TransitData;
import TransitUsers.CardHolder;

import java.io.Serializable;

/** This admin is used to analyze the buisness statistics
 *
 * This class is also responsible for a daily report for the governement to analyze the TTC statistics */
public class AdminUser implements Serializable {

    private static final long serialVersionUID = 364732;
    private String name;
    private String email;
    private String password;
    private String bannedPassword = "101x000";

    /**
     * constructs a new instance of AdminUser
     */
    public AdminUser (String name, String email) {
        this.name = name;
        this.email = email;
    }

    /**
     * Creates Admin User
     * @param name name of Admin User
     * @param email email of Admin User
     * @param password password of Admin User
     */
    public AdminUser (String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    /**
     * Returns Admin User email
     * @return Admin User email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns Admin User Name
     * @return Admin User Name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns Admin User Password
     * @return Admin User Password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets Admin User Email
     * @param newemail new Admin User Email
     */
    public void setEmail(String newemail) {
        email = newemail;
    }

    /**
     * Set Admin User Name
     * @param newname
     */
    public void setName(String newname) {
        email = newname;
    }

    /**
     * Set Password for Admin User
     * @param newpassword new password for Admin User
     */
    public void setPassword(String newpassword) {
        password = newpassword;
    }

    /**
     * Checks if given password is correct
     * @param pass the password to be checked
     * @return returns whether or not the given password is correct
     */
    public boolean isPassCorrect(String pass){
        if (this.password != null) {
            return (this.password.equals(pass)); }
        return false;
    }

    /**
     * Bans given user
     * @param ch user to be banned
     */
    public void banUser(CardHolder ch){
        ch.banCardHolder(this.bannedPassword);
    }

    /**
     * unbans given user
     * @param ch user to be unbanned
     */
    public void unBanUser(CardHolder ch){
        ch.unBanCardHolder();
    }
}

