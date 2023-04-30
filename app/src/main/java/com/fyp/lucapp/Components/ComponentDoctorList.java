package com.fyp.lucapp.Components;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.fyp.lucapp.Helper.Helper;
import com.fyp.lucapp.R;

public class ComponentDoctorList extends LinearLayout {

    private ImageView doctorImage;
    private TextView doctorName;
    private TextView doctorExperience;
    private TextView doctorSpeciality;
    private TextView doctorPhone;
    private TextView doctorEmail;


    public ComponentDoctorList(Context context) {
        super(context);
        init();
    }

    public ComponentDoctorList(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ComponentDoctorList(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.component_doctor_card, this,
                true);
        doctorImage = view.findViewById(R.id.docImage);
        doctorName = view.findViewById(R.id.doctorName);
        doctorExperience = view.findViewById(R.id.experience);
        doctorSpeciality = view.findViewById(R.id.speciality);
        doctorPhone = view.findViewById(R.id.phone);
        doctorEmail = view.findViewById(R.id.fpEmail);
    }

    public void setDoctorImage(Bitmap doctorBitmap) {
        this.doctorImage.setImageBitmap(doctorBitmap);

    }

    public void setDoctorImage(String doctorBase64) {
        Bitmap doctorBitmap = Helper.convertBase64ToBitmap(doctorBase64);
        this.doctorImage.setImageBitmap(doctorBitmap);

    }

    public void setDoctorName(String doctorName) {
        this.doctorName.setText(doctorName);
    }

    public void setDoctorExperience(String doctorExperience) {
        this.doctorExperience.setText(doctorExperience);
    }

    public void setDoctorSpeciality(String doctorSpeciality) {
        this.doctorSpeciality.setText(doctorSpeciality);
    }

    public void setDoctorPhone(String doctorPhone) {
        this.doctorPhone.setText(doctorPhone);
    }

    public void setDoctorEmail(String doctorEmail) {
        this.doctorEmail.setText(doctorEmail);
    }


}
