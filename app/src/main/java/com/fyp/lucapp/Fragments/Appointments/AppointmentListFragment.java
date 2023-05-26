package com.fyp.lucapp.Fragments.Appointments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.fyp.lucapp.Adapters.ViewPagerAdapter;
import com.fyp.lucapp.BasicModels.DAppointment;
import com.fyp.lucapp.Components.ComponentCustomDialogue;
import com.fyp.lucapp.Components.ComponentLoader;
import com.fyp.lucapp.Helper.Helper;
import com.fyp.lucapp.Helper.URL;
import com.fyp.lucapp.Interface.InterfaceApi;
import com.fyp.lucapp.R;
import com.fyp.lucapp.Routes.RouteGet;
import com.fyp.lucapp.Routes.Url;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class AppointmentListFragment extends Fragment implements InterfaceApi {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;

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

        View view = inflater.inflate(R.layout.fragment_main_appointment, container,
                false);
        try {
            // Inflate the layout for this fragment

            viewPager = view.findViewById(R.id.viewPager);
            tabLayout = view.findViewById(R.id.tabLayout);
            componentLoader = view.findViewById(R.id.componentLoader);

            viewPager.setVisibility(View.GONE);
            tabLayout.setVisibility(View.GONE);
            componentLoader.setVisibility(View.VISIBLE);

            loadDataFromApi();
            return view;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;


    }


    public void loadDataFromApi() {

        Toast.makeText(getContext(), "user id : " + URL.LOGGED_IN_PATIENT_ID, Toast.LENGTH_SHORT).show();
        RouteGet routeGet = new RouteGet(getContext(), this);
        routeGet.get(Url.GET_APPOINTMENTS + "/" + URL.LOGGED_IN_PATIENT_ID);

    }

    @Override
    public void onSuccess(JSONObject response) {
        try {
            int statusCode = response.getInt("status_code");
            if (statusCode == 200) {
                JSONArray reportsJsonList = response.getJSONArray("appointment_list");
                appointmentData = Helper.getAppointmentData(reportsJsonList);

                ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getActivity(),
                        appointmentData);
                viewPager.setAdapter(viewPagerAdapter);
                TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager,
                        (tab, position) -> {
                            if (position == 0) {
                                tab.setText("Ongoing");
                            } else if (position == 1) {
                                tab.setText("Completed");
                            } else {
                                tab.setText("Cancelled");
                            }
                        }
                );
                tabLayoutMediator.attach();
                viewPager.setVisibility(View.VISIBLE);
                tabLayout.setVisibility(View.VISIBLE);
                componentLoader.setVisibility(View.GONE);


            } else {
                componentLoader.setVisibility(View.GONE);
                ComponentCustomDialogue dialogue = new ComponentCustomDialogue(getContext(),
                        "Error", response.optString("message"), R.raw.cancel_animation);
                dialogue.onShow();
            }
        } catch
        (Exception e) {
            componentLoader.setVisibility(View.GONE);
            ComponentCustomDialogue dialogue = new ComponentCustomDialogue(getContext(),
                    "Error", "JSON exception : -1", R.raw.cancel_animation);
            dialogue.onShow();
        }
    }


    @Override
    public void onError(Object message) {
        componentLoader.setVisibility(View.GONE);
        ComponentCustomDialogue dialogue = new ComponentCustomDialogue(getContext(),
                "Error", message.toString(), R.raw.cancel_animation);
        dialogue.onShow();

    }
}