package com.fyp.lucapp.Components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.airbnb.lottie.LottieAnimationView;
import com.fyp.lucapp.R;

public class ComponentLoader extends FrameLayout {

    private LottieAnimationView animationView;

    public ComponentLoader(@NonNull Context context) {
        super(context);
        init(context);
    }

    public ComponentLoader(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ComponentLoader(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.loader_component,
                this, true);
        animationView = view.findViewById(R.id.progress_animation_view);
    }

    public void playAnimation() {
        if (animationView != null) {
            animationView.playAnimation();
        }
    }

    public void pauseAnimation() {
        if (animationView != null) {
            animationView.pauseAnimation();
        }
    }
}
