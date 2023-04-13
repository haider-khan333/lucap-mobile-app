package com.fyp.lucapp.Helper;

import android.view.View;

import com.fyp.lucapp.Components.ComponentLoader;

public class LoaderUtils {
    public static void showLoader(ComponentLoader componentLoader) {
        if (componentLoader != null) {
            componentLoader.setVisibility(View.VISIBLE);
            componentLoader.playAnimation();
        }
    }

    public static void hideLoader(ComponentLoader componentLoader) {
        if (componentLoader != null) {
            componentLoader.setVisibility(View.GONE);
            componentLoader.pauseAnimation();
        }
    }
}
