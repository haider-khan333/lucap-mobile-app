package com.fyp.lucapp.Fragments.Appointments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.fyp.lucapp.Adapters.Appointments.CompletedAdapter;
import com.fyp.lucapp.BasicModels.DAppointment;
import com.fyp.lucapp.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CompleteAppointmentFragment extends Fragment {

    private static final String ARG_DATA = "data_complete";
    private List<DAppointment> appointmentData;


    public static CompleteAppointmentFragment newInstance(List<DAppointment> data) {
        CompleteAppointmentFragment fragment = new CompleteAppointmentFragment();
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
        View view = inflater.inflate(R.layout.fragment_complete_appointment, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.completeAppointmentAdapter);

        List<DAppointment> appointmentData = onlyShowCompleteAppointments();

        CompletedAdapter adapter = new CompletedAdapter(
                appointmentData
        );
        recyclerView.setAdapter(adapter);
        return view;
    }

    private List<DAppointment> onlyShowCompleteAppointments() {
        List<DAppointment> completedAppointments = new ArrayList<>();
        for (DAppointment appointment : appointmentData) {
            if (appointment.getStatus().equals("complete")) {
                completedAppointments.add(appointment);
            }
        }
        return completedAppointments;
    }
}