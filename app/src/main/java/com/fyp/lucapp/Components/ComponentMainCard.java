package com.fyp.lucapp.Components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;

import com.fyp.lucapp.R;
import com.google.android.material.card.MaterialCardView;

public class ComponentMainCard extends MaterialCardView {
    private TextView heading;
    private TextView subHeading;
    private TextView button;
    private ImageView imageView;



    public ComponentMainCard(Context context) {
        super(context);
        init();
    }

    public ComponentMainCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    public void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.main_card, this,
                true);
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
        this.imageView.setImageDrawable(ResourcesCompat.getDrawable(getResources(), imageView,
                null));
    }


}
