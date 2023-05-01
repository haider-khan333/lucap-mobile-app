package com.fyp.lucapp.Adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.fyp.lucapp.BasicModels.DAppointmentAdapter;
import com.fyp.lucapp.Fragments.Appointments.CancelAppointmentFragment;
import com.fyp.lucapp.Fragments.Appointments.CompleteAppointmentFragment;
import com.fyp.lucapp.Fragments.Appointments.UpcomingAppointmentFragment;

import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<DAppointmentAdapter> appointmentData;

    public ViewPagerAdapter(@NonNull FragmentManager fm,
                            List<DAppointmentAdapter> appointmentData) {
        super(fm);
        this.appointmentData = appointmentData;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return UpcomingAppointmentFragment.newInstance(appointmentData);
        } else if (position == 1) {
            return new CompleteAppointmentFragment();
        } else {
            return new CancelAppointmentFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return "Ongoing";
        } else if (position == 1) {
            return "Completed";
        } else {
            return "Cancelled";
        }
    }
}
