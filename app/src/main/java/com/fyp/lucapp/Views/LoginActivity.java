package com.fyp.lucapp.Views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.fyp.lucapp.Components.CustomDialogue;
import com.fyp.lucapp.Helper.URL;
import com.fyp.lucapp.Interface.LoginCallback;
import com.fyp.lucapp.Main;
import com.fyp.lucapp.BasicModels.Address;
import com.fyp.lucapp.BasicModels.Person;
import com.fyp.lucapp.R;
import com.fyp.lucapp.Schemas.LoginSchema;
import com.fyp.lucapp.Views.Registration.RegistrationActivity;

public class LoginActivity extends AppCompatActivity implements LoginCallback {


    private TextView txtEmail, txtPassword, txtRegister;

    private Button btnLogin;

    private URL url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Initialize
        txtEmail = findViewById(R.id.email);
        txtPassword = findViewById(R.id.password);
        txtRegister = findViewById(R.id.registerLink);
        btnLogin = findViewById(R.id.login);

        url = new URL(this, this);


        //add email validation on text change
        txtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!Patterns.EMAIL_ADDRESS.matcher(s).matches()) {
                    txtEmail.setError("Invalid Email");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        btnLogin.setOnClickListener(v -> {
            String email = txtEmail.getText().toString();
            String password = txtPassword.getText().toString();

            if (email.isEmpty()) {
                txtEmail.setError("Email is required");
            } else if (password.isEmpty()) {
                txtPassword.setError("Password is required");
            } else {
                LoginSchema loginSchema = new LoginSchema(email, password);
                url.loginPatient(loginSchema);
            }


            //get the user from the shared preferences
//            SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
//            String id = sharedPreferences.getString("id", "");
//            String name = sharedPreferences.getString("name", "");
//            String email = sharedPreferences.getString("email", "");
//            String password = sharedPreferences.getString("password", "");
//            String contactNumber = sharedPreferences.getString("contactNumber", "");
//            Person person = new Person(id, name, email, password, contactNumber
//                    , new Address("", "", "", "",
//                    ""));

            //validate the email field
//            if (txtEmail.getText().toString().isEmpty()) {
//                txtEmail.setError("Email is required");
//            } else if (txtPassword.getText().toString().isEmpty()) {
//                txtPassword.setError("Password is required");
//            } else if (isRegistered(person)) {
//                //if the user is registered, then check if the password is correct
//                if (person.getPersonPassword().equals(txtPassword.getText().toString())
//                        && person.getPersonEmail().equals(txtEmail.getText().toString())) {
//                    //if the password is correct, then navigate to the home page
//                    Intent intent = new Intent(LoginActivity.this, Main.class);
//                    startActivity(intent);
//                    this.finish();
//
//
//                } else {
//                    //if the password is incorrect, then show a toast message
//                    Toast.makeText(LoginActivity.this,
//                            "Incorrect credentials", Toast.LENGTH_SHORT).show();
//                }
//            } else {
//                //if the user is not registered, then show a toast message
//                Toast.makeText(LoginActivity.this,
//                        "User not registered", Toast.LENGTH_SHORT).show();
//            }
        });

        txtRegister.setOnClickListener(v -> {
            //if the user clicks on the register link, then go to the registration activity
            startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));

        });


    }

    /**
     * This method checks if the user is registered
     *
     * @param person
     * @return
     */
    private boolean isRegistered(Person person) {
        SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        String id = sharedPreferences.getString("id", "");
        String name = sharedPreferences.getString("name", "");
        String email = sharedPreferences.getString("email", "");
        String password = sharedPreferences.getString("password", "");
        String contactNumber = sharedPreferences.getString("contactNumber", "");

        Person person_obj = new Person(id, name, email, password, contactNumber, new Address("", "", "", "", ""));
        System.out.println("email from shared prefernces: " + person_obj.getPersonEmail() + "\n" + "email from the text field: " + txtEmail.getText().toString());

        return person_obj.getPersonEmail().equals(person.getPersonEmail());
    }


    @Override
    public void onSuccess() {
        Intent intent = new Intent(LoginActivity.this, Main.class);
        startActivity(intent);
        this.finish();
    }


    @Override
    public void onError(Object message) {
        CustomDialogue customDialogue = new CustomDialogue(this,
                "Invalid request", message.toString(),
                R.raw.cancel_animation);
        customDialogue.onShow();

    }
}