package com.fyp.lucapp.Views.ForgetPassword;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.fyp.lucapp.Components.ComponentCustomDialogue;
import com.fyp.lucapp.Components.ComponentLoader;
import com.fyp.lucapp.Helper.LoaderUtils;
import com.fyp.lucapp.Helper.URL;
import com.fyp.lucapp.Interface.InterfaceApi;
import com.fyp.lucapp.R;
import com.fyp.lucapp.Schemas.EditPasswordSchema;
import com.fyp.lucapp.Views.LoginActivity;

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
            url = new URL(this, this);

            EditPasswordSchema schema = new EditPasswordSchema();
            schema.setNewPassword(txtPassword.getText().toString());

            url.updatePassword(schema);
        });


    }

    private void checkButtonState() {
        boolean isPasswordEmpty = txtPassword.getText().toString().isEmpty();
        boolean isConfirmPasswordEmpty = txtConfirmPassword.getText().toString().isEmpty();
        boolean isPasswordMatch = txtPassword.getText().toString().
                equals(txtConfirmPassword.getText().toString());

        btnSubmit.setEnabled(!isPasswordEmpty && !isConfirmPasswordEmpty && isPasswordMatch);

    }

    @Override
    public void onSuccess(Object object) {
        LoaderUtils.hideLoader(loader);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();

    }

    @Override
    public void onError(Object message) {
        btnSubmit.setEnabled(true);
        txtPassword.setEnabled(true);
        txtConfirmPassword.setEnabled(true);
        LoaderUtils.hideLoader(loader);

        ComponentCustomDialogue dialogue = new ComponentCustomDialogue(this,
                "Error", message.toString(), R.raw.cancel_animation);
        dialogue.onShow();


    }

}
