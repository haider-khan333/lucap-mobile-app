package com.fyp.lucapp.Components.CancelledAppointment;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.fyp.lucapp.Helper.Helper;
import com.fyp.lucapp.R;

public class ComponentCancelledAppointment extends CardView {

    private ImageView docImage;
    private TextView docSpeciality;
    private TextView docName;

    private LinearLayout reason;


    public ComponentCancelledAppointment(@NonNull Context context) {
        super(context);
        init();
    }

    public ComponentCancelledAppointment(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ComponentCancelledAppointment(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View view = inflate(getContext(), R.layout.component_appointment_cancel, this);
        docImage = view.findViewById(R.id.can_docImage);
        docSpeciality = view.findViewById(R.id.can_docSpeciality);
        docName = view.findViewById(R.id.can_doctorName);
        reason = view.findViewById(R.id.can_info);

        this.setBackground(null);
    }

    public void setDocImage(String docImage) {
        Bitmap docImageBitmap = Helper.convertBase64ToBitmap(docImage);
        this.docImage.setImageBitmap(docImageBitmap);
    }

    public void setDocSpeciality(String docSpeciality) {
        this.docSpeciality.setText(docSpeciality);
    }

    public void setDocName(String docName) {
        this.docName.setText(docName);
    }


    public void addOnCLickListener(OnClickListener onClickListener) {
        reason.setOnClickListener(onClickListener);
    }


}
