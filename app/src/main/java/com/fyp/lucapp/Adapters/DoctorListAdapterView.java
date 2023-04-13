package com.fyp.lucapp.Adapters;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fyp.lucapp.Components.ComponentDoctorList;
import com.fyp.lucapp.Helper.Helper;
import com.fyp.lucapp.Interface.AdapterInterface;
import com.fyp.lucapp.BasicModels.Doctors;
import com.fyp.lucapp.Interface.InterfaceDoctorList;
import com.fyp.lucapp.R;

import java.util.List;

public class DoctorListAdapterView extends RecyclerView.Adapter<DoctorListAdapterView.DoctorListViewHolder> {

    private List<Doctors> doctorsList;
    private InterfaceDoctorList doctorListInterface;

    public DoctorListAdapterView() {

    }

    public DoctorListAdapterView(List<Doctors> items
            , InterfaceDoctorList doctorListInterface) {
        doctorsList = items;
        this.doctorListInterface = doctorListInterface;
    }

    @Override
    @NonNull
    public DoctorListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_doctor_list, parent, false);
        return new DoctorListViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull DoctorListAdapterView.DoctorListViewHolder holder,
                                 int position) {
        Doctors doctors = doctorsList.get(position);

        holder.componentDoctorList.setDoctorName(doctors.getUsername());
        holder.componentDoctorList.setDoctorSpeciality(doctors.getSpeciality());

        String doctorBase64Image = doctors.getImage();
        Bitmap doctorImageBitmap = Helper.convertBase64ToBitmap(doctorBase64Image);
        holder.componentDoctorList.setDoctorImage(doctorImageBitmap);
        holder.componentDoctorList.setDoctorExperience("(" + doctors.
                getYearsOfExperience() + " Years)");
        holder.componentDoctorList.setDoctorPhone(doctors.getPhone());
        holder.componentDoctorList.setDoctorEmail(doctors.getEmail());

        holder.componentDoctorList.setOnClickListener(v -> doctorListInterface.
                onDoctorItemClicked(position));

    }

    @Override
    public int getItemCount() {
        if (doctorsList != null) {
            return doctorsList.size();
        } else {
            return 0;
        }

    }

    public static class DoctorListViewHolder extends RecyclerView.ViewHolder {


        private final ComponentDoctorList componentDoctorList;

        public DoctorListViewHolder(@NonNull View itemView) {
            super(itemView);
            componentDoctorList = itemView.findViewById(R.id.component_doctor_list);
        }
    }


}