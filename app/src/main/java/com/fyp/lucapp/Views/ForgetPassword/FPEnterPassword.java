package com.fyp.lucapp.Views.ForgetPassword;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fyp.lucapp.Components.ComponentLoader;
import com.fyp.lucapp.Helper.LoaderUtils;
import com.fyp.lucapp.Helper.URL;
import com.fyp.lucapp.Interface.InterfaceApi;
import com.fyp.lucapp.R;
import com.fyp.lucapp.Routes.RoutePut;
import com.fyp.lucapp.Routes.Url;
import com.fyp.lucapp.Schemas.EditPasswordSchema;
import com.fyp.lucapp.Utils.UtilsDialogue;
import com.fyp.lucapp.Views.LoginActivity;

import org.json.JSONObject;

public class FPEnterPassword extends AppCompatActivity implements InterfaceApi {
    private EditText txtPassword;
    private EditText txtConfirmPassword;

    private Button btnSubmit;

    private ComponentLoader loader;

    private URL url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fp_enter_password);

        txtPassword = findViewById(R.id.fpePassword);
        txtConfirmPassword = findViewById(R.id.fpeConfirmPassword);
        loader = findViewById(R.id.fpeComponentLoader);
        btnSubmit = findViewById(R.id.fpeNext);

        btnSubmit.setEnabled(false);

        txtConfirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkButtonState();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnSubmit.setOnClickListener(e -> {
            btnSubmit.setEnabled(false);
            txtPassword.setEnabled(false);
            txtConfirmPassword.setEnabled(false);
            loader.setAnimation(R.raw.loader_anim);
            LoaderUtils.showLoader(loader);


            EditPasswordSchema schema = new EditPasswordSchema();
            schema.setNewPassword(txtPassword.getText().toString());

            loadDataFromApi(schema);
        });


    }

    private void loadDataFromApi(EditPasswordSchema schema) {

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("new_password", schema.getNewPassword());
        } catch
        (Exception e) {
            e.printStackTrace();
        }

        RoutePut routePut = new RoutePut(this, this);
        routePut.put(Url.EDIT_PASSWORD + "/" + URL.LOGGED_IN_PATIENT_ID, jsonObject);


    }

    private void checkButtonState() {
        boolean isPasswordEmpty = txtPassword.getText().toString().isEmpty();
        boolean isConfirmPasswordEmpty = txtConfirmPassword.getText().toString().isEmpty();
        boolean isPasswordMatch = txtPassword.getText().toString().
                equals(txtConfirmPassword.getText().toString());

        btnSubmit.setEnabled(!isPasswordEmpty && !isConfirmPasswordEmpty && isPasswordMatch);

    }

    @Override
    public void onSuccess(JSONObject response) {

        try {
            int statusCode = response.optInt("status_code");
            if (statusCode == 200) {
                LoaderUtils.hideLoader(loader);
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "System: Error changing password ",
                        Toast.LENGTH_SHORT).show();
            }

        } catch
        (Exception e) {
            e.printStackTrace();
            UtilsDialogue.showErrorDialogue(e.getMessage(), this);
        }


    }

    @Override
    public void onError(Object message) {
        btnSubmit.setEnabled(true);
        txtPassword.setEnabled(true);
        txtConfirmPassword.setEnabled(true);
        LoaderUtils.hideLoader(loader);

        UtilsDialogue.showErrorDialogue(message.toString(), this);


    }


}
