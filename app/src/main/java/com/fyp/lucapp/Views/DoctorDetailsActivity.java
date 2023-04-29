package com.fyp.lucapp.Views;

import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fyp.lucapp.Components.AppointmentTiming.ComponentAppointmentTiming;
import com.fyp.lucapp.Components.ComponentCustomDialogue;
import com.fyp.lucapp.Components.DayNavigation.ComponentDayNavigation;
import com.fyp.lucapp.Interface.InterfaceApi;
import com.fyp.lucapp.R;
import com.google.android.flexbox.FlexboxLayout;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DoctorDetailsActivity extends AppCompatActivity implements InterfaceApi {


    private InterfaceApi interfaceApi;
    private FlexboxLayout flexBoxLayout;
    private ComponentDayNavigation componentDayNavigation;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        flexBoxLayout = findViewById(R.id.appointmentGrid);
        componentDayNavigation = findViewById(R.id.dayNavigation);

        componentDayNavigation.setBackArrowClickListener(v -> {
            Toast.makeText(this, "Back", Toast.LENGTH_SHORT).show();
            displayAppointmentsOnCalendar();
        });




        for (int i = 0; i < 10; i++) {
            ComponentAppointmentTiming appointmentItem = new ComponentAppointmentTiming(this);
            appointmentItem.setTiming("10:00 AM");
            flexBoxLayout.addView(appointmentItem);
        }


    }

    private void showAppointmentCalendar(List<Calendar> appointmentDates) {
        Calendar now = Calendar.getInstance();
        DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                        // Handle the selected date
                    }
                },
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );

        // Highlight appointment dates
        datePickerDialog.setHighlightedDays(appointmentDates.toArray(new Calendar[0]));

        datePickerDialog.show(getSupportFragmentManager(), "Datepickerdialog");
    }

    private List<Calendar> convertDatesToCalendars(ArrayList<String> appointmentDates) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        List<Calendar> calendars = new ArrayList<>();

        for (String dateString : appointmentDates) {
            try {
                Date date = sdf.parse(dateString);
                if (date != null) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(date);
                    calendars.add(calendar);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return calendars;
    }

    private void displayAppointmentsOnCalendar() {
        ArrayList<String> appointmentDates = new ArrayList<>();
        // Add your appointment dates as strings in the "yyyy-MM-dd" format
        appointmentDates.add("2023-05-01");
        appointmentDates.add("2023-05-03");
        appointmentDates.add("2023-05-05");

        List<Calendar> calendars = convertDatesToCalendars(appointmentDates);
        showAppointmentCalendar(calendars);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    @Override
    public void onSuccess(Object object) {


    }

    @Override
    public void onError(Object message) {
        ComponentCustomDialogue componentCustomDialogue = new
                ComponentCustomDialogue(this, "Error",
                message.toString(), R.raw.cancel_animation);
        componentCustomDialogue.onShow();

    }
}

