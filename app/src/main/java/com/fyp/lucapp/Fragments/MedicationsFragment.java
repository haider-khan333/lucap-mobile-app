package com.fyp.lucapp.Fragments;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.fyp.lucapp.Adapters.MedicineAdapterView;
import com.fyp.lucapp.BasicModels.Medications;
import com.fyp.lucapp.Components.ComponentCustomDialogue;
import com.fyp.lucapp.Components.ComponentLoader;
import com.fyp.lucapp.Helper.AlarmReceiver;
import com.fyp.lucapp.Helper.Helper;
import com.fyp.lucapp.Helper.LoaderUtils;
import com.fyp.lucapp.Helper.URL;
import com.fyp.lucapp.Interface.InterfaceAlarm;
import com.fyp.lucapp.Interface.InterfaceApi;
import com.fyp.lucapp.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MedicationsFragment extends Fragment implements InterfaceApi,
        InterfaceAlarm {

    private Map<Integer, List<PendingIntent>> pendingIntentMap;
    private RecyclerView recyclerView;
    private ArrayList<Medications> medicationsList;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ComponentLoader componentLoader;
    private URL url;

    public MedicationsFragment() {
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
        recyclerView = view.findViewById(R.id.medicationRecyclerView);
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        componentLoader = view.findViewById(R.id.fpComponentLoader);
        recyclerView.setAdapter(new MedicineAdapterView());
        pendingIntentMap = new HashMap<>();

        url = new URL(getContext(), this);
        swipeRefreshLayout.setOnRefreshListener(this::refreshData);

        EditText searchEditText = view.findViewById(R.id.editName);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterReports(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        DividerItemDecoration dividerItemDecoration = new
                DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider_item));
        recyclerView.addItemDecoration(dividerItemDecoration);


        componentLoader.setAnimation(R.raw.loader_anim);
        LoaderUtils.showLoader(componentLoader);
        url.getMedicines();
        return view;
    }

    private void filterReports(String text) {
        boolean found = false;
        ArrayList<Medications> filteredList = new ArrayList<>();

        for (Medications item : medicationsList) {
            if (item.getMedicineName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
                found = true;
            }
        }

        MedicineAdapterView adapterView = new MedicineAdapterView(filteredList,
                this);
        recyclerView.setAdapter(adapterView);
        adapterView.notifyDataSetChanged();

        if (!found) {
            componentLoader.setAnimation(R.raw.not_found_anim);
            LoaderUtils.showLoader(componentLoader);
        } else {
            LoaderUtils.hideLoader(componentLoader);
        }

    }

    private void refreshData() {
        if (swipeRefreshLayout.isRefreshing()) {
            url.getMedicines();
        }
    }


    private void setDefaultAlarm(Medications medications, int position) {

        int medicineDosage = Integer.parseInt(medications.getMedicineDosage());
//        int medicineDosage = 4;

        if (medicineDosage == 1) {
            //set alarm for 1 time a day
            Calendar calender = Calendar.getInstance();
            calender.set(Calendar.HOUR_OF_DAY, 12);
            calender.set(Calendar.MINUTE, 0);
            calender.set(Calendar.SECOND, 0);

            List<Calendar> calendarList = new ArrayList<>();
            calendarList.add(calender);
            setAlarm(position, calendarList);
        } else if (medicineDosage == 2) {
            //set alarm for 2 times a day
            Calendar calender = Calendar.getInstance();
            calender.set(Calendar.HOUR_OF_DAY, 6);
            calender.set(Calendar.MINUTE, 0);
            calender.set(Calendar.SECOND, 0);

            Calendar calender2 = Calendar.getInstance();
            calender2.set(Calendar.HOUR_OF_DAY, 12);
            calender2.set(Calendar.MINUTE, 0);
            calender2.set(Calendar.SECOND, 0);

            List<Calendar> calendarList = new ArrayList<>();
            calendarList.add(calender);
            calendarList.add(calender2);
            setAlarm(position, calendarList);
        } else if (medicineDosage == 3) {
            //set alarm for 3 times a day
            Calendar calender = Calendar.getInstance();
            calender.set(Calendar.HOUR_OF_DAY, 6);
            calender.set(Calendar.MINUTE, 0);
            calender.set(Calendar.SECOND, 0);

            Calendar calender2 = Calendar.getInstance();
            calender2.set(Calendar.HOUR_OF_DAY, 12);
            calender2.set(Calendar.MINUTE, 0);
            calender2.set(Calendar.SECOND, 0);

            Calendar calender3 = Calendar.getInstance();
            calender3.set(Calendar.HOUR_OF_DAY, 18);
            calender3.set(Calendar.MINUTE, 0);
            calender3.set(Calendar.SECOND, 0);

            List<Calendar> calendarList = new ArrayList<>();
            calendarList.add(calender);
            calendarList.add(calender2);
            calendarList.add(calender3);
            setAlarm(position, calendarList);
        } else {
            //set alarm for current time
            Calendar calender = Calendar.getInstance();
            calender.set(Calendar.HOUR_OF_DAY, 23);
            calender.set(Calendar.MINUTE, 3);
            calender.set(Calendar.SECOND, 0);

            List<Calendar> calendarList = new ArrayList<>();
            calendarList.add(calender);
            setAlarm(position, calendarList);
        }

    }


    private void setAlarm(int position, List<Calendar> calendarList) {
        List<PendingIntent> pendingIntentList = new ArrayList<>();
        Calendar currentTime = Calendar.getInstance();
        Calendar setTime = null;

        // Calculate 1 minute in milliseconds
        long repeatInterval = 60 * 1000;
        for (int i = 0; i < calendarList.size(); i++) {
            Calendar calendar = calendarList.get(i);

            if (calendar.before(currentTime)) {
                calendar.add(Calendar.DATE, 1);
            }

            Intent intent = new Intent(getContext(), AlarmReceiver.class);
            intent.putExtra("medicineName", medicationsList.get(position).getMedicineName());
            pendingIntentMap.put(position, null);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                pendingIntentList.add(i, PendingIntent.getBroadcast(getContext(),
                        0, intent, PendingIntent.FLAG_IMMUTABLE));
            } else {
                System.out.println("Alarm not set");
            }
            AlarmManager alarmManager = (AlarmManager) getContext().getSystemService(getContext().ALARM_SERVICE);
            if (alarmManager != null) {
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                        repeatInterval, pendingIntentList.get(i));
                System.out.println("Alarm set for " + calendar.getTime());
                setTime = calendar;
            } else {
                System.out.println("Alarm not set");
            }

        }
        pendingIntentMap.put(position, pendingIntentList);
        if (setTime != null) {
            //calculate the remaining time upto setTIME FROM THE CURRENT TIME
            long remainingTime = setTime.getTimeInMillis() - currentTime.getTimeInMillis();
            long remainingHours = remainingTime / (60 * 60 * 1000);
            long remainingMinutes = (remainingTime / (60 * 1000)) % 60;
            long remainingSeconds = (remainingTime / 1000) % 60;


            Toast.makeText(getContext(), "Alarm set for " + remainingHours + " hours "
                            + remainingMinutes + " minutes " +
                            remainingSeconds + " seconds",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Alarm not set",
                    Toast.LENGTH_SHORT).show();
        }

        System.out.println("After setting alarms, pending intent map is " +
                pendingIntentMap.toString());


    }

    private void showBottomSheet() {
        View bottomSheetView = LayoutInflater.from(getContext()).
                inflate(R.layout.layout_bottom_sheet, null);
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
        bottomSheetDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        bottomSheetDialog.setContentView(bottomSheetView);
        bottomSheetDialog.show();
    }

    private void cancelAlarm(int position) {
        List<PendingIntent> pendingIntentList = pendingIntentMap.get(position);

        //cancel all the alarms inside the pending list
        if (pendingIntentList != null) {
            for (int i = 0; i < pendingIntentList.size(); i++) {
                if (pendingIntentList.get(i) != null) {
                    AlarmManager alarmManager = (AlarmManager) getContext().getSystemService(getContext().ALARM_SERVICE);
                    if (alarmManager != null) {
                        alarmManager.cancel(pendingIntentList.get(i));
                        pendingIntentList.get(i).cancel();
                        pendingIntentList.set(i, null);
                        pendingIntentMap.put(position, pendingIntentList);
                    }
                }
            }
            Toast.makeText(getContext(), "Alarm cancelled", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "No alarm to cancel", Toast.LENGTH_SHORT).show();
        }
        System.out.println("After cancelling alarm, pending intent map is " +
                pendingIntentMap.toString());
    }

    @Override
    public void onSuccess(Object object) {
        LoaderUtils.hideLoader(componentLoader);
        JSONArray medicineList = (JSONArray) object;
        medicationsList = Helper.getMedicines(medicineList);

        MedicineAdapterView adapterView = new MedicineAdapterView(medicationsList,
                this);
        recyclerView.setAdapter(adapterView);
        adapterView.notifyDataSetChanged();

        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }


    }

    @Override
    public void onError(Object message) {
        LoaderUtils.hideLoader(componentLoader);
        ComponentCustomDialogue componentCustomDialogue = new ComponentCustomDialogue
                (getContext(),
                        "Invalid request", message.toString(),
                        R.raw.cancel_animation);
        componentCustomDialogue.onShow();


    }

    @Override
    public void OnClick(View view, int position, Object object) {
        switch (view.getId()) {
            case R.id.add_alarm:
                Medications medications = (Medications) object;
                setDefaultAlarm(medications, position);
                break;
            case R.id.cancel_alarm:
                cancelAlarm(position);
                break;
            case R.id.alarm_info:
                showBottomSheet();
                break;
        }

    }
}