package com.fyp.lucapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fyp.lucapp.BasicModels.ReportData;
import com.fyp.lucapp.Components.ComponentReport;
import com.fyp.lucapp.Helper.Helper;
import com.fyp.lucapp.Interface.InterfaceClickItem;
import com.fyp.lucapp.R;

import java.util.ArrayList;

public class ReportsAdapterView extends RecyclerView.Adapter<ReportsAdapterView.ReportCardView> {

    private ArrayList<ReportData> reportData;
    private InterfaceClickItem interfaceClickItem;

    public ReportsAdapterView() {
    }

    public ReportsAdapterView(ArrayList<ReportData> reportData
            , InterfaceClickItem interfaceClickItem) {
        this.reportData = reportData;
        this.interfaceClickItem = interfaceClickItem;
    }


    @NonNull
    @Override
    public ReportCardView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_report_card,
                parent, false);
        return new ReportCardView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportsAdapterView.ReportCardView holder, int position) {
        ReportData report = reportData.get(position);
        holder.reportComponent.setDate(Helper.getFormattedDate(report.getDate()));
        holder.reportComponent.setDoctorDetails(report.getDoctorName() + " - " +
                report.getDoctorSpeciality());
        holder.reportComponent.setOnClickListener(v -> interfaceClickItem.onItemClicked(position));

    }


    @Override
    public int getItemCount() {
        if (reportData == null)
            return 0;
        else if (reportData.size() == 0)
            return 0;
        else {
            reportData.size();
            return reportData.size();
        }
    }

    public static class ReportCardView extends RecyclerView.ViewHolder {
        private final ComponentReport reportComponent;

        public ReportCardView(@NonNull View itemView) {
            super(itemView);
            reportComponent = itemView.findViewById(R.id.component_report_card);
        }
    }
}
