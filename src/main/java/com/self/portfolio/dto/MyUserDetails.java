package com.self.portfolio.dto;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.self.portfolio.entity.UsersAuth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	private String userName;
    private String password;
    private Boolean active;
    private List<GrantedAuthority> roles;

    public MyUserDetails(UsersAuth userAuth) {
        System.out.println(userAuth.toString());
        this.userName = userAuth.getUserName();
        this.password = userAuth.getPassword();
        this.active = userAuth.getActive();
        this.roles = Arrays.stream(userAuth.getRoles().split(",")).map(role -> {
            role = "ROLE_" + role;
            return new SimpleGrantedAuthority(role);
        }).collect(Collectors.toList());
        System.out.println(this.getAuthorities());

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }

}