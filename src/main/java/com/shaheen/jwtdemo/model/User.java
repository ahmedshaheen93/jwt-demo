package com.shaheen.jwtdemo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;
@Entity
@Table(name = "USERS")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID", unique = true, nullable = false)
    private Long userId;
    /**
     * user first name with string type has a column representation as FIRST_NAME at
     * table on database
     */
    @NotNull
    @NotEmpty
    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;
    /**
     * user last name with string type has a column representation as LAST_NAME at
     * table on database
     */
    @NotNull
    @NotEmpty
    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;
    /**
     * user phone with string type has a column representation as PHONE at table on
     * database
     */
    @NotNull
    @NotEmpty
    @Column(name = "PHONE", nullable = false)
    private String phone;
    /**
     * user email with string type has a column representation as EMAIL at table on
     * database
     */
    @NotNull
    @NotEmpty
    @Column(name = "EMAIL", unique = true, updatable = false, nullable = false)
    private String email;
    /**
     * user password with string type and should be encrypted and not views for the
     * upper com.iti.model has a column representation as PASSWORD at table on
     * database
     */
    @JsonIgnore
    @NotNull
    @NotEmpty
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    public User() {
    }

    public User(@NotNull @NotEmpty String firstName, @NotNull @NotEmpty String lastName, @NotNull @NotEmpty String phone, @NotNull @NotEmpty String email, @NotNull @NotEmpty String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return email;
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
}
