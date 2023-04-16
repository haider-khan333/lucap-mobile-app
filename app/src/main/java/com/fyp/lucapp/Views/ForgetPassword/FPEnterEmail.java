package com.fyp.lucapp.Views.ForgetPassword;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.fyp.lucapp.BasicModels.Patient;
import com.fyp.lucapp.Components.ComponentCustomDialogue;
import com.fyp.lucapp.Components.ComponentLoader;
import com.fyp.lucapp.Helper.Helper;
import com.fyp.lucapp.Helper.LoaderUtils;
import com.fyp.lucapp.Helper.URL;
import com.fyp.lucapp.Interface.InterfaceApi;
import com.fyp.lucapp.R;
import com.fyp.lucapp.Schemas.GetPatientSchema;

import org.json.JSONObject;

public class FPEnterEmail extends AppCompatActivity implements InterfaceApi {

    private TextView txtEmail;
    private ComponentLoader loader;
    private Button nextBtn;
    private URL url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fp_enter_email);

        txtEmail = findViewById(R.id.fpEmail);
        loader = findViewById(R.id.fpComponentLoader);
        nextBtn = findViewById(R.id.fpNext);
        nextBtn.setEnabled(false);


        url = new URL(this, this);

        txtEmail.addTextChangedListener(new TextWatcher() {
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

        nextBtn.setOnClickListener(v -> {
            nextBtn.setEnabled(false);
            loader.setAnimation(R.raw.loader_anim);
            LoaderUtils.showLoader(loader);
            GetPatientSchema patientSchema = new GetPatientSchema();
            patientSchema.setEmail(txtEmail.getText().toString());
            url.getPatient(patientSchema);
        });


    }

    private void checkNextButtonState() {
        boolean isEmailValid = txtEmail.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+");
        boolean isEmailEmpty = txtEmail.getText().toString().isEmpty();
        nextBtn.setEnabled(!isEmailEmpty && isEmailValid);
    }

    @Override
    public void onSuccess(Object object) {
        LoaderUtils.hideLoader(loader);
        JSONObject patientObject = (JSONObject) object;
        Patient patient = Helper.getPatient(patientObject);
        Intent intent = new Intent(this, FPEnterCode.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("patient", patient);
        intent.putExtras(bundle);
        startActivity(intent);


    }

    @Override
    public void onError(Object message) {
        LoaderUtils.hideLoader(loader);
        nextBtn.setEnabled(true);
        ComponentCustomDialogue dialogue =
                new ComponentCustomDialogue(this, "Error", message.toString(),
                        R.raw.cancel_animation);
        dialogue.onShow();

    }
}
