package com.fyp.lucapp.Adapters;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fyp.lucapp.BasicModels.DDoctor;
import com.fyp.lucapp.Components.ComponentDoctorList;
import com.fyp.lucapp.Helper.Helper;
import com.fyp.lucapp.Interface.InterfaceClickItem;
import com.fyp.lucapp.R;

import java.util.List;

public class DoctorListAdapterView extends RecyclerView.Adapter<DoctorListAdapterView.DoctorListViewHolder> {

    private List<DDoctor> doctorsDataList;
    private InterfaceClickItem doctorListInterface;

    public DoctorListAdapterView() {

    }

    public DoctorListAdapterView(List<DDoctor> items
            , InterfaceClickItem doctorListInterface) {
        doctorsDataList = items;
        this.doctorListInterface = doctorListInterface;
    }

    @Override
    @NonNull
    public DoctorListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_doctor_card, parent, false);
        return new DoctorListViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull DoctorListAdapterView.DoctorListViewHolder holder,
                                 int position) {
        DDoctor doctorsData = doctorsDataList.get(position);

        holder.componentDoctorList.setDoctorName(doctorsData.getUsername());
        holder.componentDoctorList.setDoctorSpeciality(doctorsData.getSpeciality());

        String doctorBase64Image = doctorsData.getImage();
        Bitmap doctorImageBitmap = Helper.convertBase64ToBitmap(doctorBase64Image);
        holder.componentDoctorList.setDoctorImage(doctorImageBitmap);
        holder.componentDoctorList.setDoctorPhone(doctorsData.getPhone());
        holder.componentDoctorList.setDoctorEmail(doctorsData.getEmail());

        holder.componentDoctorList.setOnClickListener(v -> doctorListInterface.
                onItemClicked(position));

    }

    @Override
    public int getItemCount() {
        if (doctorsDataList != null) {
            return doctorsDataList.size();
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