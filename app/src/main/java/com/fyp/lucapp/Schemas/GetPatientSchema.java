package com.fyp.lucapp.Schemas;

public class GetPatientSchema {

    private String email;

    public GetPatientSchema(){}

    public GetPatientSchema(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
