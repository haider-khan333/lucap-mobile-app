package com.fyp.lucapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.fyp.lucapp.Fragments.Appointments.AppointmentListFragment;
import com.fyp.lucapp.Fragments.DoctorsFragment;
import com.fyp.lucapp.Fragments.MedicationsFragment;
import com.fyp.lucapp.Fragments.SettingsFragment;
import com.fyp.lucapp.Fragments.ReportFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigationView = findViewById(R.id.bottomnavigationbar);

        navigationView.setBackground(null);


        //by default selection
        loadFragment(new DoctorsFragment(), 1);

        //set a listener for the bottom navigation bar
        navigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {

                case R.id.nav_appointment_list:
                    loadFragment(new AppointmentListFragment(), 0);
                    break;
                case R.id.nav_profile_settings:
                    loadFragment(new SettingsFragment(), 0);
                    break;
                case R.id.nav_report:
                    loadFragment(new ReportFragment(), 0);
                    break;

                case R.id.nav_medication:
                    loadFragment(new MedicationsFragment(), 0);
                    break;

                case R.id.nav_book_doctor:
                    loadFragment(new DoctorsFragment(), 0);
                    break;
            }
            return true;
        });


    }

    public void loadFragment(Fragment fragment, int flag) {
        if (flag == 1) {
            getSupportFragmentManager().beginTransaction().add(R.id.container,
                    fragment).commit();
        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.container,
                    fragment).commit();
        }
    }
}

