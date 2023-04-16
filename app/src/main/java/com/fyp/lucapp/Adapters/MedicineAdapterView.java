package com.fyp.lucapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fyp.lucapp.BasicModels.Medications;
import com.fyp.lucapp.Components.ComponentMedicine;
import com.fyp.lucapp.Interface.InterfaceAlarm;
import com.fyp.lucapp.R;

import java.util.ArrayList;

public class MedicineAdapterView extends RecyclerView.Adapter
        <MedicineAdapterView.MedicineViewHolder> {

    private ArrayList<Medications> medicationList;
    private InterfaceAlarm interfaceAlarm;


    public MedicineAdapterView() {
    }

    public MedicineAdapterView(ArrayList<Medications> medicationList
            , InterfaceAlarm interfaceAlarm) {
        this.medicationList = medicationList;
        this.interfaceAlarm = interfaceAlarm;

    }

    @NonNull
    @Override
    public MedicineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MedicineViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_medicine_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MedicineAdapterView.MedicineViewHolder holder,
                                 int position) {
        Medications medication = medicationList.get(position);
        holder.componentMedicine.setMedicineName(medication.getMedicineName());
        holder.componentMedicine.setMedicineGrams(medication.getMedicineGrams() + "mg");
        holder.componentMedicine.setMedicineFrequency("For " + medication.getMedicineFrequency()
                + " day/s");
        holder.componentMedicine.setMedicineDosage(medication.getMedicineDosage()
                + " times a day");

        holder.componentMedicine.getAddAlarm().setOnClickListener(v ->
                interfaceAlarm.OnClick(v, position, medication));
        holder.componentMedicine.getCancelAlarm().setOnClickListener(v ->
                interfaceAlarm.OnClick(v, position, medication));
        holder.componentMedicine.getAlarmInfo().setOnClickListener(v ->
                interfaceAlarm.OnClick(v, position, medication));


    }

    @Override
    public int getItemCount() {
        if (medicationList == null)
            return 0;
        return medicationList.size();
    }

    public static class MedicineViewHolder extends RecyclerView.ViewHolder {
        private final ComponentMedicine componentMedicine;

        public MedicineViewHolder(@NonNull View itemView) {
            super(itemView);
            componentMedicine = itemView.findViewById(R.id.component_medicine_card);
        }
    }
}
