package com.fyp.lucapp.Fragments.Appointments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.fyp.lucapp.Adapters.Appointments.OngoingAdapter;
import com.fyp.lucapp.BasicModels.DAppointment;
import com.fyp.lucapp.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class UpcomingAppointmentFragment extends Fragment implements
        View.OnClickListener {

    private List<DAppointment> appointmentData;
    private static final String ARG_DATA = "data_ongoing";

    public static UpcomingAppointmentFragment newInstance(List<DAppointment> data) {
        UpcomingAppointmentFragment fragment = new UpcomingAppointmentFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATA, (Serializable) data);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            appointmentData = (List<DAppointment>)
                    getArguments().getSerializable(ARG_DATA);
        }


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_upcomming_appointment, container,
                false);

        RecyclerView recyclerView = view.findViewById(R.id.upcomingAppointmentAdapter);
        List<DAppointment> appointmentData = onlyShowOngoingAppointments();
        OngoingAdapter adapter = new OngoingAdapter(
                appointmentData
        );
        recyclerView.setAdapter(adapter);


        return view;
    }


    private List<DAppointment> onlyShowOngoingAppointments() {
        List<DAppointment> ongoingAppointments = new ArrayList<>();
        for (DAppointment appointment : appointmentData) {
            if (appointment.getStatus().equals("ongoing")) {
                ongoingAppointments.add(appointment);
            }
        }
        return ongoingAppointments;
    }


    @Override
    public void onClick(View view) {

    }
}