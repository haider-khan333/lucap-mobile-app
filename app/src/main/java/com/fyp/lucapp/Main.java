package com.fyp.lucapp;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.fyp.lucapp.Fragments.Appointments.AppointmentListFragment;
import com.fyp.lucapp.Fragments.DoctorListFragment;
import com.fyp.lucapp.Fragments.MedicationsFragment;
import com.fyp.lucapp.Fragments.ProfileFragment;
import com.fyp.lucapp.Fragments.ReportFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Main extends AppCompatActivity {

    BottomNavigationView navigationView;
//    FloatingActionButton book_appointment;
//    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationView = findViewById(R.id.bottomnavigationbar);

        navigationView.setBackground(null);


        //by default selection
        loadFragment(new DoctorListFragment(), 1);

//        book_appointment.setOnClickListener(v -> {
//            loadFragment(new DoctorListFragment(), 0);
//            System.out.println("clicked");
//        });


        for (int i = 0; i < navigationView.getMenu().size(); i++) {
            navigationView.getMenu().getItem(i).setChecked(false);
        }


        //set a listener for the bottom navigation bar
        navigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {

                case R.id.nav_appointment_list:
                    loadFragment(new AppointmentListFragment(), 0);
                    break;
                case R.id.nav_profile_settings:
                    loadFragment(new ProfileFragment(), 0);
                    break;
                case R.id.nav_report:
                    loadFragment(new ReportFragment(), 0);
                    break;

                case R.id.nav_medication:
                    loadFragment(new MedicationsFragment(), 0);
                    break;

                case R.id.nav_book_doctor:
                    loadFragment(new DoctorListFragment(), 0);
                    break;
            }
            return true;
        });


    }

//    @Override
//    public void onBackPressed() {
//        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
//            drawerLayout.closeDrawer(GravityCompat.START);
//        } else {
//            //ask for logout
//            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            builder.setTitle("Logout");
//            builder.setMessage("Are you sure you want to logout?");
//            builder.setPositiveButton("Yes", (dialog, which) -> {
//                //got login activity
//                Intent intent = new Intent(this, LoginActivity.class);
//                startActivity(intent);
//                finish();
//            });
//            builder.setNegativeButton("No", (dialog, which) -> {
//                //do nothing
//            });
//            builder.show();
//
//        }
//    }

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
