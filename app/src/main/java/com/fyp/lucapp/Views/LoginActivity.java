package com.fyp.lucapp.Views;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fyp.lucapp.BasicModels.DPatient;
import com.fyp.lucapp.Components.ComponentLoader;
import com.fyp.lucapp.Helper.Helper;
import com.fyp.lucapp.Helper.LoaderUtils;
import com.fyp.lucapp.Helper.URL;
import com.fyp.lucapp.Interface.InterfaceApi;
import com.fyp.lucapp.Main;
import com.fyp.lucapp.R;
import com.fyp.lucapp.Routes.RoutePost;
import com.fyp.lucapp.Routes.Url;
import com.fyp.lucapp.Schemas.LoginSchema;
import com.fyp.lucapp.Utils.UtilsDialogue;
import com.fyp.lucapp.Views.ForgetPassword.FPEnterEmail;
import com.fyp.lucapp.Views.Registration.RegisterPatientDetails;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements InterfaceApi {
    private TextView txtEmail;
    private TextView txtPassword;
    private ComponentLoader componentLoader;
    private Button btnLogin;


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

            loadDataFromApi(loginSchema);


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

    private void loadDataFromApi(LoginSchema schema) {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("email", schema.getEmail());
            jsonObject.put("password", schema.getPassword());
        } catch
        (Exception e) {
            e.printStackTrace();
        }

        RoutePost routePost = new RoutePost(this, this);
        routePost.post(Url.PATIENT_LOGIN, jsonObject);

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
    public void onSuccess(JSONObject response) {

        try {
            int statusCode = response.optInt("status_code");
            if (statusCode == 200) {
                JSONObject patientObject = response.getJSONObject("patient");
                DPatient patient = Helper.getPatient(patientObject);
                LoaderUtils.hideLoader(componentLoader);
                Intent intent = new Intent(LoginActivity.this, Main.class);

                Helper.saveUser(this, patient.getPatientId(),
                        patient.getPatientName(),
                        patient.getPatientEmail(),
                        patient.getPatientContact(),
                        patient.getPatientGender(),
                        patient.getPatientImage(),
                        String.valueOf(patient.getPatientAge()));

                startActivity(intent);
                this.finish();


            } else {
                btnLogin.setEnabled(true);
                LoaderUtils.hideLoader(componentLoader);
                Toast.makeText(this, "Invalid Username or Password",
                        Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            btnLogin.setEnabled(true);
            LoaderUtils.hideLoader(componentLoader);
            UtilsDialogue.showErrorDialogue(e.toString(), this);
        }


    }

    @Override
    public void onError(Object message) {
        btnLogin.setEnabled(true);
        LoaderUtils.hideLoader(componentLoader);
        UtilsDialogue.showErrorDialogue(message.toString(), this);

    }
}