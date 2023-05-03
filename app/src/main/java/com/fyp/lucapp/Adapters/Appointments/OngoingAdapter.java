package com.fyp.lucapp.Adapters.Appointments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fyp.lucapp.BasicModels.DAppointment;
import com.fyp.lucapp.Components.OngoingAppointment.ComponentOngoingAppointment;
import com.fyp.lucapp.Interface.InterfaceClickItem;
import com.fyp.lucapp.R;

import java.util.List;

public class OngoingAdapter extends RecyclerView.Adapter<OngoingAdapter.OngoingViewHolder> {
    private List<DAppointment> appointments;
    private InterfaceClickItem interfaceClickItem;

    public OngoingAdapter() {
    }

    public OngoingAdapter(List<DAppointment> appointments) {
        this.appointments = appointments;
    }


    @NonNull
    @Override
    public OngoingAdapter.OngoingViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_appointment_ongoing, parent, false);
        return new OngoingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OngoingAdapter.OngoingViewHolder holder, int position) {
        DAppointment appointment = appointments.get(position);
        holder.componentOngoingAppointment.setDocName(appointment.getDoctorName());
        holder.componentOngoingAppointment.setDocSpeciality(appointment.getDoctorSpeciality());
        holder.componentOngoingAppointment.setDocImage(appointment.getDoctorImage());
        holder.componentOngoingAppointment.setDateTiming(appointment.getDoctorTiming());



    }

    @Override
    public int getItemCount() {
        if (appointments != null) {
            System.out.println("size Ongoing : " + appointments.size());
            return appointments.size();

        } else {
            System.out.println("size Ongoing : 0");
            return 0;
        }
    }

    public static class OngoingViewHolder extends RecyclerView.ViewHolder {
        private final ComponentOngoingAppointment componentOngoingAppointment;

        public OngoingViewHolder(@NonNull View itemView) {
            super(itemView);
            componentOngoingAppointment = itemView.findViewById(R.id.component_ongoing_appointment);

        }
    }
}
