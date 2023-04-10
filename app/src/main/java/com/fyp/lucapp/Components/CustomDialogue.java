package com.fyp.lucapp.Components;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.airbnb.lottie.LottieAnimationView;
import com.fyp.lucapp.Helper.Status;
import com.fyp.lucapp.R;

public class CustomDialogue extends AlertDialog {

    private TextView mainHeading;
    private TextView message;
    //    private Button submitButton;
    private LottieAnimationView animationView;

    private AlertDialog alertDialog;


    public CustomDialogue(Context context, String mainHeading, String message, int animation) {
        super(context);
        init(mainHeading, message, animation);

    }

    private void init(String mainHeading, String message, int animation) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.custom_dialogue, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setView(view);

        this.alertDialog = alertDialogBuilder.create();
        this.alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        this.mainHeading = view.findViewById(R.id.mainHeading);
        this.message = view.findViewById(R.id.message);
//        this.submitButton = view.findViewById(R.id.ok);
        this.animationView = view.findViewById(R.id.animationView);

        setMainHeading(mainHeading);
        setMessage(message);
        setAnimation(animation);
//        setSubmitButton(submitButton, status);


    }

    public void setMainHeading(String mainHeading) {
        this.mainHeading.setText(mainHeading);
    }

    public void setMessage(String message) {
        this.message.setText(message);
    }

    public void setAnimation(int animation) {
        animationView.setAnimation(animation);
    }


//    public void setSubmitButton(String text, Status status) {
//        submitButton.setText(text);
//        switch (status) {
//            case SUCCESS:
//                submitButton.setBackgroundResource(R.drawable.accepted);
//                break;
//            case ERROR:
//                submitButton.setBackgroundResource(R.drawable.rejected);
//                break;
//            case OK:
//                break;
//
//        }
//    }

    public void onShow() {
        this.alertDialog.show();
    }

    public void onDismiss() {
        this.alertDialog.dismiss();
    }

}
