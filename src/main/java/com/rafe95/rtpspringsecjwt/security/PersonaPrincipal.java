package com.rafe95.rtpspringsecjwt.security;

import com.rafe95.rtpspringsecjwt.model.Persona;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PersonaPrincipal implements UserDetails {

    private Persona persona;

    public PersonaPrincipal(Persona persona) {
        this.persona = persona;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        // Extract list of permissions (name)
        this.persona.getPermissionList().forEach(perm -> {
            GrantedAuthority authority = new SimpleGrantedAuthority(perm);
            authorities.add(authority);
        });
        // Extract list of roles (ROLE_name)
        this.persona.getRoleList().forEach(r -> {
            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + r);
            authorities.add(authority);
        });
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.persona.getPassword();
    }

    @Override
    public String getUsername() {
        return this.persona.getUsername();
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
        return this.persona.isActive();
    }

    public Persona getPersona(){
        return this.persona;
    }
}
