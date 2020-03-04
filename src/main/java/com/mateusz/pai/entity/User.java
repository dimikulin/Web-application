/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mateusz.pai.entity;

import javax.persistence.Column;
import javax.persistence.Entity; 
import javax.persistence.GeneratedValue; 
import javax.persistence.GenerationType; 
import javax.persistence.Id; 
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
 
@Entity @Table(name = "Users") 
public class User { 
 
    @Id     
    @GeneratedValue(strategy = GenerationType.AUTO)    
    private Integer userid;
    
    @NotNull
    @Size(min=3, message="Min 3 znaki")
    private String name;     
    
    @NotNull
    @Size(min=3, message="Min 3 znaki")
    private String surname;
    
    @NotNull
    @Size(min=5, max=15, message="Min 5, maks. 15 znaków")
    private String login;  
    
    @NotNull
    @Size(min=6, message="Min 6 znaków")
    private String password;
    
    @NotEmpty(message="To pole nie może być puste")
    @Email(message="Niepoprawny format email")
    private String email;
 
    public User() {     } 
 
    public User(String name, String surname, String login, String password, String email) {
        this.name = name;
        this.surname = surname;         
        this.login = login;         
        this.password = password;
        this.email=email;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
}