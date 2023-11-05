package com.isep.acme.model.user;

import com.isep.acme.model.Role;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Id;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Document("user")
public class UserMongo implements UserDetails {

    @Transient
    public static final String USER_SEQUENCE = "userSequence";

    private static final long serialVersionUID = 1L;

    @Id
    private Long userId;

    private String username;

    private String password;

    private String fullName;

    private Set<Role> authorities = new HashSet<>();

    private String nif;

    private String morada;

/*    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> review = new ArrayList<Review>(); */

    protected UserMongo() {}

    public UserMongo(final String username, final String password){
        this.username = username;
        this.password = password;
    }


    public UserMongo(final String username, final String password, final String fullName, final String nif, final String morada) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        setNif(nif);
        this.morada = morada;
    }

    public UserMongo(final Long userId, final String username, final String password, final String fullName, final String nif, final String morada) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        setNif(nif);
        this.morada = morada;
    }

    public void addAuthority(Role r) {
        authorities.add(r);
    }

    public void setNif(String nif) {
        if(nif.length() != 9) {
            throw new IllegalArgumentException("NIF must be 9 characters.");
        }
        this.nif = nif;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
        return true;
    }

    public BaseUser toBaseUser() {
        return new BaseUser(username, password, fullName, nif, morada);
    }
}
