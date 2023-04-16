package com.fyp.lucapp.Views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.fyp.lucapp.Components.TypingTextView;
import com.fyp.lucapp.Helper.URL;
import com.fyp.lucapp.Main;
import com.fyp.lucapp.R;

public class SplashActivity extends AppCompatActivity {

    private static final long SPLASH_TIME_OUT = 3000; // 3 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TypingTextView typingTextView = findViewById(R.id.typingTextView);
        ImageView logo = findViewById(R.id.logo);

        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        logo.startAnimation(fadeIn);

        typingTextView.animateText("LUCAP");

        new Handler().postDelayed(() -> {
            SharedPreferences sharedPreferences = getSharedPreferences("userDetail", MODE_PRIVATE);
            String userId = sharedPreferences.getString("userID", null);
            System.out.println("userId: " + userId);


            Intent intent;
            if (userId != null) {
                URL.LOGGED_IN_PATIENT_ID = userId;
                // If the user is logged in, go to the main activity
                intent = new Intent(SplashActivity.this, Main.class);
            } else {
                // If the user is not logged in, go to the login activity
                intent = new Intent(SplashActivity.this, LoginActivity.class);
            }
            startActivity(intent);
            finish();
            startActivity(intent);
            finish();
        }, SPLASH_TIME_OUT);
    }
}
