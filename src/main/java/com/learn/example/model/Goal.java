package com.learn.example.model;

import com.learn.example.model.User;

import java.io.Serializable;
import java.util.Set;


import javax.persistence.*;

@Entity
@Table(name = "goal")

public class Goal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToOne
    private User user;

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

    public Goal() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Goal(String name, User user) {
        this.user = user;
        this.name = name;
    }
}
