package be.projecttycoon.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Collection;

/**
 * Created by thomas on 09/02/16.
 */
@MappedSuperclass
public class User {

    @Id
    @GeneratedValue
    private long id;

    private String username;
    private String password;

    public User() {
    }

    public User(String password, String username) {
        this.password = password;
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
