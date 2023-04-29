package com.fyp.lucapp.Components.DayNavigation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.fyp.lucapp.R;

public class ComponentDayNavigation extends LinearLayout {
    private TextView currentDay;
    private ImageView backArrow, frontArrow;

    public ComponentDayNavigation(@NonNull Context context) {
        super(context);
        init();
    }

    public ComponentDayNavigation(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ComponentDayNavigation(@NonNull Context context, @Nullable AttributeSet attrs,
                                  int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View view = inflate(getContext(), R.layout.component_day_navigation, this);
        currentDay = view.findViewById(R.id.current_day);
        backArrow = view.findViewById(R.id.back_arrow);
        frontArrow = view.findViewById(R.id.front_arrow);
    }

    public void setCurrentDay(String day) {
        this.currentDay.setText(day);
    }

    public void setBackArrowClickListener(OnClickListener onClickListener) {
        this.backArrow.setOnClickListener(onClickListener);
    }

    public void setFrontArrowClickListener(OnClickListener onClickListener) {
        this.frontArrow.setOnClickListener(onClickListener);
    }
}

