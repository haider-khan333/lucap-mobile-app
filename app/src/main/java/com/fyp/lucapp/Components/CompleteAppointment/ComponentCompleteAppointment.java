package com.fyp.lucapp.Components.CompleteAppointment;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.fyp.lucapp.Helper.Helper;
import com.fyp.lucapp.R;

public class ComponentCompleteAppointment extends CardView {

    private ImageView docImage;
    private TextView docSpeciality;
    private TextView docName;
    private TextView dateTiming;
    private Button leaveReviewBtn;


    public ComponentCompleteAppointment(@NonNull Context context) {
        super(context);
        init();
    }

    public ComponentCompleteAppointment(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ComponentCompleteAppointment(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View view = inflate(getContext(), R.layout.component_appointment_complete, this);
        docImage = view.findViewById(R.id.cmp_docImage);
        docSpeciality = view.findViewById(R.id.cmp_docSpeciality);
        docName = view.findViewById(R.id.cmp_doctorName);
        dateTiming = view.findViewById(R.id.cmp_dateTime);
        leaveReviewBtn = view.findViewById(R.id.cmp_leaveReviewBtn);

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

    public void setDateTiming(String dateTiming) {
        this.dateTiming.setText(dateTiming);
    }

    public void addOnCLickListener(OnClickListener onClickListener) {
        leaveReviewBtn.setOnClickListener(onClickListener);
    }


}
