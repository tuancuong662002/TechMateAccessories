package org.example.techmateaccessories.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "roles")
public class Role{
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private long id ;
     private String name ;
     private String description ;
     @OneToMany(mappedBy = "role")
     private List<User> users ;
     public String getName() {
        return name;
     }

    public List<User> getUsers() {
        return users;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
