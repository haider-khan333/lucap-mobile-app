package com.fyp.lucapp.Adapters.Appointments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fyp.lucapp.BasicModels.DAppointment;
import com.fyp.lucapp.Components.CancelledAppointment.ComponentCancelledAppointment;
import com.fyp.lucapp.Interface.InterfaceClickItem;
import com.fyp.lucapp.R;

import java.util.List;

public class CanceledAdapter extends RecyclerView.Adapter<CanceledAdapter.CanceledViewHolder> {
    private List<DAppointment> appointments;
    private InterfaceClickItem interfaceClickItem;

    public CanceledAdapter() {
    }

    public CanceledAdapter(List<DAppointment> appointments) {
        this.appointments = appointments;
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
        DAppointment appointment = appointments.get(position);
        holder.componentCancelledAppointment.setDocName(appointment.getDoctorName());
        holder.componentCancelledAppointment.setDocSpeciality(appointment.getDoctorSpeciality());
        holder.componentCancelledAppointment.setDocImage(appointment.getDoctorImage());

    }

    @Override
    public int getItemCount() {
        if (appointments != null) {
            System.out.println("size Canceled " + appointments.size());
            return appointments.size();
        } else {
            System.out.println("size Canceled 0");

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
