package AdminUsers;

import AdminUsers.AdminUser;

import java.io.Serializable;
import java.util.ArrayList;

public class AdminUserList implements Serializable {

    private ArrayList<AdminUser> allAdminUsers;

    public AdminUserList(){
        this.allAdminUsers = new ArrayList<>();
    }

    /**
     * Adds a new Admin User.
     *
     * This method checks to see if the user exists and returns
     * false if the user exists already. It returns true otherwise.
     *
     * @param name the name of the new Admin User being added
     * @param email the email of the new Admin User being added
     * @return true if successful, false if fails
     */
    public boolean addAdminUser(String name, String email, String password){
        for (AdminUser au: this.allAdminUsers){
            if (au.getEmail().equals(email)){
                return false;
            }
        }
        this.allAdminUsers.add(new AdminUser(name, email, password));
        return true;
    }

    /**
     * Returns an Admin User if the given email is the email of
     * an existing user. Returns null otherwise.
     *
     * @param email the email of the Admin User we want to find
     * @return admin user with given email
     */
    public AdminUser findAdminUser(String email){
        for (AdminUser au: this.allAdminUsers) {
            if (au.getEmail().equals(email)){
                return au;
            }
        }
        return null;
    }

}
