package com.fyp.lucapp.Views.DoctorDetails;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.fyp.lucapp.BasicModels.DDoctor;
import com.fyp.lucapp.BasicModels.DSpecificDoctor;
import com.fyp.lucapp.Components.AppointmentTiming.ComponentAppointmentTiming;
import com.fyp.lucapp.Components.ComponentDoctorList;
import com.fyp.lucapp.Components.DayNavigation.ComponentDayNavigation;
import com.fyp.lucapp.Components.DoctorExperience.ComponentDoctorExperience;
import com.fyp.lucapp.Components.Header.ComponentHeader;
import com.fyp.lucapp.Helper.Helper;
import com.fyp.lucapp.Helper.URL;
import com.fyp.lucapp.Interface.InterfaceApi;
import com.fyp.lucapp.Interface.InterfaceAppointments;
import com.fyp.lucapp.Main;
import com.fyp.lucapp.R;
import com.fyp.lucapp.Routes.RouteGet;
import com.fyp.lucapp.Routes.RoutePut;
import com.fyp.lucapp.Routes.Url;
import com.fyp.lucapp.Schemas.ConfirmAppointmentSchema;
import com.fyp.lucapp.Utils.UtilsDialogue;
import com.google.android.flexbox.FlexboxLayout;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.json.JSONObject;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DoctorDetailsActivity extends AppCompatActivity implements InterfaceApi,
        View.OnClickListener {

    private FlexboxLayout flexBoxLayout;
    private ComponentDayNavigation componentDayNavigation;
    private ComponentDoctorList componentDoctorList;
    private ComponentDoctorExperience componentDoctorExperience;
    private TextView aboutDoctor;
    private int pointer = 0;
    private DSpecificDoctor dSpecificDoctor;
    private LinkedList<Map<String, List<Integer>>> map;
    private BottomSheetDialog bottomSheetDialog;
    private String url = "";
    private String gDay;
    private int gTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        flexBoxLayout = findViewById(R.id.appointmentGrid);
        componentDayNavigation = findViewById(R.id.dayNavigation);
        componentDoctorList = findViewById(R.id.dd_doctorList);
        ComponentHeader componentHeader = findViewById(R.id.dd_header);
        aboutDoctor = findViewById(R.id.doctorDescription);
        componentDoctorExperience = findViewById(R.id.dd_doctorExperience);
        TextView contactDoctor = findViewById(R.id.dd_contactDoctor);


        DDoctor doctorsData =
                ((DDoctor) getIntent().getSerializableExtra("doctor"));
        System.out.println("DoctorDetailsActivity:: doctor data : " + doctorsData);


        componentHeader.setTitle("Doctor Details");

        componentHeader.addClickListener(this);
        componentDayNavigation.setBackArrowClickListener(this);
        componentDayNavigation.setFrontArrowClickListener(this);
        contactDoctor.setOnClickListener(this);


        loadDataFromApi_GetDoctors(doctorsData.getId());


    }

    private void loadDataFromApi_GetDoctors(int id) {
        this.url = "GET_DOCTORS";
        RouteGet routeGet = new RouteGet(this, this);
        routeGet.get(Url.GET_DOCTORS + "/" + id + "/" + URL.LOGGED_IN_PATIENT_ID);

    }

    private void loadDataFromApi_ConfirmAppointment(String url,
                                                    ConfirmAppointmentSchema confirmAppointment) {
        this.url = "CONFIRM_APPOINTMENT";
        RoutePut routePut = new RoutePut(this, this);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("doctor_id", confirmAppointment.getDoctorID());
            jsonObject.put("day", confirmAppointment.getDay());
            jsonObject.put("time", confirmAppointment.getTime());
        } catch
        (Exception e) {
            e.printStackTrace();
        }

        System.out.println("confirm appointment : " + jsonObject.toString());
        routePut.put(Url.CONFIRM_APPOINTMENT + "/" + URL.LOGGED_IN_PATIENT_ID, jsonObject);
    }

    private void setComponentData(DSpecificDoctor dSpecificDoctor) {
        componentDoctorList.setDoctorPhone(dSpecificDoctor.getPhone());
        componentDoctorList.setDoctorName(dSpecificDoctor.getUsername());
        componentDoctorList.setDoctorSpeciality(dSpecificDoctor.getSpeciality());
        componentDoctorList.setDoctorEmail(dSpecificDoctor.getEmail());
        componentDoctorList.setDoctorImage(dSpecificDoctor.getImage());

        componentDoctorExperience.setExperience(String.
                valueOf(dSpecificDoctor.getYearsOfExperience()));
        componentDoctorExperience.setPatient(String.
                valueOf(dSpecificDoctor.getNumberOfPatients()));
        componentDoctorExperience.setRating(String.
                valueOf(dSpecificDoctor.getCurrentRatings()));
        componentDoctorExperience.setReviews(String.
                valueOf(dSpecificDoctor.getCurrentReviews().size()));
        System.out.println("reviews length : " + dSpecificDoctor.getCurrentReviews());

        aboutDoctor.setText(dSpecificDoctor.getAbout());
        map = dSpecificDoctor.getAvailableTimes();
        showAppointments(map.get(0));


    }

    private void showAppointments(Map<String, List<Integer>> appointmentsDict) {
        flexBoxLayout.removeAllViews();
        for (Map.Entry<String, List<Integer>> entry : appointmentsDict.entrySet()) {
            String key = entry.getKey();
            List<Integer> value = entry.getValue();
            componentDayNavigation.setCurrentDay(key);

            if (value.size() == 0) {
                TextView textView = new TextView(this);
                textView.setText("No appointments available");
                textView.setTextSize(15);
                textView.setTextColor(Color.GRAY);
                textView.setGravity(Gravity.CENTER);
                textView.setTypeface(ResourcesCompat.getFont(this, R.font.poppins_light));
                flexBoxLayout.addView(textView);
            }

            for (int i = 0; i < value.size(); i++) {
                ComponentAppointmentTiming componentAppointmentTiming =
                        new ComponentAppointmentTiming(this);
                String startTime = value.get(i).toString();
                String endTime = String.valueOf(Integer.parseInt(startTime) + 1);
                componentAppointmentTiming.setTiming(startTime + ":00 - " + endTime + ":00");

                componentAppointmentTiming.setOnClickListener(view -> confirmAppointment(startTime, key));
                flexBoxLayout.addView(componentAppointmentTiming);
            }
        }
    }

    private void confirmAppointment(String startTime, String day) {
        bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.layout_bottom_sheet_appointment);
        bottomSheetDialog.setCanceledOnTouchOutside(false);
        bottomSheetDialog.show();
        String doctorName = dSpecificDoctor.getUsername();
        String doctorSpeciality = dSpecificDoctor.getSpeciality();

        TextView confirmAppointmentText = bottomSheetDialog.
                findViewById(R.id.confirmAppointmentText);
        TextView confirmAppointmentButton = bottomSheetDialog.
                findViewById(R.id.confirmAppointment);
        ImageView cancelAppointmentButton = bottomSheetDialog.
                findViewById(R.id.cancelAppointment);


        DayOfWeek dayOfWeek = DayOfWeek.valueOf(day.toUpperCase());
        LocalDate nextDate = getNextOccurrenceOfDay(dayOfWeek);
        DateTimeFormatter formatter = null;
        startTime = startTime + ":00";
        String formattedDate = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
            formattedDate = nextDate.format(formatter);
        }

        String date = formattedDate;
        String duration = "60 minutes";

        String template = "I confirm the appointment with %s, of department %s," +
                " on %s at %s for a total time of %s.";
        String fullText = String.format(template, doctorName, doctorSpeciality,
                date, startTime, duration);

        gDay = day;
        gTime = Integer.parseInt(startTime.split(":")[0]);

        SpannableString spannableText = new SpannableString(fullText);

        int doctorNameStart = fullText.indexOf(doctorName);
        int doctorNameEnd = doctorNameStart + doctorName.length();
        spannableText.setSpan(new UnderlineSpan(), doctorNameStart, doctorNameEnd, 0);
        spannableText.setSpan(new StyleSpan(Typeface.BOLD), doctorNameStart, doctorNameEnd, 0);

        int departmentStart = fullText.indexOf(doctorSpeciality);
        int departmentEnd = departmentStart + doctorSpeciality.length();
        spannableText.setSpan(new UnderlineSpan(), departmentStart, departmentEnd, 0);
        spannableText.setSpan(new StyleSpan(Typeface.BOLD), departmentStart, departmentEnd, 0);

        int dateStart = fullText.indexOf(date);
        int dateEnd = dateStart + date.length();
        spannableText.setSpan(new UnderlineSpan(), dateStart, dateEnd, 0);
        spannableText.setSpan(new StyleSpan(Typeface.BOLD), dateStart, dateEnd, 0);

        int timeStart = fullText.indexOf(startTime);
        int timeEnd = timeStart + duration.length();
        spannableText.setSpan(new UnderlineSpan(), timeStart, timeEnd, 0);
        spannableText.setSpan(new StyleSpan(Typeface.BOLD), timeStart, timeEnd, 0);

        int durationStart = fullText.indexOf(duration);
        int durationEnd = durationStart + duration.length();
        spannableText.setSpan(new UnderlineSpan(), durationStart, durationEnd, 0);
        spannableText.setSpan(new StyleSpan(Typeface.BOLD), durationStart, durationEnd, 0);

        confirmAppointmentText.setText(spannableText);

        confirmAppointmentButton.setOnClickListener(this);
        cancelAppointmentButton.setOnClickListener(this);


    }

    private LocalDate getNextOccurrenceOfDay(DayOfWeek dayOfWeek) {
        LocalDate today = null;
        LocalDate nextOccurrence = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            today = LocalDate.now();
            nextOccurrence = today.with(TemporalAdjusters.next(dayOfWeek));
        }
        return nextOccurrence;
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    @Override
    public void onSuccess(JSONObject response) {
        if (this.url.equals("GET_DOCTORS")) {
            try {
                JSONObject doctorJson = response.getJSONObject("doctors_list");
                dSpecificDoctor = Helper.getSpecificDoctorDetails(doctorJson);
                setComponentData(dSpecificDoctor);

            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Appointment confirmed", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, Main.class);
            startActivity(intent);
            this.finish();
        }


    }


    @Override
    public void onError(Object message) {
        UtilsDialogue.showErrorDialogue(message.toString(), this);

    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.back_arrow) {
            if (pointer == 0) {
                showAppointments(map.getLast());
                pointer = map.size() - 1;
            } else {
                showAppointments(map.get(pointer - 1));
                pointer--;
            }
        } else if (id == R.id.front_arrow) {
            if (pointer == map.size() - 1) {
                showAppointments(map.getFirst());
                pointer = 0;
            } else {
                showAppointments(map.get(pointer + 1));
                pointer++;
            }
        } else if (id == R.id.headerBackButton) {
            onBackPressed();
        } else if (id == R.id.confirmAppointment) {


            ConfirmAppointmentSchema confirmAppointment = new ConfirmAppointmentSchema();
            confirmAppointment.setDoctorID(dSpecificDoctor.getId());
            confirmAppointment.setDay(gDay);
            confirmAppointment.setTime(gTime);
            bottomSheetDialog.dismiss();

            loadDataFromApi_ConfirmAppointment(Url.CONFIRM_APPOINTMENT + "/" + URL.LOGGED_IN_PATIENT_ID,
                    confirmAppointment);

        } else if (id == R.id.cancelAppointment) {
            bottomSheetDialog.dismiss();
        } else if (id == R.id.dd_contactDoctor) {
            // Create an AlertDialog.Builder and set the message.
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Contact Doctor Via:");
            builder.setItems(new CharSequence[]
                            {"WhatsApp", "SMS", "Gmail"},
                    (dialog, which) -> {
                        // The 'which' argument contains the index position
                        // of the selected item
                        switch (which) {
                            case 0: // WhatsApp
                                Uri uri = Uri.parse("smsto:" + dSpecificDoctor.getPhone());
                                Intent i = new Intent(Intent.ACTION_SENDTO, uri);
                                i.setPackage("com.whatsapp");
                                startActivity(Intent.createChooser(i, ""));
                                break;
                            case 1: // SMS
                                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                                sendIntent.setData(Uri.parse("sms:" + dSpecificDoctor.getPhone()));
                                startActivity(sendIntent);
                                break;
                            case 2: // Gmail
                                Intent email = new Intent(Intent.ACTION_SEND);
                                email.putExtra(Intent.EXTRA_EMAIL, new String[]{dSpecificDoctor.getEmail()});
                                email.setType("message/rfc822");
                                startActivity(Intent.createChooser(email, "Choose an Email client :"));
                                break;
                        }
                    });
            builder.create().show();
        }
    }
}

