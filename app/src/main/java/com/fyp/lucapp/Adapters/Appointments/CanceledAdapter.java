package com.fyp.lucapp.Adapters.Appointments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fyp.lucapp.BasicModels.DAppointmentAdapter;
import com.fyp.lucapp.Components.CancelledAppointment.ComponentCancelledAppointment;
import com.fyp.lucapp.Interface.InterfaceClickItem;
import com.fyp.lucapp.R;

import java.util.List;

public class CanceledAdapter extends RecyclerView.Adapter<CanceledAdapter.CanceledViewHolder> {
    private List<DAppointmentAdapter> appointments;
    private InterfaceClickItem interfaceClickItem;

    public CanceledAdapter() {
    }

    public CanceledAdapter(List<DAppointmentAdapter> appointments
            , InterfaceClickItem interfaceClickItem) {
        this.appointments = appointments;
        this.interfaceClickItem = interfaceClickItem;
    }


    @NonNull
    @Override
    public CanceledAdapter.CanceledViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                                 int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_appointment_canceled, parent, false);
        return new CanceledViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CanceledAdapter.CanceledViewHolder holder, int position) {
        DAppointmentAdapter appointment = appointments.get(position);
        holder.componentCancelledAppointment.setDocName(appointment.getDoctorName());
        holder.componentCancelledAppointment.setDocSpeciality(appointment.getDoctorSpeciality());
        holder.componentCancelledAppointment.setDocImage(appointment.getDoctorImage());

    }

    @Override
    public int getItemCount() {
        if (appointments != null) {
            return appointments.size();
        } else {
            return 0;
        }
    }

    public static class CanceledViewHolder extends RecyclerView.ViewHolder {
        private final ComponentCancelledAppointment componentCancelledAppointment;

        public CanceledViewHolder(@NonNull View itemView) {
            super(itemView);
            componentCancelledAppointment =
                    itemView.findViewById(R.id.component_cancelled_appointment);
        }
    }
}
