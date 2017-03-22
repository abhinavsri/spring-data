package com.learn.example.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table( name = "user_table" )
public class User {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private Long id;

    private String username;
    private String password;

    @OneToMany(cascade = {CascadeType.ALL},mappedBy = "user")
    private Set<Car> carSet;


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



    public Set<Car> getCarSet() {
        return carSet;
    }

    public void setCarSet(Set<Car> carSet) {
        this.carSet = carSet;
    }

    public  User(String username, String password){
        this.username=username;
        this.password=password;
    }
	
	public User (){}
}