package com.turtles.sport_news.entity;

import javax.persistence.*;
import java.util.List;

@Entity(name = "UserAccount")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private boolean enabled;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name = "User_id")},
            inverseJoinColumns = {@JoinColumn(name = "Role_id")}
    )
    private List<Role> roles;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String xsurname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
