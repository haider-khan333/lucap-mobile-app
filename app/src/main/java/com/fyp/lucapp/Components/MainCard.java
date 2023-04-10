package com.fyp.lucapp.Components;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.fyp.lucapp.R;
import com.google.android.material.card.MaterialCardView;

public class MainCard extends MaterialCardView {
    private MaterialCardView cardView;
    private TextView heading;
    private TextView subHeading;
    private Button button;
    private ImageView imageView;

    public MainCard(Context context) {
        super(context);
        init()  ;
    }

    public void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.main_card, this,
                true);
        cardView = view.findViewById(R.id.mainCard);
        heading = view.findViewById(R.id.heading);
        subHeading = view.findViewById(R.id.subHeading);
        button = view.findViewById(R.id.button);
        imageView = view.findViewById(R.id.imageView);
    }

    public void setHeading(String heading) {
        this.heading.setText(heading);
    }

    public void setSubHeading(String subHeading) {
        this.subHeading.setText(subHeading);
    }

    public void setButton(String button) {
        this.button.setText(button);
    }

    public void setImageView(int imageView) {
        this.imageView.setImageResource(imageView);
    }


    public void setOnClickListener(View.OnClickListener listener) {
        cardView.setOnClickListener(listener);
    }




}
