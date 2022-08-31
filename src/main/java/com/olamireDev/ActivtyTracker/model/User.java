package com.olamireDev.ActivtyTracker.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;


@Entity(name = "users")
@Data
@Getter @Setter @NoArgsConstructor
public class User{

    @Id
    @Column(name ="user_id")
    private long userId;

    @Column(name ="firstname")
    private String firstname;
    
    @Column(name ="lastname")
    private String lastname;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Transient
    private String passConfirm;

    public User(long id, String firstname, String lastname, String email, String password){
        this.userId = id;
        this.firstname =firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

    public User(String firstname, String lastname, String email, String password){
        this.firstname =firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

    public User(long userId, String firstname){
        this.userId= userId;
        this.firstname =firstname;
    }

}