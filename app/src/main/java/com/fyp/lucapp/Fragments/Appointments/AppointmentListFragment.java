package com.fyp.lucapp.Fragments.Appointments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.fyp.lucapp.Adapters.ViewPagerAdapter;
import com.fyp.lucapp.Interface.AdapterInterface;
import com.fyp.lucapp.R;
import com.fyp.lucapp.databinding.ReportCardViewBinding;
import com.google.android.material.tabs.TabLayout;

public class AppointmentListFragment extends Fragment implements AdapterInterface {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    public AppointmentListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_appointment, container,
                false);
        viewPager = view.findViewById(R.id.viewPager);
        tabLayout = view.findViewById(R.id.tabLayout);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

    @Override
    public void onDownloadClicked(ReportCardViewBinding binding, int position) {

    }

    @Override
    public void onClick(View view, int position) {

    }
}