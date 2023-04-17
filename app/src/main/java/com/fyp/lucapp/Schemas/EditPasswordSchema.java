package com.fyp.lucapp.Schemas;

public class EditPasswordSchema {
    private String newPassword;

    public EditPasswordSchema(String newPassword) {
        this.newPassword = newPassword;
    }

    public EditPasswordSchema() {
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
