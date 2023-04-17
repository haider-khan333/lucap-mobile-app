package com.fyp.lucapp.Components;

import android.content.Context;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.fyp.lucapp.R;

public class ComponentTextView extends LinearLayout {

    private TextView label;
    private TextView value;

    public ComponentTextView(Context context) {
        super(context);
        init();
    }

    public ComponentTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public ComponentTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R
                .layout.component_text_view, this, true);
        label = view.findViewById(R.id.textLabel);
        value = view.findViewById(R.id.textValue);
    }

    public void setLabel(String label) {
        this.label.setText(label);
    }

    public void setValue(String value) {
        this.value.setText(value);
    }

    public String getValueData() {
        return this.value.getText().toString();
    }

    public void setEnabled(boolean enabled) {
        this.value.setEnabled(enabled);
    }

    public void addOnTextWatcher(TextWatcher textWatcher) {
        this.value.addTextChangedListener(textWatcher);
    }

    public void setInputType(int type) {
        this.value.setInputType(type);
    }

}
