package com.comit.model;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UserForm() {
    }

    public UserForm(String username, String password, String name, String surName, String type) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surName = surName;
        this.type = type;
    }

    private String username;

    private String password;

    private String name;

    private String surName;

    private String type;
}
