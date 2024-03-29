package com.fyp.lucapp.Views.Settings;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fyp.lucapp.BasicModels.DPatient;
import com.fyp.lucapp.Components.ComponentCustomDialogue;
import com.fyp.lucapp.Components.ComponentLoader;
import com.fyp.lucapp.Components.ComponentTextView;
import com.fyp.lucapp.Components.EditUserImage.ComponentEditUserImage;
import com.fyp.lucapp.Components.GenderSelection.ComponentGenderSelection;
import com.fyp.lucapp.Components.Header.ComponentHeader;
import com.fyp.lucapp.Helper.Helper;
import com.fyp.lucapp.Helper.URL;
import com.fyp.lucapp.Interface.InterfaceApi;
import com.fyp.lucapp.R;
import com.fyp.lucapp.Routes.RoutePut;
import com.fyp.lucapp.Routes.Url;
import com.fyp.lucapp.Utils.UtilsDialogue;

import org.json.JSONObject;

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener,
        TextWatcher, InterfaceApi {

    private ComponentHeader header;
    private ComponentEditUserImage editUserImage;
    private ComponentTextView firstName;
    private ComponentTextView lastName;
    private ComponentTextView userID;
    private ComponentTextView email;
    private ComponentTextView phoneNumber;

    private ComponentTextView age;
    private ComponentGenderSelection genderSelection;

    private ComponentLoader componentLoader;
    private Button updateButton;

    private final int SELECT_PICTURE = 300;

    private Bitmap selectedImage;

    private DPatient patient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        header = findViewById(R.id.edit_componentHeader);
        editUserImage = findViewById(R.id.edit_componentEditUserImage);
        genderSelection = findViewById(R.id.edit_componentGenderSelection);
        updateButton = findViewById(R.id.edit_updateButton);
        componentLoader = findViewById(R.id.edit_componentLoader);


        firstName = findViewById(R.id.edit_componentTextViewFirstName);
        lastName = findViewById(R.id.edit_componentTextViewLastName);
        userID = findViewById(R.id.edit_componentTextViewId);
        email = findViewById(R.id.edit_componentTextViewEmail);
        phoneNumber = findViewById(R.id.edit_componentTextViewPhone);
        age = findViewById(R.id.edit_componentTextViewAge);

        editUserImage.addClickListener(this);
        header.addClickListener(this);
        firstName.addOnTextWatcher(this);
        lastName.addOnTextWatcher(this);
        phoneNumber.addOnTextWatcher(this);
        age.addOnTextWatcher(this);


        updateButton.setEnabled(false);
        updateButton.setOnClickListener(this);


        //get the user details from shared preferences
        patient = Helper.getSavedUser(this);

        setTextViewLabel();
        setTextViewValues();


    }

    private void setTextViewLabel() {
        firstName.setLabel("First Name");
        lastName.setLabel("Last Name");
        userID.setLabel("User ID");
        email.setLabel("Email");
        phoneNumber.setLabel("Phone Number");
        age.setLabel("Age");
    }

    private void setTextViewValues() {
        String fullName = patient.getPatientName();
        String[] name = fullName.split(" ");
        if (name.length == 1) {
            firstName.setValue(name[0]);
            lastName.setValue("");
        } else {
            firstName.setValue(name[0]);
            lastName.setValue(name[1]);
        }
        userID.setValue(patient.getPatientId());
        userID.setEnabled(false);

        email.setValue(patient.getPatientEmail());
        email.setEnabled(false);


        phoneNumber.setValue(patient.getPatientContact());
        phoneNumber.setInputType(InputType.TYPE_CLASS_NUMBER);
        age.setValue(String.valueOf(patient.getPatientAge()));
        age.setInputType(InputType.TYPE_CLASS_NUMBER);
        genderSelection.setGenderSelection(patient.getPatientGender());


        editUserImage.setImage(patient.getPatientImage());
        header.setTitle("Edit Profile");

        String base64 = patient.getPatientImage();
        if (base64 != null) {
            selectedImage = Helper.convertBase64ToBitmap(base64);
        }
    }

    private void imageChooser() {

        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }

    // this function is triggered when user
    // selects the image from the imageChooser
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                try {
                    selectedImage = MediaStore.Images.Media.getBitmap(this.getContentResolver()
                            , selectedImageUri);
                } catch (Exception ex) {
                    System.out.println("Exception in getting bitmap : " + ex);
                }

                if (selectedImage != null) {
                    editUserImage.setImage(selectedImage);

                } else {
                    ComponentCustomDialogue componentCustomDialogue = new
                            ComponentCustomDialogue(this, "Error", "Please select an image"
                            , R.raw.cancel_animation);
                    componentCustomDialogue.onShow();
                }
            }
        }

        if (resultCode == RESULT_CANCELED) {
            //DO SOMETHING IF CANCELLED
            Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();
        }

    }

    private void enableButton() {
        boolean isFirstNameEmpty = firstName.getValueData().isEmpty();
        boolean isFirstNameValid = firstName.getValueData().matches("[a-zA-Z]+");

        boolean isLastNameEmpty = lastName.getValueData().isEmpty();
        boolean isLastNameValid = lastName.getValueData().matches("[a-zA-Z]+");

        boolean isPhoneNumberEmpty = phoneNumber.getValueData().isEmpty();
        boolean isPhoneNumberValid = phoneNumber.getValueData().matches("^03\\d{9}$");

        boolean isAgeEmpty = age.getValueData().isEmpty();
        boolean isAgeValid = age.getValueData().matches("[0-9]+");

        boolean isImageEmpty = selectedImage == null;


        System.out.println("isFirstNameEmpty : " + isFirstNameEmpty);
        System.out.println("isFirstNameValid : " + isFirstNameValid);
        System.out.println("isLastNameEmpty : " + isLastNameEmpty);
        System.out.println("isLastNameValid : " + isLastNameValid);
        System.out.println("isPhoneNumberEmpty : " + isPhoneNumberEmpty);
        System.out.println("isPhoneNumberValid : " + isPhoneNumberValid);
        System.out.println("isImageEmpty : " + isImageEmpty);


        updateButton.setEnabled(!isFirstNameEmpty && isFirstNameValid && !isLastNameEmpty &&
                isLastNameValid && !isPhoneNumberEmpty && isPhoneNumberValid && !isAgeEmpty &&
                isAgeValid && !isImageEmpty);

    }


    @Override
    public void onClick(View view) {
        System.out.println("BUTTON CLICKED");
        switch (view.getId()) {
            case R.id.editUserPatientImage:
                imageChooser();
                break;
            case R.id.headerBackButton:
                finish();
                break;
            case R.id.edit_updateButton:
//                url.updatePatient();
                System.out.println("UPDATE BUTTON CLICKED");
                loadDataFromApi();
                break;
        }

    }

    private void loadDataFromApi() {
        updateButton.setEnabled(false);
        componentLoader.playAnimation();
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("name", firstName.getValueData() + " " + lastName.getValueData());
            jsonObject.put("image", Helper.convertBitmapToBase64(selectedImage));
            jsonObject.put("phone", phoneNumber.getValueData());
            jsonObject.put("gender", genderSelection.getGenderSelection());
            jsonObject.put("age", age.getValueData());
        } catch (Exception ex) {
            System.out.println("Exception in load data from api : " + ex);
        }

        RoutePut routePut = new RoutePut(this, this);
        routePut.put(Url.UPDATE_PATIENT +"/"+ URL.LOGGED_IN_PATIENT_ID, jsonObject);

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        enableButton();

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onSuccess(JSONObject response) {
        componentLoader.pauseAnimation();
        updateButton.setEnabled(true);
        try {
            response = response.getJSONObject("patient");
            DPatient dPatient = Helper.getPatient(response);
            Helper.removeSavedUser(this);
            Helper.saveUser(this, dPatient.getPatientId(),
                    dPatient.getPatientName(), dPatient.getPatientEmail(),
                    dPatient.getPatientContact(), dPatient.getPatientGender(),
                    dPatient.getPatientImage(), String.valueOf(dPatient.getPatientAge()));
            Toast.makeText(this, "Profile Updated", Toast.LENGTH_SHORT).show();

        } catch (Exception ex) {
            System.out.println("Exception in on success : " + ex);
        }

    }

    @Override
    public void onError(Object message) {
        componentLoader.pauseAnimation();
        updateButton.setEnabled(true);
        UtilsDialogue.showErrorDialogue(message.toString(),this);

    }
}
