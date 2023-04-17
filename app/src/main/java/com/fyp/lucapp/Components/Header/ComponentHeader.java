package com.fyp.lucapp.Components.Header;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.fyp.lucapp.R;

public class ComponentHeader extends LinearLayout {

    private ImageView backButton;
    private TextView title;

    public ComponentHeader(Context context) {
        super(context);
        init();
    }

    public ComponentHeader(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ComponentHeader(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.component_header,
                this, true);
        backButton = view.findViewById(R.id.headerBackButton);
        title = view.findViewById(R.id.headerText);
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public void addClickListener(View.OnClickListener onClickListener) {

        this.backButton.setOnClickListener(onClickListener);
    }


}
