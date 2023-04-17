package com.fyp.lucapp.Components.EditUserImage;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.fyp.lucapp.Helper.Helper;
import com.fyp.lucapp.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class ComponentEditUserImage extends LinearLayout {

    private CircleImageView userImage;

    public ComponentEditUserImage(Context context) {
        super(context);
        init();
    }

    public ComponentEditUserImage(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ComponentEditUserImage(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R
                .layout.component_edit_user_image, this, true);
        userImage = view.findViewById(R.id.editUserPatientImage);
    }

    public void setImage(String base64) {
        Bitmap bitmap = Helper.convertBase64ToBitmap(base64);
        userImage.setImageBitmap(bitmap);
    }

    public void setImage(Bitmap bitmap) {
        userImage.setImageBitmap(bitmap);
    }

    public void setImage(int image) {
        userImage.setImageResource(image);
    }

    public void addClickListener(OnClickListener onClickListener) {
        userImage.setOnClickListener(onClickListener);
    }

    public ImageView getUserImage() {
        return userImage;
    }
}
