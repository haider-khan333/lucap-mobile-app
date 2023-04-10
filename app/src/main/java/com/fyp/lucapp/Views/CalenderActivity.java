package com.fyp.lucapp.Views;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fyp.lucapp.Components.CustomDialogue;
import com.fyp.lucapp.BasicModels.Appointment;
import com.fyp.lucapp.BasicModels.CustomDate;
import com.fyp.lucapp.BasicModels.Data;
import com.fyp.lucapp.R;
import com.google.android.material.snackbar.Snackbar;

import java.sql.Time;
import java.util.Calendar;

public class CalenderActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView backButton;
    private CalendarView calendarView;
    private AutoCompleteTextView dropItems;
    private Button nextButton;

    private CustomDate selectedDate;
    private Time selectedTime;

    private CustomDialogue customDialogue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

        backButton = findViewById(R.id.backButton);
        calendarView = findViewById(R.id.calendarView);
        dropItems = findViewById(R.id.drop_items);
        nextButton = findViewById(R.id.nextButton);


        //back button
        backButton.setOnClickListener(v -> finish());

        //set the adapter for the drop down menu
        Time[] items = new Time[]{new Time(9, 0, 0),
                new Time(10, 0, 0),
                new Time(11, 0, 0), new Time(12, 0, 0),
                new Time(13, 0, 0), new Time(14, 0, 0),
                new Time(15, 0, 0), new Time(16, 0, 0),
                new Time(17, 0, 0), new Time(18, 0, 0),
                new Time(19, 0, 0), new Time(20, 0, 0)};
        ArrayAdapter<Time> adapter = new ArrayAdapter<>(this, R.layout.items_list, items);
        dropItems.setAdapter(adapter);

        //add listener on the drop down menu
        dropItems.setOnItemClickListener((parent, view, position, id) -> {
            selectedTime = (Time) parent.getItemAtPosition(position);
            System.out.println("Selected time: " + selectedTime);
        });


        //get the current date from the calendar
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            System.out.println("Selected date: " + year + "-" + month + "-" + dayOfMonth);
            selectedDate = new CustomDate(dayOfMonth, month, year);

        });

        nextButton.setOnClickListener(v -> {


            if (selectedDate == null) {
                Snackbar.make(v, "Please select a date", Snackbar.LENGTH_SHORT).show();
                return;
            }

            //check if the selected date is passed or not
            if (!isSelectedDateValid()) {
                customDialogue = new CustomDialogue(this,
                        "Invalid Date", "Date is passed, please select a valid date",
                        R.raw.cancel_animation);
                customDialogue.onShow();
                return;
            }

            if (selectedTime == null) {
                Snackbar.make(v, "Please select a time", Snackbar.LENGTH_SHORT).show();
                return;
            }

            if (!isSelectedTimeValid()) {
                customDialogue = new CustomDialogue(this,
                        "Invalid Time", "Time is passed, please select a valid time",
                        R.raw.cancel_animation);
                customDialogue.onShow();
                return;
            }


            //check if the selected time is already booked or not
            for (Appointment appointment : Data.appointments) {
                if (appointment.getAppointmentDate().equals(selectedDate) &&
                        appointment.getAppointmentTime().equals(selectedTime)) {
                    Toast.makeText(this, "This time slot is already booked", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            Toast.makeText(this, "Booked", Toast.LENGTH_SHORT).show();

//            //check if the selected time is in the working hours of the doctor or not
//            for (Doctors doctor : Data.doctors) {
//                if (doctor.getDoctorID().equals(Data.selectedDoctor.getDoctorID())) {
//                    if (selectedTime.getHours() < doctor.getWorkingHours().getStartTime().getHours() ||
//                            selectedTime.getHours() > doctor.getWorkingHours().getEndTime().getHours()) {
//                        Toast.makeText(this, "This time slot is not available", Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                }
//            }
//
//            //create a new appointment
//            Appointment appointment = new Appointment(generateAppointmentID(),
//                    Data.selectedDoctor.getDoctorID(),
//                    Data.currentUser.getUserID(),
//                    selectedDate,
//                    selectedTime,
//                    Enums.AppointmentStatus.PENDING.toString());
//
//            //add the appointment to the appointment list
//            Data.appointments.add(appointment);
//
//            //go to the next activity
//            startActivity(SelectPaymentMethodActivity.getIntent(this));

        });


    }

    /**
     * This method will generate a unique appointment id of 6 digits
     *
     * @return
     */
    private String generateAppointmentID() {
        //check if the current appointment id is already in the appointment list
        //if yes, then generate a new id
        //if no, then return the current id
        String appointmentID = String.valueOf((int) (Math.random() * 1000000));
        for (Appointment appointment : Data.appointments) {
            if (appointment.getAppointmentId().equals(appointmentID)) {
                appointmentID = String.valueOf((int) (Math.random() * 1000000));
            }
        }
        return appointmentID;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }


    private boolean isSelectedDateValid() {

        int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        int selectedDay = selectedDate.getDay();
        int selectedMonth = selectedDate.getMonth();
        int selectedYear = selectedDate.getYear();

        return selectedDay >= currentDay &&
                selectedMonth >= currentMonth
                && selectedYear >= currentYear;
    }


    private boolean isSelectedTimeValid() {
        int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int selectedHour = selectedTime.getHours();


        return selectedHour > currentHour;
    }

    @Override
    public void onClick(View view) {
        System.out.println("Clicked");
        customDialogue.onDismiss();

    }

}

