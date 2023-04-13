package com.fyp.lucapp.Views;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fyp.lucapp.BasicModels.DoctorsData;
import com.fyp.lucapp.BasicModels.WorkTime;
import com.fyp.lucapp.Components.ComponentCustomDialogue;
import com.fyp.lucapp.Helper.Helper;
import com.fyp.lucapp.Helper.URL;
import com.fyp.lucapp.Interface.InterfaceApi;
import com.fyp.lucapp.Interface.OnClickSubmit;
import com.fyp.lucapp.Main;
import com.fyp.lucapp.R;
import com.fyp.lucapp.Schemas.AppointmentSchema;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DoctorDetailsActivity extends AppCompatActivity implements InterfaceApi, OnClickSubmit {


    private class DoctorDetails {
        private String username;
        private String speciality;
        private String image;
        private int noOfPatients;
        private int yearsOfExperience;
        private int numberOfReviews;
        private String about;
        private List<WorkTime> workingTime = new ArrayList<>();
    }

    private List<String> days;
    private int index = 0;

    private Button nextButton;
    private ImageView backButton;

    private ImageView doctorImage;
    private TextView doctorName;
    private TextView doctorSpeciality;
    private TextView doctorPatients;

    private AutoCompleteTextView dropItems;
    private TextView doctorReviews;
    private TextView doctorRating;
    private TextView doctorExperience;
    private TextView doctorDescription;

    private OnClickSubmit onClickSubmit;

    private InterfaceApi interfaceApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        days = new ArrayList<>();

        this.onClickSubmit = this;
        this.interfaceApi = this;

        URL url = new URL(this, this.interfaceApi);


        //bind the views
        doctorImage = findViewById(R.id.docImage);
        doctorName = findViewById(R.id.cardDoctorName);
        doctorSpeciality = findViewById(R.id.speciality);


        doctorPatients = findViewById(R.id.patients);
        doctorReviews = findViewById(R.id.reviews);
        doctorRating = findViewById(R.id.rating);
        doctorExperience = findViewById(R.id.experience);
        doctorDescription = findViewById(R.id.doctorDescription);
        dropItems = findViewById(R.id.drop_items);

        TextView time = findViewById(R.id.workingTimeDate);


        Button nextButton = findViewById(R.id.makeAppointment);


        //get data from intent
        DoctorsData doctorsData = (DoctorsData) getIntent().getSerializableExtra("doctor");
        int doctorId = doctorsData.getId();
        url.getSpecificDoctor(doctorId);


        final URL makeAppointments = new URL(this, this.onClickSubmit);
        nextButton.setOnClickListener(v -> {
            String start_time = days.get(index);
            //split the time and get the first part
            start_time = start_time.split(":")[0];
            String end_time = Integer.valueOf(start_time) + 1+"";

            //remove the : from the time
            end_time = end_time.replace(":", "");
            start_time = start_time.replace(":", "");

            AppointmentSchema appointmentSchema = new AppointmentSchema(doctorId,
                    Integer.parseInt(URL.LOGGED_IN_PATIENT_ID),
                    time.getText().toString(), start_time, end_time);
            makeAppointments.addAppointments(appointmentSchema);


        });


        //get the current day
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        switch (day) {
            case Calendar.SUNDAY:
                time.setText("Sunday");
                break;
            case Calendar.MONDAY:
                time.setText("Monday");
                break;
            case Calendar.TUESDAY:
                time.setText("Tuesday");
                break;
            case Calendar.WEDNESDAY:
                time.setText("Wednesday");
                break;
            case Calendar.THURSDAY:
                time.setText("Thursday");
                break;
            case Calendar.FRIDAY:
                time.setText("Friday");
                break;
            case Calendar.SATURDAY:
                time.setText("Saturday");
                break;
        }


        backButton = findViewById(R.id.backButton);
        //back button
        backButton.setOnClickListener(v -> finish());

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    @Override
    public void onSuccess(Object object) {
        JSONObject jsonObject = (JSONObject) object;
        DoctorDetails doctorDetails = new DoctorDetails();
        doctorDetails.username = jsonObject.optString("username");
        doctorDetails.speciality = jsonObject.optString("speciality");
        doctorDetails.image = jsonObject.optString("image");
        doctorDetails.noOfPatients = jsonObject.optInt("noOfPatients");
        doctorDetails.yearsOfExperience = jsonObject.optInt("yearsOfExperience");
        JSONArray reviews = jsonObject.optJSONArray("current_reviews");
        doctorDetails.about = jsonObject.optString("about");


        if (reviews == null) {
            doctorDetails.numberOfReviews = 0;
        } else {
            doctorDetails.numberOfReviews = reviews.length();
        }

        JSONArray workingTime = jsonObject.optJSONArray("current_working_times");

        if (workingTime == null) {
            doctorDetails.workingTime = null;
        } else {
            for (int i = 0; i < workingTime.length(); i++) {
                JSONObject obj = workingTime.optJSONObject(i);
                String day = obj.optString("day");
                String startTime = obj.optString("start_time");
                String endTime = obj.optString("end_time");
                doctorDetails.workingTime.add(new WorkTime(day, startTime, endTime));

            }
        }

        for (int i = 0; i < jsonObject.optJSONArray("available_times").length(); i++) {
            String day = String.valueOf(jsonObject.optJSONArray("available_times").
                    optString(i));
            days.add(day);

        }

        //add :00 to the time
        for (int i = 0; i < days.size(); i++) {
            String time = days.get(i);
            days.set(i, time + ":00");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.items_list, days);
        dropItems.setAdapter(adapter);

        //add listener to the drop down
        dropItems.setOnItemClickListener((parent, view, position, id) -> {
            index = position;
        });

        Bitmap doctorImage = Helper.convertBase64ToBitmap(doctorDetails.image);

        //set the data
        this.doctorImage.setImageBitmap(doctorImage);
        this.doctorName.setText(doctorDetails.username);
        this.doctorSpeciality.setText(doctorDetails.speciality);
        this.doctorPatients.setText(String.valueOf(doctorDetails.noOfPatients));
        this.doctorReviews.setText(String.valueOf(doctorDetails.numberOfReviews));
        this.doctorRating.setText(String.valueOf(doctorDetails.numberOfReviews));
        this.doctorExperience.setText(String.valueOf(doctorDetails.yearsOfExperience));
        this.doctorDescription.setText(doctorDetails.about);


    }

    @Override
    public void onError(Object message) {
        ComponentCustomDialogue componentCustomDialogue = new ComponentCustomDialogue(this, "Error",
                message.toString(), R.raw.cancel_animation);
        componentCustomDialogue.onShow();

    }

    @Override
    public void onSuccessSubmit(Object message) {
        Toast.makeText(this, message.toString(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, Main.class);
        startActivity(intent);

    }

    @Override
    public void onFailure(Object message) {
        ComponentCustomDialogue componentCustomDialogue = new ComponentCustomDialogue(this, "Error",
                message.toString(), R.raw.cancel_animation);
        componentCustomDialogue.onShow();
    }
}

