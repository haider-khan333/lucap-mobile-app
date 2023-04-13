package com.fyp.lucapp.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.fyp.lucapp.Adapters.ReportsAdapterView;
import com.fyp.lucapp.BasicModels.ReportData;
import com.fyp.lucapp.Components.ComponentCustomDialogue;
import com.fyp.lucapp.Components.ComponentLoader;
import com.fyp.lucapp.Helper.DocumentUtils;
import com.fyp.lucapp.Helper.Helper;
import com.fyp.lucapp.Helper.LoaderUtils;
import com.fyp.lucapp.Helper.URL;
import com.fyp.lucapp.Interface.InterfaceApi;
import com.fyp.lucapp.Interface.InterfaceClickItem;
import com.fyp.lucapp.R;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;

import java.util.ArrayList;

public class ReportFragment extends Fragment implements InterfaceApi, InterfaceClickItem {
    private RecyclerView recyclerView;
    private URL url;
    private ArrayList<ReportData> reportDataList;

    private SwipeRefreshLayout swipeRefreshLayout;
    private ComponentLoader componentLoader;

    public ReportFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ask for read and write permission
        ActivityCompat.requestPermissions(getActivity(),
                new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE,
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                1);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_medical_report, container,
                false);
        url = new URL(getContext(), this);
        recyclerView = view.findViewById(R.id.reportRecyclerView);
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        componentLoader = view.findViewById(R.id.component_loader);

        ReportsAdapterView reportsAdapterView = new ReportsAdapterView();
        recyclerView.setAdapter(reportsAdapterView);

        swipeRefreshLayout.setOnRefreshListener(this::refreshData);

        EditText searchEditText = view.findViewById(R.id.editName);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterReports(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        DividerItemDecoration dividerItemDecoration = new
                DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider_item));
        recyclerView.addItemDecoration(dividerItemDecoration);


        componentLoader.setAnimation(R.raw.loader_anim);
        LoaderUtils.showLoader(componentLoader);
        url.getReports();
        return view;
    }

    private void refreshData() {
        if (swipeRefreshLayout.isRefreshing()) {
            url.getReports();
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onSuccess(Object object) {
        LoaderUtils.hideLoader(componentLoader);
        JSONArray reportList = (JSONArray) object;
        reportDataList = Helper.getReportList(reportList);
        ReportsAdapterView reportsAdapterView = new ReportsAdapterView(reportDataList,
                this);
        recyclerView.setAdapter(reportsAdapterView);
        reportsAdapterView.notifyDataSetChanged();

        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }


    }

    @SuppressLint("NotifyDataSetChanged")
    public void filterReports(String text) {
        boolean found = false;
        ArrayList<ReportData> filteredList = new ArrayList<>();
        for (ReportData item : reportDataList) {
            String name = item.getDoctorName().toLowerCase();
            String speciality = item.getDoctorSpeciality().toLowerCase();

            if (name.contains(text.toLowerCase()) || speciality.contains(text.toLowerCase())) {
                filteredList.add(item);
                found = true;
            }
        }
        ReportsAdapterView adapter = new ReportsAdapterView(filteredList,
                this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        if (!found) {
            componentLoader.setAnimation(R.raw.not_found_anim);
            LoaderUtils.showLoader(componentLoader);
        } else {
            LoaderUtils.hideLoader(componentLoader);
        }
    }

    @Override
    public void onError(Object message) {
        LoaderUtils.hideLoader(componentLoader);
        ComponentCustomDialogue componentCustomDialogue = new ComponentCustomDialogue
                (getContext(),
                        "Invalid request", message.toString(),
                        R.raw.cancel_animation);
        componentCustomDialogue.onShow();

    }

    @Override
    public void onItemClicked(int position) {
        System.out.println("Item clicked  + " + position);
        ReportData reportDetails = reportDataList.get(position);
        boolean flag = DocumentUtils.createPDF(reportDetails, getContext());
        if (flag) {
            Snackbar.make(getView(), "Report saved successfully", Snackbar.LENGTH_LONG).show();
        } else {
            Snackbar.make(getView(), "Report not saved", Snackbar.LENGTH_LONG).show();
        }


    }


}