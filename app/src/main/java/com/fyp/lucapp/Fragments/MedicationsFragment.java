package com.fyp.lucapp.Fragments;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.fyp.lucapp.Adapters.MedicationsAdapterView;
import com.fyp.lucapp.Helper.AlarmReceiver;
import com.fyp.lucapp.Interface.AlarmInterface;
import com.fyp.lucapp.BasicModels.Data;
import com.fyp.lucapp.R;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.util.Calendar;

public class MedicationsFragment extends Fragment implements AlarmInterface {


    private MaterialTimePicker timePicker;
    private Calendar calendar;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    public MedicationsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_medications, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.medicationRecyclerView);
        recyclerView.setAdapter(new MedicationsAdapterView());
        createNotificationChannel();
        return view;
    }

    private void showDialogue(String messageToBeDisplayed, int animation) {
        View alertCustomDialog = LayoutInflater.from(getContext()).inflate(R.layout.custom_dialogue,
                null);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setView(alertCustomDialog);

        //change the message of dialogue message
        TextView message = alertCustomDialog.findViewById(R.id.message);
        message.setText(messageToBeDisplayed);
        AlertDialog dialog = alertDialog.create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        LottieAnimationView animationView = dialog.findViewById(R.id.animationView);
        animationView.setAnimation(animation);

    }

    private void showTimePicker() {
        timePicker = new MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(12)
                .setMinute(0)
                .setTitleText("Select Alarm Time")
                .build();

        timePicker.show(getParentFragmentManager(), "timePicker");

        timePicker.addOnPositiveButtonClickListener(v -> {
            int hour = timePicker.getHour();
            int minute = timePicker.getMinute();

            calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, minute);
            calendar.set(Calendar.SECOND, 0);

            //set the alarm
            setAlarm();
        });
    }

    private void setAlarm() {
        alarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getContext(), AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(getContext(), 1, intent, 0);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                pendingIntent);

        showDialogue("Alarm set successfully", R.raw.success_animation);
    }

    private void cancelAlarm() {
        alarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getContext(), AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(getContext(), 1, intent, 0);
        if (alarmManager == null) {
            showDialogue("No Alarm Set", R.raw.cancel_animation);
        }
        alarmManager.cancel(pendingIntent);
        showDialogue("Alarm is cancelled", R.raw.cancel_animation);


    }


    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "LucAppMedicationChannel";
            String description = "Channel for LucApp";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new android.app.NotificationChannel("LucApp",
                    name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getActivity().getSystemService(android.app.
                    NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


    @Override
    public void onSetAlarm(View view, int position) {
        System.out.println("Alarm clicked");
        showTimePicker();

    }

    @Override
    public void onCancelAlarm(View view, int position) {
        System.out.println("Cancel clicked");
        cancelAlarm();

    }
}