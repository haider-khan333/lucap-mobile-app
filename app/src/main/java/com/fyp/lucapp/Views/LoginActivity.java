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

import com.fyp.lucapp.Components.ComponentCustomDialogue;
import com.fyp.lucapp.Components.ComponentLoader;
import com.fyp.lucapp.Helper.LoaderUtils;
import com.fyp.lucapp.Helper.URL;
import com.fyp.lucapp.Interface.LoginCallback;
import com.fyp.lucapp.Main;
import com.fyp.lucapp.R;
import com.fyp.lucapp.Schemas.LoginSchema;
import com.fyp.lucapp.Views.ForgetPassword.FPEnterEmail;
import com.fyp.lucapp.Views.Registration.RegisterPatientDetails;

public class LoginActivity extends AppCompatActivity implements LoginCallback {
    private TextView txtEmail;
    private TextView txtPassword;
    private ComponentLoader componentLoader;
    private Button btnLogin;
    private URL url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Initialize
        txtEmail = findViewById(R.id.lgEmail);
        txtPassword = findViewById(R.id.lgPassword);
        btnLogin = findViewById(R.id.login);
        componentLoader = findViewById(R.id.lgComponentLoader);

        TextView txtRegister = findViewById(R.id.lgRegisterLink);
        TextView txtForgetPassword = findViewById(R.id.lgForgetPassword);


        btnLogin.setEnabled(false);
        url = new URL(this, this);
        txtPassword.addTextChangedListener(new TextWatcher() {
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

        btnLogin.setOnClickListener(v -> {
            String email = txtEmail.getText().toString();
            String password = txtPassword.getText().toString();
            LoginSchema loginSchema = new LoginSchema(email, password);
            btnLogin.setEnabled(false);
            componentLoader.setAnimation(R.raw.loader_anim);
            LoaderUtils.showLoader(componentLoader);
            url.loginPatient(loginSchema);


        });

        txtForgetPassword.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, FPEnterEmail.class);
            startActivity(intent);
        });

        txtRegister.setOnClickListener(v -> {
            //if the user clicks on the register link, then go to the registration activity
            startActivity(new Intent(LoginActivity.this, RegisterPatientDetails.class));

        });


    }

    private void checkNextButtonState() {
        boolean isEmailEmpty = txtEmail.getText().toString().isEmpty();
        boolean isPasswordEmpty = txtPassword.getText().toString().isEmpty();
        boolean isEmailValid = Patterns.EMAIL_ADDRESS.
                matcher(txtEmail.getText().toString()).matches();

        System.out.println("isEmailEmpty: " + isEmailEmpty);
        System.out.println("isPasswordEmpty: " + isPasswordEmpty);
        System.out.println("isEmailValid: " + isEmailValid);

        btnLogin.setEnabled(!isEmailEmpty && !isPasswordEmpty && isEmailValid);
    }


    @Override
    public void onSuccess() {
//        btnLogin.setEnabled(true);
        LoaderUtils.hideLoader(componentLoader);
        Intent intent = new Intent(LoginActivity.this, Main.class);

        SharedPreferences sharedPreferences = getSharedPreferences("userDetail", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        System.out.println("userID (logged in): " + URL.LOGGED_IN_PATIENT_ID);
        editor.putString("userID", URL.LOGGED_IN_PATIENT_ID);
        editor.apply();


        startActivity(intent);
        this.finish();
    }

    @Override
    public void onError(Object message) {
        btnLogin.setEnabled(true);
        LoaderUtils.hideLoader(componentLoader);
        ComponentCustomDialogue componentCustomDialogue = new ComponentCustomDialogue(this,
                "Invalid request", message.toString(),
                R.raw.cancel_animation);
        componentCustomDialogue.onShow();

    }
}