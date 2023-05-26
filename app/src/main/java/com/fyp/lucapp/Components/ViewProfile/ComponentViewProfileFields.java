package com.fyp.lucapp.Components.ViewProfile;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.fyp.lucapp.R;

public class ComponentViewProfileFields extends LinearLayout {
    private TextView title;
    private TextView value;
    private ImageView icon;

    public ComponentViewProfileFields(Context context) {
        super(context);
        init();
    }

    public ComponentViewProfileFields(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ComponentViewProfileFields(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View view = inflate(getContext(), R.layout.component_view_profile_fields, this);
        icon = view.findViewById(R.id.pf_iconImage);
        title = view.findViewById(R.id.pf_title);
        value = view.findViewById(R.id.pf_value);
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public void setValue(String value) {
        this.value.setText(value);
    }

    public void setIcon(int icon) {
        this.icon.setImageResource(icon);
    }
}
