package com.fyp.lucapp.Utils;

import android.content.Context;

import com.fyp.lucapp.Components.ComponentCustomDialogue;
import com.fyp.lucapp.R;

public class UtilsDialogue {

    public static void showSuccessDialogue() {
        // TODO implement here
    }

    public static void showErrorDialogue(String message, Context context) {
        ComponentCustomDialogue dialogue = new ComponentCustomDialogue(context,
                "Error", message, R.raw.cancel_animation);
        dialogue.onShow();

    }
}
