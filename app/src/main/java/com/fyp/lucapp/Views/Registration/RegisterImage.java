package com.fyp.lucapp.Views.Registration;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.fyp.lucapp.Components.CustomDialogue;
import com.fyp.lucapp.Helper.Helper;
import com.fyp.lucapp.Helper.URL;
import com.fyp.lucapp.Interface.RegisterCallback;
import com.fyp.lucapp.R;
import com.fyp.lucapp.Schemas.RegisterPatientSchema;
import com.fyp.lucapp.Views.LoginActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterImage extends AppCompatActivity implements RegisterCallback {

    private final int SELECT_PICTURE = 200;
    private Bitmap selectedImage;

    private Button uploadButton;


    private TextView age;

    private URL url;

    private CircleImageView userImage;
    private Button nextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_image);

        url = new URL(this, this);

        //get intents from previous activity
        Intent intent = getIntent();
        String firstName = intent.getStringExtra("firstName");
        String lastName = intent.getStringExtra("lastName");
        String email = intent.getStringExtra("email");
        String password = intent.getStringExtra("password");
        String contactNumber = intent.getStringExtra("contactNumber");
        String gender = intent.getStringExtra("gender");

        uploadButton = findViewById(R.id.uploadButton);
        nextButton = findViewById(R.id.nextButton);
        userImage = findViewById(R.id.userImage);
        age = findViewById(R.id.age);

        nextButton.setEnabled(false);

        age.addTextChangedListener(new TextWatcher() {
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

        uploadButton.setOnClickListener(v -> imageChooser());

        nextButton.setOnClickListener(e -> {


            if (selectedImage == null) {
                CustomDialogue customDialogue = new CustomDialogue(this,
                        "Image Alert!", "Please upload an image before proceeding",
                        R.raw.cancel_animation);
                customDialogue.onShow();
            } else {
                String base64 = Helper.convertBitmapToBase64(selectedImage);
                int age = Integer.parseInt(this.age.getText().toString());
                RegisterPatientSchema register
                        = new RegisterPatientSchema(email, password, firstName + " " + lastName,
                        contactNumber, gender, age, base64);

                //lottie animation

                url.registerPatient(register);
            }


        });

    }

    private void checkNextButtonState() {
        boolean isAgeEmpty = age.getText().toString().isEmpty();
        boolean isImageEmpty = userImage == null;
        boolean isAgeValid = Integer.parseInt(age.getText().toString()) > 0
                && Integer.parseInt(age.getText().toString()) < 150;


        nextButton.setEnabled(!isAgeEmpty && !isImageEmpty && isAgeValid);
    }


    public void imageChooser() {

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
                    userImage.setBorderWidth(0);
                    userImage.setImageBitmap(selectedImage);

                } else {
                    CustomDialogue customDialogue = new
                            CustomDialogue(this, "Error", "Please select an image"
                            , R.raw.cancel_animation);
                    customDialogue.onShow();
                }
            }
        }

        if (resultCode == RESULT_CANCELED) {
            //DO SOMETHING IF CANCELLED
            Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onSuccess() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();

    }

    @Override
    public void onError(Object message) {

        CustomDialogue customDialogue = new
                CustomDialogue(this, "Error", message.toString()
                , R.raw.cancel_animation);
        customDialogue.onShow();

    }

}
