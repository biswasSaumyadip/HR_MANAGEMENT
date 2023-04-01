package com.human_resource.hr_management.v1.Security;

import com.human_resource.hr_management.v1.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {


    private String user_id;

    private String username;
    private String user_email;
    private String user_password;


    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(String user_id, String username, String user_email, String user_password, Collection<? extends GrantedAuthority> authorities) {
        this.user_id = user_id;
        this.username = username;
        this.user_email = user_email;
        this.user_password = user_password;

        if (authorities == null) {
            this.authorities = null;
        } else {
            this.authorities = new ArrayList<>(authorities);
        }

    }

    public static UserPrincipal create(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>(); //TODO: to be taken care later
        return new UserPrincipal(user.getUser_id(), user.getUsername(), user.getUser_email(), user.getUser_password(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities == null? null : new ArrayList<>(authorities);
    }

    public String getUser_id() {
        return user_id;
    }

    public String getUser_email() {
        return user_email;
    }

    @Override
    public String getPassword() {
        return user_password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
