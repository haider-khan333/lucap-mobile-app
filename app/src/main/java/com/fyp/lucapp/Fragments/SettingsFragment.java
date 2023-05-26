package com.fyp.lucapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.fyp.lucapp.Adapters.SettingsAdapterView;
import com.fyp.lucapp.BasicModels.DPatient;
import com.fyp.lucapp.BasicModels.DSettings;
import com.fyp.lucapp.Components.ComponentUser;
import com.fyp.lucapp.Helper.Helper;
import com.fyp.lucapp.Helper.Store;
import com.fyp.lucapp.R;
import com.fyp.lucapp.Views.LoginActivity;
import com.fyp.lucapp.Views.Settings.EditProfileActivity;

import java.util.List;

public class SettingsFragment extends Fragment implements View.OnClickListener {
    private ComponentUser componentUser;


    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        componentUser = view.findViewById(R.id.component_user);

        DPatient patient = Helper.getSavedUser(getContext());
        componentUser.setPatientImage(patient.getPatientImage());
        componentUser.setPatientName(patient.getPatientName());
        componentUser.setPatientEmail(patient.getPatientEmail());

        RecyclerView recyclerView = view.findViewById(R.id.settingsRecyclerView);

        List<DSettings> settingData = Store.getSettingsList();

        SettingsAdapterView settingsAdapterView = new
                SettingsAdapterView(this, settingData);
        recyclerView.setAdapter(settingsAdapterView);

        DividerItemDecoration dividerItemDecoration = new
                DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider_item));
        recyclerView.addItemDecoration(dividerItemDecoration);


        return view;

    }

    @Override
    public void onClick(View view) {
        System.out.println("clicked");

        int id = view.getId();
        System.out.println("id: " + id);
        if (id == Store.SETTINGS_EDIT_PROFILE_ID) {
            Intent intent = new Intent(getContext(), EditProfileActivity.class);
            startActivity(intent);
        }
//        else if (id == Store.SETTINGS_NOTIFICATIONS_ID) {
//            Toast.makeText(getContext(), "Notifications", Toast.LENGTH_SHORT).show();
//        } else if (id == Store.SETTINGS_APPOINTMENTS_ID) {
//            Toast.makeText(getContext(), "Appoinment id", Toast.LENGTH_SHORT).show();
//        }
        else if (id == Store.SETTINGS_LOGOUT_ID) {
            Helper.removeSavedUser(getContext());
            Intent intent = new Intent(getContext(), LoginActivity.class);
            startActivity(intent);
            getActivity().finish();

        }
//        else if (id == Store.SETTINGS_HELP_CENTER_ID) {
//            Toast.makeText(getContext(), "help center", Toast.LENGTH_SHORT).show();
//        } else if (id == Store.SETTINGS_ACCOUNT_ID) {
//            Toast.makeText(getContext(), "account", Toast.LENGTH_SHORT).show();
//        } else if (id == Store.SETTINGS_GALLERY_ID) {
//            Toast.makeText(getContext(), "gallery", Toast.LENGTH_SHORT).show();
//        }
    }


}