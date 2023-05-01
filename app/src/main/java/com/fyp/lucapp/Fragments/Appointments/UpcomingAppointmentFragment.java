package com.fyp.lucapp.Fragments.Appointments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.fyp.lucapp.Adapters.Appointments.OngoingAdapter;
import com.fyp.lucapp.BasicModels.DAppointmentAdapter;
import com.fyp.lucapp.R;

import java.io.Serializable;
import java.util.List;


public class UpcomingAppointmentFragment extends Fragment implements
        View.OnClickListener {

    private RecyclerView recyclerView;
    private List<DAppointmentAdapter> appointmentData;
    private static final String ARG_DATA = "data";

    public static UpcomingAppointmentFragment newInstance(List<DAppointmentAdapter> data) {
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
            appointmentData = (List<DAppointmentAdapter>)
                    getArguments().getSerializable(ARG_DATA);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_upcomming_appointment, container,
                false);

        recyclerView = view.findViewById(R.id.upcomingAppointmentAdapter);


        OngoingAdapter adapter = new OngoingAdapter();
        recyclerView.setAdapter(adapter);

        DividerItemDecoration dividerItemDecoration = new
                DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider_item));
        recyclerView.addItemDecoration(dividerItemDecoration);


        return view;
    }


    @Override
    public void onClick(View view) {

    }
}