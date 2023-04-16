package com.fyp.lucapp.Views.Registration;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.fyp.lucapp.R;

public class RegisterPatientContact extends AppCompatActivity {

    private Button nextBtn;

    private TextView txtContactNumber;

    private RadioGroup radioGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_contact);

        //get previous intent
        Intent intent = getIntent();
        String firstName = intent.getStringExtra("firstName");
        String lastName = intent.getStringExtra("lastName");
        String email = intent.getStringExtra("email");
        String password = intent.getStringExtra("password");

        nextBtn = findViewById(R.id.nextButton);
        txtContactNumber = findViewById(R.id.contact);
        radioGroup = findViewById(R.id.radioGroup);

        nextBtn.setEnabled(false);

        radioGroup.clearCheck();

        txtContactNumber.addTextChangedListener(new TextWatcher() {
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

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> checkNextButtonState());

        nextBtn.setOnClickListener(v -> {
            //validate the text fields
            if (txtContactNumber.getText().toString().isEmpty()) {
                txtContactNumber.setError("Contact number is required");
            } else {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton = radioGroup.findViewById(selectedId);

                String contact = txtContactNumber.getText().toString();
                String gender = radioButton.getText().toString();

                Intent nextIntent = new Intent(this, RegisterPatientImage.class);
                nextIntent.putExtra("firstName", firstName);
                nextIntent.putExtra("lastName", lastName);
                nextIntent.putExtra("email", email);
                nextIntent.putExtra("password", password);
                nextIntent.putExtra("contactNumber", contact);
                nextIntent.putExtra("gender", gender);

                startActivity(nextIntent);

            }
        });


    }

    private void checkNextButtonState() {
        String contactNumber = txtContactNumber.getText().toString();
        boolean isTextEmpty = contactNumber.isEmpty();
        boolean isNumberValid = contactNumber.matches("^03\\d{9}$");
        boolean isRadioGroupEmpty = radioGroup.getCheckedRadioButtonId() == -1;
        nextBtn.setEnabled(!isTextEmpty && !isRadioGroupEmpty && isNumberValid);

    }

}
