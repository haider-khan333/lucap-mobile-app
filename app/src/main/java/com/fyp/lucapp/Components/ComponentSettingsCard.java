package com.fyp.lucapp.Components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fyp.lucapp.R;

public class ComponentSettingsCard extends LinearLayout {
    private TextView settingTitle;
    private ImageView settingIcon;

    public ComponentSettingsCard(Context context) {
        super(context);
        init();
    }

    public ComponentSettingsCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ComponentSettingsCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.component_setting,
                this, true);
        settingTitle = view.findViewById(R.id.settingsName);
        settingIcon = view.findViewById(R.id.settingsIcon);
    }

    public void setSettingTitle(String title) {
        settingTitle.setText(title);
    }

    public void setSettingIcon(int icon) {
        settingIcon.setImageResource(icon);
    }

    public void setID(int id) {
        settingTitle.setId(id);
    }

    public void addClickListener(OnClickListener listener) {
        this.settingTitle.setOnClickListener(listener);
    }


}
