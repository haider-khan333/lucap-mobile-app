package com.fyp.lucapp.Views.ForgetPassword;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fyp.lucapp.BasicModels.Patient;
import com.fyp.lucapp.Components.ComponentCustomDialogue;
import com.fyp.lucapp.Helper.Helper;
import com.fyp.lucapp.Helper.Store;
import com.fyp.lucapp.Helper.URL;
import com.fyp.lucapp.Interface.InterfaceApi;
import com.fyp.lucapp.R;
import com.fyp.lucapp.Schemas.SendMailSchema;

public class FPEnterCode extends AppCompatActivity implements InterfaceApi {

    private EditText txtCode1;
    private EditText txtCode2;
    private EditText txtCode3;
    private EditText txtCode4;
    private EditText txtCode5;
    private EditText txtCode6;

    private TextView instructionsTextView;
    private TextView fpResendCode;
    private int uniqueCode = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fp_enter_code);


        txtCode1 = findViewById(R.id.editText1);
        txtCode2 = findViewById(R.id.editText2);
        txtCode3 = findViewById(R.id.editText3);
        txtCode4 = findViewById(R.id.editText4);
        txtCode5 = findViewById(R.id.editText5);
        txtCode6 = findViewById(R.id.editText6);

        URL url = new URL(this, this);

        instructionsTextView = findViewById(R.id.fpInstructions);
        fpResendCode = findViewById(R.id.fpResendCode);


        Patient patient = (Patient) getIntent().getSerializableExtra("patient");
        String email = patient.getPatientEmail();
        String text = getString(R.string.instructions) + Helper.convertEmailToAsterisks(email);
        instructionsTextView.setText(text);

        SendMailSchema sendMailSchema = new SendMailSchema();
        sendMailSchema.setToEmail(email);
        sendMailSchema.setFromEmail(Store.EMAIL);
        url.sendMail(sendMailSchema);
        setupEditTextListeners();
        txtCode1.requestFocus();


        fpResendCode.setOnClickListener(v -> url.sendMail(sendMailSchema));


    }

    private boolean areAllEditTextsFilled() {
        return !txtCode1.getText().toString().isEmpty()
                && !txtCode2.getText().toString().isEmpty()
                && !txtCode3.getText().toString().isEmpty()
                && !txtCode4.getText().toString().isEmpty()
                && !txtCode5.getText().toString().isEmpty()
                && !txtCode6.getText().toString().isEmpty();
    }


    private void setupEditTextListeners() {
        txtCode1.addTextChangedListener(new CustomTextWatcher(txtCode1, txtCode2));
        txtCode2.addTextChangedListener(new CustomTextWatcher(txtCode2, txtCode3));
        txtCode3.addTextChangedListener(new CustomTextWatcher(txtCode3, txtCode4));
        txtCode4.addTextChangedListener(new CustomTextWatcher(txtCode4, txtCode5));
        txtCode5.addTextChangedListener(new CustomTextWatcher(txtCode5, txtCode6));
        txtCode6.addTextChangedListener(new CustomTextWatcher(txtCode6, null));
    }

    @Override
    public void onSuccess(Object object) {
        uniqueCode = (int) object;

    }

    @Override
    public void onError(Object message) {
        ComponentCustomDialogue componentCustomDialogue = new ComponentCustomDialogue(this,
                "Error", message.toString(), R.raw.cancel_animation);
        componentCustomDialogue.onShow();

    }


    private void shakeEditTexts() {
        Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        txtCode1.startAnimation(shake);
        txtCode2.startAnimation(shake);
        txtCode3.startAnimation(shake);
        txtCode4.startAnimation(shake);
        txtCode5.startAnimation(shake);
        txtCode6.startAnimation(shake);
    }

    private void setEditTextBackgroundRed(boolean isRed) {
        int drawableResource = isRed ? R.drawable.rounded_edittext_red : R.drawable.rounded_edittext;
        txtCode1.setBackgroundResource(drawableResource);
        txtCode2.setBackgroundResource(drawableResource);
        txtCode3.setBackgroundResource(drawableResource);
        txtCode4.setBackgroundResource(drawableResource);
        txtCode5.setBackgroundResource(drawableResource);
        txtCode6.setBackgroundResource(drawableResource);
    }


    private class CustomTextWatcher implements TextWatcher {
        private final EditText currentEditText;
        private final EditText nextEditText;

        public CustomTextWatcher(EditText currentEditText, EditText nextEditText) {
            this.currentEditText = currentEditText;
            this.nextEditText = nextEditText;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            // Move to next EditText when a digit is entered
            if (charSequence.length() == 1 && nextEditText != null) {
                nextEditText.requestFocus();
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // Move to the previous EditText when backspace is pressed
            if (editable.length() == 0 && currentEditText.isFocused()) {
                View previousEditText = currentEditText.focusSearch(View.FOCUS_LEFT);
                if (previousEditText instanceof EditText) {
                    previousEditText.requestFocus();
                }
            }

            // Perform the desired action when all EditTexts are filled
            if (areAllEditTextsFilled()) {
                // Do something, e.g., startActivity, perform a task, etc.
                if (uniqueCode != -1) {
                    String code = txtCode1.getText().toString() +
                            txtCode2.getText().toString() +
                            txtCode3.getText().toString() +
                            txtCode4.getText().toString() +
                            txtCode5.getText().toString() +
                            txtCode6.getText().toString();
                    int codeInt = Integer.parseInt(code);
                    if (codeInt == uniqueCode) {
                        Toast.makeText(FPEnterCode.this, "Code is correct", Toast.LENGTH_SHORT).show();
                        setEditTextBackgroundRed(false);
                    } else {
                        Toast.makeText(FPEnterCode.this, "Code is incorrect", Toast.LENGTH_SHORT).show();
                        setEditTextBackgroundRed(true);
                        shakeEditTexts();
                    }
                }

            }
        }


    }


}
