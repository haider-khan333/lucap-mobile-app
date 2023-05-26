package com.fyp.lucapp.Components.GenderSelection;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;

import com.fyp.lucapp.R;

public class ComponentGenderSelection extends LinearLayout {

    private RadioGroup radioGroup;
    private RadioButton male;
    private RadioButton female;

    public ComponentGenderSelection(Context context) {
        super(context);
        init();
    }

    public ComponentGenderSelection(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ComponentGenderSelection(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.component_gender_selection,
                this, true);
        radioGroup = view.findViewById(R.id.radioGroupGender);
        male = view.findViewById(R.id.radioMale);
        female = view.findViewById(R.id.radioFemale);
    }

    public void setGenderSelection(String gender) {
        if (gender.equalsIgnoreCase("male")) {
            male.toggle();
        } else {
            female.toggle();
        }

    }

    public String getGenderSelection() {
        int selectedId = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(selectedId);
        return radioButton.getText().toString();
    }
}
