package be.projecttycoon.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * Created by thomas on 11/02/16.
 */
public enum SecurityAuths {
    UNREGISTERED(new SimpleGrantedAuthority("UNREGISTERED")),
    TEAM(new SimpleGrantedAuthority("TEAM")),
    ADMIN(new SimpleGrantedAuthority("ADMIN"));

    private final GrantedAuthority authority;

    SecurityAuths(GrantedAuthority authority){
        this.authority = authority;
    }

    public GrantedAuthority getGrantedAuthority(){
        return authority;
    }

    @Override
    public String toString(){
        return authority.getAuthority();
    }



}
