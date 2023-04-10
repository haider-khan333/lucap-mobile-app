package com.fyp.lucapp.Adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.fyp.lucapp.Fragments.Appointments.CancelAppointmentFragment;
import com.fyp.lucapp.Fragments.Appointments.CompleteAppointmentFragment;
import com.fyp.lucapp.Fragments.Appointments.UpcomingAppointmentFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new UpcomingAppointmentFragment();
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
            return "Upcoming";
        } else if (position == 1) {
            return "Completed";
        } else {
            return "Cancelled";
        }
    }
}
