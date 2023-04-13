package com.fyp.lucapp.Views.Registration;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.fyp.lucapp.Components.ComponentCustomDialogue;
import com.fyp.lucapp.Helper.URL;
import com.fyp.lucapp.R;

public class RegistrationActivity extends AppCompatActivity {

    private TextView txtFirstName;
    private TextView txtLastName;
    private TextView txtEmail;
    private TextView txtPassword;
    private TextView txtConfirmPassword;
    private Button nextBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_patient);


        //Initialize
        txtFirstName = findViewById(R.id.firstName);
        txtLastName = findViewById(R.id.lastName);
        txtEmail = findViewById(R.id.email);
        txtPassword = findViewById(R.id.password);
        txtConfirmPassword = findViewById(R.id.confirmPassword);
        nextBtn = findViewById(R.id.nextButton);

        nextBtn.setEnabled(false);
        txtFirstName.requestFocus();
        txtConfirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkNextButtonState();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        //check if the password and confirm password are the same
        nextBtn.setOnClickListener(v -> {
            //validate the text fields
            if (txtFirstName.getText().toString().isEmpty()) {
                txtFirstName.setError("First name is required");
            } else if (txtLastName.getText().toString().isEmpty()) {
                txtLastName.setError("Last name is required");
            } else if (txtEmail.getText().toString().isEmpty()) {
                txtEmail.setError("Email is required");
            } else if (txtPassword.getText().toString().isEmpty()) {
                txtPassword.setError("Password is required");
            } else if (txtConfirmPassword.getText().toString().isEmpty()) {
                txtConfirmPassword.setError("Confirm password is required");
            } else if (txtPassword.getText().toString().
                    equals(txtConfirmPassword.getText().toString())) {

                Intent intent = new Intent(this, RegisterPatient.class);
                intent.putExtra("firstName", txtFirstName.getText().toString());
                intent.putExtra("lastName", txtLastName.getText().toString());
                intent.putExtra("email", txtEmail.getText().toString());
                intent.putExtra("password", txtPassword.getText().toString());
                startActivity(intent);


            } else {
                //if the password and confirm password are not the same, then show an error message
                ComponentCustomDialogue componentCustomDialogue = new ComponentCustomDialogue(this,
                        "Error", "Password and confirm password are not the same"
                        , R.raw.cancel_animation);
                componentCustomDialogue.onShow();
            }
        });


    }

    public void checkNextButtonState() {
        boolean isFirstNameEmpty = txtFirstName.getText().toString().isEmpty();
        boolean isFirstNameCharacters = txtFirstName.getText().toString().matches("[a-zA-Z]+");


        boolean isLastNameEmpty = txtLastName.getText().toString().isEmpty();
        boolean isLastNameCharacters = txtLastName.getText().toString().matches("[a-zA-Z]+");

        boolean isEmailEmpty = txtEmail.getText().toString().isEmpty();

        boolean isEmailValid = txtEmail.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+");

        boolean isPasswordEmpty = txtPassword.getText().toString().isEmpty();

        boolean isConfirmPasswordEmpty = txtConfirmPassword.getText().toString().isEmpty();

        nextBtn.setEnabled(!isFirstNameEmpty && isFirstNameCharacters && !isLastNameEmpty &&
                isLastNameCharacters && !isEmailEmpty &&
                isEmailValid && !isPasswordEmpty && !isConfirmPasswordEmpty);

    }


}
