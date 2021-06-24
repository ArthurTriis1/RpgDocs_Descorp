package com.descorp.rpgdocs.beans;


public class SignInBean {
    
    private String email;
    private Integer password;

    public SignInBean() {
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPassword() {
        return password;
    }

    public void setPassword(Integer password) {
        this.password = password;
    } 
}
