package com.turtles.sport_news.entities;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
public class Role {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    @NotEmpty
    private String name;

    @ManyToMany
    @JoinTable(
            name = "role_privilege",
            joinColumns = {@JoinColumn(name = "Role_id")},
            inverseJoinColumns = {@JoinColumn(name = "Privilege_id")}
    )
    private List<Privilege> privileges;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Privilege> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(List<Privilege> privileges) {
        this.privileges = privileges;
    }
}
