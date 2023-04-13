package com.fyp.lucapp.Adapters;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.fyp.lucapp.Interface.AlarmInterface;
import com.fyp.lucapp.BasicModels.Medications;
import com.fyp.lucapp.databinding.MedicationsCardViewBinding;

import java.util.ArrayList;

public class MedicationsAdapterView extends RecyclerView.Adapter<MedicationsAdapterView.ViewHolder> {

    private ArrayList<Medications> medicationList;
    private AlarmInterface alarmInterface;


    public MedicationsAdapterView() {
    }

    public MedicationsAdapterView(ArrayList<Medications> medicationList
            , AlarmInterface alarmInterface) {
        this.medicationList = medicationList;
        this.alarmInterface = alarmInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(MedicationsCardViewBinding.inflate(LayoutInflater.
                        from(parent.getContext()),
                parent, false), alarmInterface);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Medications medication = medicationList.get(position);
        holder.medicineName.setText(medication.getMedicineName());
        holder.medicineGrams.setText(medication.getMedicineGrams());
        holder.startDate.setText(medication.getStartDate().toString());
        holder.endDate.setText(medication.getEndDate().toString());
        //get the values from list and print them on the text views
        //clear the text from medicine text View
        holder.medicineTime.setText("");
        for (int i = 0; i < medication.getMedicineTime().size(); i++) {
            System.out.println("size of list is : " + medication.getMedicineTime().size());
            holder.medicineTime.append(medication.getMedicineTime().get(i) + "\n");
        }


//        medication.getMedicineTime().forEach(time -> holder.medicineTime.append(time + "\n"));
        holder.medicineGenerationTime.setText(medication.getMedicineGeneratedTime().
                toString());


    }

    @Override
    public int getItemCount() {
        if (medicationList == null)
            return 0;
        return medicationList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView medicineName;
        private TextView medicineGrams;
        private TextView startDate;
        private TextView endDate;
        private TextView medicineTime;
        private TextView medicineGenerationTime;
        private TextView addAlarm;
        private TextView cancelAlarm;

        private AlarmInterface alarmInterface;
        private MedicationsCardViewBinding binding;

        public ViewHolder(MedicationsCardViewBinding binding,
                          AlarmInterface alarmInterface) {
            super(binding.getRoot());


            this.medicineName = binding.medicineName;
            this.medicineGrams = binding.medicineGrams;
            this.startDate = binding.startDate;
            this.endDate = binding.endDate;
            this.medicineTime = binding.medicineTime;
            this.addAlarm = binding.setAlarm;
            this.medicineGenerationTime = binding.medicineGeneratedTime;
            this.cancelAlarm = binding.cancelAlarm;
            this.alarmInterface = alarmInterface;
            this.addAlarm.setOnClickListener(v ->
                    alarmInterface.onSetAlarm(binding.getRoot(), getAdapterPosition()));

            this.cancelAlarm.setOnClickListener(v ->
                    alarmInterface.onCancelAlarm(binding.getRoot(), getAdapterPosition()));


        }


    }
}
