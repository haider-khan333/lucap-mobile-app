package com.fyp.lucapp.Components.DoctorExperience;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.fyp.lucapp.R;

public class ComponentDoctorExperience extends LinearLayout {
    private TextView patient;
    private TextView experience;
    private TextView rating;
    private TextView reviews;

    public ComponentDoctorExperience(Context context) {
        super(context);
        init();
    }

    public ComponentDoctorExperience(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ComponentDoctorExperience(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View view = inflate(getContext(), R.layout.comoponent_doctor_experience, this);
        patient = view.findViewById(R.id.de_patients);
        experience = view.findViewById(R.id.de_experience);
        rating = view.findViewById(R.id.de_rating);
        reviews = view.findViewById(R.id.de_reviews);

        this.setBackground(null);
    }

    public void setPatient(String patient) {
        this.patient.setText(patient);
    }

    public void setExperience(String experience) {
        this.experience.setText(experience);
    }

    public void setRating(String rating) {
        this.rating.setText(rating);
    }

    public void setReviews(String reviews) {
        this.reviews.setText(reviews);
    }


}
