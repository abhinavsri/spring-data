package com.learn.example.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@NamedQuery(name = "User.findByFirstname", query = "SELECT user FROM User user WHERE user.firstName = ?1")
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;
    private String username;
    private String password;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "user")
    private Set<Goal> goalSet;


    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<Goal> getGoalSet() {
        return goalSet;
    }

    public void setGoalSet(Set<Goal> goalSet) {
        this.goalSet = goalSet;
    }

    public User(String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User() {
    }
}