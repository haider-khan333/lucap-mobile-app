package com.fyp.lucapp.Adapters.Appointments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fyp.lucapp.BasicModels.DAppointment;
import com.fyp.lucapp.Components.CompleteAppointment.ComponentCompleteAppointment;
import com.fyp.lucapp.Interface.InterfaceClickItem;
import com.fyp.lucapp.R;

import java.util.List;

public class CompletedAdapter extends RecyclerView.Adapter<CompletedAdapter.CompletedViewHolder> {

    private List<DAppointment> appointments;
    private InterfaceClickItem interfaceClickItem;

    public CompletedAdapter() {
    }

    public CompletedAdapter(List<DAppointment> appointments) {
        this.appointments = appointments;
    }

    @NonNull
    @Override
    public CompletedAdapter.CompletedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_appointment_completed, parent, false);
        return new CompletedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompletedAdapter.CompletedViewHolder holder, int position) {
        DAppointment appointment = appointments.get(position);
        holder.componentCompleteAppointment.setDocName(appointment.getDoctorName());
        holder.componentCompleteAppointment.setDocSpeciality(appointment.getDoctorSpeciality());
        holder.componentCompleteAppointment.setDocImage(appointment.getDoctorImage());
        holder.componentCompleteAppointment.setDateTiming(appointment.getDoctorTiming());


    }

    @Override
    public int getItemCount() {
        if (appointments != null) {
            System.out.println("size Completed " + appointments.size());
            return appointments.size();
        } else {
            System.out.println("size Completed 0");
            return 0;
        }
    }

    public static class CompletedViewHolder extends RecyclerView.ViewHolder {
        private final ComponentCompleteAppointment componentCompleteAppointment;

        public CompletedViewHolder(@NonNull View itemView) {
            super(itemView);
            componentCompleteAppointment = itemView.
                    findViewById(R.id.component_complete_appointment);
        }
    }
}
