package com.fyp.lucapp.Adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fyp.lucapp.BasicModels.Report;
import com.fyp.lucapp.Interface.AdapterInterface;
import com.fyp.lucapp.databinding.ReportCardViewBinding;

import java.util.ArrayList;

public class ReportsAdapterView extends RecyclerView.Adapter<ReportsAdapterView.ViewHolder> {

    private ArrayList<Report> reportList;
    private AdapterInterface adapterInterface;

    public ReportsAdapterView() {
    }

    public ReportsAdapterView(ArrayList<Report> reportList
            , AdapterInterface adapterInterface) {
        this.reportList = reportList;
        this.adapterInterface = adapterInterface;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ReportCardViewBinding.inflate(LayoutInflater.
                        from(parent.getContext()),
                parent, false), adapterInterface);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.date.setText(reportList.get(position).
                getReportGenerationDate());
        holder.doctorDetails.setText(reportList.get(position).getDoctorDetails());


    }

    @Override
    public int getItemCount() {
        if (reportList == null)
            return 0;
        else if (reportList.size() == 0)
            return 0;
        else {
            reportList.size();
            return reportList.size();
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView date;
        public TextView doctorDetails;
        public TextView downloadTextView;

        public ViewHolder(ReportCardViewBinding binding,
                          AdapterInterface adapterInterface) {
            super(binding.getRoot());
            doctorDetails = binding.doctorDetails;
            date = binding.reportDate;
            downloadTextView = binding.downloadReport;


            downloadTextView.setOnClickListener(v -> {
                if (adapterInterface != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        adapterInterface.onDownloadClicked(binding, position);
                    }
                }
            });
        }

    }
}
