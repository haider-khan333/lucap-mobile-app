package com.fyp.lucapp.Views.ForgetPassword;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.fyp.lucapp.Helper.Helper;
import com.fyp.lucapp.Helper.Store;
import com.fyp.lucapp.Interface.InterfaceApi;
import com.fyp.lucapp.R;
import com.fyp.lucapp.Routes.RoutePost;
import com.fyp.lucapp.Routes.Url;
import com.fyp.lucapp.Schemas.SendMailSchema;
import com.fyp.lucapp.Utils.UtilsDialogue;

import org.json.JSONObject;

public class FPEnterCode extends AppCompatActivity implements InterfaceApi {

    private EditText txtCode1;
    private EditText txtCode2;
    private EditText txtCode3;
    private EditText txtCode4;
    private EditText txtCode5;
    private EditText txtCode6;

    private CountDownTimer countDownTimer;

    private TextView instructionsTextView;
    private TextView fpResendCode;
    private TextView fpEmailText;
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

        instructionsTextView = findViewById(R.id.fpInstructions);
        fpResendCode = findViewById(R.id.fpResendCode);
        fpEmailText = findViewById(R.id.fpEmailBox);

        String text = "Didn't receive the OTP? ";
        String resendOtpText = "RESEND OTP";

        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text + resendOtpText);


        int start = text.length();
        int end = start + resendOtpText.length();
        spannableStringBuilder.setSpan(new ForegroundColorSpan(
                ContextCompat.getColor(this, R.color.green)
        ), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


        String instructionsText = getString(R.string.resend_code);
        instructionsTextView.setText(instructionsText);

        fpResendCode.setText(spannableStringBuilder);


        String email = Helper.getSavedUser(this).getPatientEmail();
        System.out.println("email: " + email);
        fpEmailText.setText(email);

        SendMailSchema sendMailSchema = new SendMailSchema();
        sendMailSchema.setToEmail(email);
        sendMailSchema.setFromEmail(Store.EMAIL);
        setupEditTextListeners();
        txtCode1.requestFocus();


        loadDataFromApi(sendMailSchema);


        fpResendCode.setOnClickListener(view -> {
            loadDataFromApi(sendMailSchema);
            startCountDownTimer();
            Toast.makeText(FPEnterCode.this, "OTP sent", Toast.LENGTH_SHORT).show();

        });


    }

    private void loadDataFromApi(SendMailSchema sendMailSchema) {
        RoutePost routePost = new RoutePost(this, this);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("from_email", sendMailSchema.getFromEmail());
            jsonObject.put("to_email", sendMailSchema.getToEmail());
        } catch
        (Exception e) {
            e.printStackTrace();
        }

        routePost.post(Url.SEND_MAIL, jsonObject);


    }

    private void startCountDownTimer() {
        // Disable the fpResendCode button
        fpResendCode.setEnabled(false);

        // Cancel any existing timer
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

        // Start a new 30-second countdown timer
        countDownTimer = new CountDownTimer(5 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Display the remaining time
                String text = "Resend OTP in " + (millisUntilFinished / 1000) + "s";
                fpResendCode.setText(text);
            }

            @Override
            public void onFinish() {
                // Enable the fpResendCode button and reset the text
                fpResendCode.setEnabled(true);

                String resendOtpText = "RESEND OTP";

                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(resendOtpText);


                int start = 0;
                int end = start + resendOtpText.length();
                spannableStringBuilder.setSpan(new ForegroundColorSpan(
                        ContextCompat.getColor(FPEnterCode.this, R.color.green)
                ), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                fpResendCode.setText(spannableStringBuilder);
            }
        }.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Cancel the timer when the activity is destroyed
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
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
    public void onSuccess(JSONObject response) {

        try {
            int statusCode = response.optInt("status_code");
            if (statusCode == 202) {
                uniqueCode = response.optInt("code");
            } else {
                Toast.makeText(this, "Error sending mail", Toast.LENGTH_SHORT).show();
            }

        } catch
        (Exception e) {
            e.printStackTrace();
            UtilsDialogue.showErrorDialogue(e.toString(), this);
        }


    }

    @Override
    public void onError(Object message) {
        UtilsDialogue.showErrorDialogue(message.toString(), this);

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
                        setEditTextBackgroundRed(false);
                        Intent intent = new Intent(FPEnterCode.this,
                                FPEnterPassword.class);
                        startActivity(intent);
                        finish();

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
