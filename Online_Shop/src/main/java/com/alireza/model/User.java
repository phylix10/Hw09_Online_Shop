package com.alireza.model;

import com.alireza.model.enumeration.Role;

public class User {
    private int id;
    private String username;
    private String password;
    private String nationalCode;
    private Role role;

    public User(String username, String password, String nationalCode) {
        this.username = username;
        this.password = password;
        this.nationalCode = nationalCode;
    }

    public User(String username) {
        this.username = username;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
