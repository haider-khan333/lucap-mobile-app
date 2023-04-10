package com.fyp.lucapp.Adapters;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.fyp.lucapp.Helper.Helper;
import com.fyp.lucapp.Interface.AdapterInterface;
import com.fyp.lucapp.BasicModels.Doctors;
import com.fyp.lucapp.databinding.FragmentSingleAppointmentBinding;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Doctors}.
 * TODO: Replace the implementation with code for your data type.
 */
public class DoctorListAdapterView extends RecyclerView.Adapter<DoctorListAdapterView.ViewHolder> {

    private List<Doctors> doctorsList;
    private AdapterInterface adapterInterface;

    public DoctorListAdapterView() {

    }

    public DoctorListAdapterView(List<Doctors> items
            , AdapterInterface adapterInterface) {
        doctorsList = items;
        this.adapterInterface = adapterInterface;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(FragmentSingleAppointmentBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false), adapterInterface);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if (doctorsList == null) {
            return;
        }
        holder.name.setText(doctorsList.get(position).getUsername());
        holder.speciality.setText(doctorsList.get(position).getSpeciality());
        String doctorImageBase64 = doctorsList.get(position).getImage();
        Bitmap doctorImageBitmap = Helper.convertBase64ToBitmap(doctorImageBase64);
        holder.image.setImageBitmap(doctorImageBitmap);

    }

    @Override
    public int getItemCount() {
        if (doctorsList != null) {
            return doctorsList.size();
        } else {
            return 0;
        }

    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final ImageView image;
        public final TextView name;
        public final TextView speciality;
        public final AdapterInterface adapterInterface;

        public ViewHolder(FragmentSingleAppointmentBinding binding,
                          AdapterInterface adapterInterface) {
            super(binding.getRoot());
            this.image = binding.docImage;
            this.name = binding.doctorName;
            this.speciality = binding.speciality;
            this.adapterInterface = adapterInterface;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            adapterInterface.onClick(view, getAdapterPosition());

        }
    }


}