package com.fyp.lucapp.Components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.fyp.lucapp.R;

public class ComponentMedicine extends LinearLayout {

    private TextView medicineName;
    private TextView medicineGrams;
    private TextView medicineFrequency;
    private TextView medicineDosage;
    private TextView addAlarm;
    private TextView cancelAlarm;

    private TextView alarmInfo;


    public ComponentMedicine(Context context) {
        super(context);
        init();
    }

    public ComponentMedicine(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ComponentMedicine(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).
                inflate(R.layout.component_medicine_card, this, true);
        medicineName = view.findViewById(R.id.medicine_name);
        medicineGrams = view.findViewById(R.id.medicine_grams);
        medicineFrequency = view.findViewById(R.id.medicine_frequency);
        medicineDosage = view.findViewById(R.id.medicine_dosage);
        addAlarm = view.findViewById(R.id.add_alarm);
        cancelAlarm = view.findViewById(R.id.cancel_alarm);
        alarmInfo = view.findViewById(R.id.alarm_info);
    }

    public void setMedicineName(String name) {
        medicineName.setText(name);
    }

    public void setMedicineGrams(String grams) {
        medicineGrams.setText(grams);
    }

    public void setMedicineFrequency(String frequency) {
        medicineFrequency.setText(frequency);
    }

    public void setMedicineDosage(String dosage) {
        medicineDosage.setText(dosage);
    }


    public TextView getAddAlarm() {
        return addAlarm;
    }

    public TextView getCancelAlarm() {
        return cancelAlarm;
    }

    public TextView getAlarmInfo() {
        return alarmInfo;
    }


}
