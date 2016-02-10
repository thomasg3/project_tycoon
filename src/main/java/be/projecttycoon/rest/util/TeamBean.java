package be.projecttycoon.rest.util;

/**
 * Created by thomas on 10/02/16.
 */
public class TeamBean {
    private String oldUsername;
    private String oldPassword;
    private String newUsername;
    private String newPassword;

    public TeamBean(){}

    public TeamBean(String oldUsername, String oldPassword, String newUsername, String newPassword){
        this.oldUsername = oldUsername;
        this.oldPassword = oldPassword;
        this.newUsername = newUsername;
        this.newPassword = newPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewUsername() {
        return newUsername;
    }

    public void setNewUsername(String newUsername) {
        this.newUsername = newUsername;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getOldUsername() {
        return oldUsername;
    }

    public void setOldUsername(String oldUsername) {
        this.oldUsername = oldUsername;
    }
}
