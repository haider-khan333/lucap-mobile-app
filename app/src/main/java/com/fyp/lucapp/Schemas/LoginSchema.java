package com.fyp.lucapp.Schemas;

public class LoginSchema {
    public String email;
    public String password;

    public LoginSchema(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginSchema{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
