package com.fyp.lucapp.Components;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.fyp.lucapp.Helper.Helper;
import com.fyp.lucapp.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ComponentUser extends LinearLayout {
    private CircleImageView patientImage;
    private TextView patientName;
    private TextView patientEmail;

    public ComponentUser(Context context) {
        super(context);
        init();
    }

    public ComponentUser(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ComponentUser(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.component_user,
                this, true);
        patientImage = view.findViewById(R.id.setPatientImage);
        patientName = view.findViewById(R.id.setPatientName);
        patientEmail = view.findViewById(R.id.setPatientEmail);
    }

    public void setPatientImage(String base64) {
        Bitmap image = Helper.convertBase64ToBitmap(base64);
        patientImage.setImageBitmap(image);
    }

    public void setPatientName(String name) {
        patientName.setText(name);
    }

    public void setPatientEmail(String email) {
        patientEmail.setText(email);
    }


}
