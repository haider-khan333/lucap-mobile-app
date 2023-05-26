package com.fyp.lucapp.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.fyp.lucapp.BasicModels.DAppointment;
import com.fyp.lucapp.Fragments.Appointments.CancelAppointmentFragment;
import com.fyp.lucapp.Fragments.Appointments.CompleteAppointmentFragment;
import com.fyp.lucapp.Fragments.Appointments.UpcomingAppointmentFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentStateAdapter {

    private final List<DAppointment> upcomingAppointmentData;
    private final List<DAppointment> completeAppointmentData;
    private final List<DAppointment> cancelledAppointmentData;

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity,
                            List<DAppointment> appointmentData) {
        super(fragmentActivity);

        this.upcomingAppointmentData = onlyShowUpcomingAppointments(appointmentData);
        this.completeAppointmentData = onlyShowCompleteAppointments(appointmentData);
        this.cancelledAppointmentData = onlyShowCancelledAppointments(appointmentData);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return UpcomingAppointmentFragment.newInstance(upcomingAppointmentData);
        } else if (position == 1) {
            return CompleteAppointmentFragment.newInstance(completeAppointmentData);
        } else {
            return CancelAppointmentFragment.newInstance(cancelledAppointmentData);
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    private List<DAppointment> onlyShowCompleteAppointments(List<DAppointment> appointmentData) {
        List<DAppointment> completeAppointmentData = new ArrayList<>();
        for (DAppointment appointment : appointmentData) {
            if (appointment.getStatus().equals("completed")) {
                completeAppointmentData.add(appointment);
            }
        }
        return completeAppointmentData;
    }

    private List<DAppointment> onlyShowCancelledAppointments(List<DAppointment> appointmentData) {
        List<DAppointment> cancelledAppointmentData = new ArrayList<>();
        for (DAppointment appointment : appointmentData) {
            if (appointment.getStatus().equals("cancel")) {
                cancelledAppointmentData.add(appointment);
            }
        }
        return cancelledAppointmentData;
    }

    private List<DAppointment> onlyShowUpcomingAppointments(List<DAppointment> appointmentData) {
        List<DAppointment> upcomingAppointmentData = new ArrayList<>();
        for (DAppointment appointment : appointmentData) {
            if (appointment.getStatus().equals("ongoing")) {
                upcomingAppointmentData.add(appointment);
            }
        }
        return upcomingAppointmentData;
    }
}

