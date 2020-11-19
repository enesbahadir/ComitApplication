package com.comit.payload;

import java.util.Set;

public class UserForm {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public Set<String> getRole() {
        return role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }

    public UserForm() {
    }

    public UserForm(String username, String password, String name, String surName, Set<String> role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surName = surName;
        this.role = role;
    }

    private String username;

    private String password;

    private String name;

    private String surName;

    private Set<String> role;
}
