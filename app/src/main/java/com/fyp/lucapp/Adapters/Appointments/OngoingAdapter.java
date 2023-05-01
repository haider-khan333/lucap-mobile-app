package com.fyp.lucapp.Adapters.Appointments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fyp.lucapp.BasicModels.DAppointmentAdapter;
import com.fyp.lucapp.Components.OngoingAppointment.ComponentOngoingAppointment;
import com.fyp.lucapp.Interface.InterfaceClickItem;
import com.fyp.lucapp.R;

import java.util.List;

public class OngoingAdapter extends RecyclerView.Adapter<OngoingAdapter.OngoingViewHolder> {
    private List<DAppointmentAdapter> appointments;
    private InterfaceClickItem interfaceClickItem;

    public OngoingAdapter() {
    }

    public OngoingAdapter(List<DAppointmentAdapter> appointments) {
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
        DAppointmentAdapter appointment = appointments.get(position);
        holder.componentOngoingAppointment.setDocName(appointment.getDoctorName());
        holder.componentOngoingAppointment.setDocSpeciality(appointment.getDoctorSpeciality());
        holder.componentOngoingAppointment.setDocImage(appointment.getDoctorImage());
        holder.componentOngoingAppointment.setDateTiming(appointment.getDoctorTiming());



    }

    @Override
    public int getItemCount() {
        if (appointments != null) {
            return appointments.size();
        } else {
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
