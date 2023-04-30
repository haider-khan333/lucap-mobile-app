package com.fyp.lucapp.Components.AppointmentTiming;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.fyp.lucapp.R;
import com.google.android.material.card.MaterialCardView;

public class ComponentAppointmentTiming extends MaterialCardView {
    private TextView timing;
    private MaterialCardView cardView;

    public ComponentAppointmentTiming(@NonNull Context context) {
        super(context);
        init();
    }

    public ComponentAppointmentTiming(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ComponentAppointmentTiming(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        View view = inflate(getContext(), R.layout.component_appointment_timings, this);
        timing = view.findViewById(R.id.appointment_time);
        cardView = view.findViewById(R.id.component_appointment_card_view);
        this.setBackground(getResources().getDrawable(R.drawable.style_appointment_time));
        //add 10dp margin to this component
        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(10, 10, 10, 10);
        this.setLayoutParams(layoutParams);

        this.cardView.setBackground(null);


    }

    public void setTiming(String timing) {
        this.timing.setText(timing);
    }


}
