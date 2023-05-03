package com.fyp.lucapp.Fragments.Appointments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.fyp.lucapp.Adapters.ViewPagerAdapter;
import com.fyp.lucapp.BasicModels.DAppointment;
import com.fyp.lucapp.Components.ComponentCustomDialogue;
import com.fyp.lucapp.Components.ComponentLoader;
import com.fyp.lucapp.Helper.Helper;
import com.fyp.lucapp.Helper.URL;
import com.fyp.lucapp.Interface.InterfaceApi;
import com.fyp.lucapp.R;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;

import java.util.List;

public class AppointmentListFragment extends Fragment implements InterfaceApi {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private List<DAppointment> appointmentData;
    private ComponentLoader componentLoader;

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
        componentLoader = view.findViewById(R.id.componentLoader);

        viewPager.setVisibility(View.GONE);
        tabLayout.setVisibility(View.GONE);
        componentLoader.setVisibility(View.VISIBLE);

        loadDataFromApi();


        return view;
    }


    public void loadDataFromApi() {
        URL url = new URL(getContext(), this);
        url.getAppointments();
    }

    @Override
    public void onSuccess(Object object) {

        JSONArray data = (JSONArray) object;
        appointmentData = Helper.getAppointmentData(data);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(),
                appointmentData);

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.setVisibility(View.VISIBLE);
        tabLayout.setVisibility(View.VISIBLE);
        componentLoader.setVisibility(View.GONE);


    }

    @Override
    public void onError(Object message) {
        ComponentCustomDialogue dialogue = new ComponentCustomDialogue(getContext(),
                "Error", message.toString(), R.raw.cancel_animation);
        dialogue.onShow();

    }
}