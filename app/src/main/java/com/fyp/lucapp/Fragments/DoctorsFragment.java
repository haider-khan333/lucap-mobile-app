package com.fyp.lucapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.fyp.lucapp.Adapters.DoctorListAdapterView;
import com.fyp.lucapp.Adapters.MainCardAdapter;
import com.fyp.lucapp.BasicModels.DoctorsData;
import com.fyp.lucapp.BasicModels.MainCardData;
import com.fyp.lucapp.Components.ComponentLoader;
import com.fyp.lucapp.Helper.Helper;
import com.fyp.lucapp.Helper.LoaderUtils;
import com.fyp.lucapp.Helper.Store;
import com.fyp.lucapp.Helper.URL;
import com.fyp.lucapp.Interface.InterfaceApi;
import com.fyp.lucapp.Interface.InterfaceClickItem;
import com.fyp.lucapp.R;
import com.fyp.lucapp.Views.DoctorDetailsActivity;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class DoctorsFragment extends Fragment implements InterfaceClickItem, InterfaceApi,
        View.OnClickListener {

    private RecyclerView recyclerView;
    private ComponentLoader componentLoader;
    private ArrayList<DoctorsData> docList;
    private SwipeRefreshLayout swipeRefreshLayout;
    private URL url;

    public DoctorsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doctor_list, container, false);

        url = new URL(getContext(), this);
        componentLoader = view.findViewById(R.id.component_loader);
        recyclerView = view.findViewById(R.id.doctorRecyclerView);
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);

        DoctorListAdapterView doctorListAdapter = new DoctorListAdapterView();
        recyclerView.setAdapter(doctorListAdapter);

        DividerItemDecoration dividerItemDecoration = new
                DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider_item));
        recyclerView.addItemDecoration(dividerItemDecoration);

        //Search functionality
        EditText searchEditText = view.findViewById(R.id.editName);
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterDoctors(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        swipeRefreshLayout.setOnRefreshListener(this::refreshData);


        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        List<MainCardData> mainCardDataList = new ArrayList<>();


        mainCardDataList.add(new MainCardData(0, "Book Appointment", "Quickly " +
                "create an appointment with your doctor", "Book Now",
                R.mipmap.appointment_pic_foreground));
        mainCardDataList.add(new MainCardData(1, "See Reports", "Now you can view and " +
                "download your medical reports.", "View " +
                "Reports", R.mipmap.report_pic_foreground));
        mainCardDataList.add(new MainCardData(2, "View Prescriptions",
                "Now you can view your prescriptions " +
                        "provided by your doctor.", "View " +
                "Prescriptions", R.mipmap.prescriptions_pic_foreground));

        MainCardAdapter adapter = new MainCardAdapter(mainCardDataList,
                this);
        recyclerView.setAdapter(adapter);
        componentLoader.setAnimation(R.raw.loader_anim);
        LoaderUtils.showLoader(componentLoader);
        url.getDoctors();
        return view;
    }

    private void refreshData() {
        if (swipeRefreshLayout.isRefreshing()) {
            url.getDoctors();
        }
    }

    public void filterDoctors(String text) {
        boolean found = false;
        ArrayList<DoctorsData> filteredList = new ArrayList<>();
        for (DoctorsData item : docList) {
            String name = item.getUsername().toLowerCase();
            String speciality = item.getSpeciality().toLowerCase();

            if (name.contains(text.toLowerCase()) || speciality.contains(text.toLowerCase())) {
                filteredList.add(item);
                found = true;
            }
        }
        DoctorListAdapterView adapter = new DoctorListAdapterView(filteredList,
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
    public void onSuccess(Object object) {
        //on getting data from api, set the data to adapter and notify the adapter
        LoaderUtils.hideLoader(componentLoader);
        JSONArray jsonArray = (JSONArray) object;
        docList = Helper.getDoctors(jsonArray);
        DoctorListAdapterView doctorListAdapter = new DoctorListAdapterView(docList,
                this);
        recyclerView.setAdapter(doctorListAdapter);
        doctorListAdapter.notifyDataSetChanged();

        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }


    }

    @Override
    public void onError(Object message) {
        LoaderUtils.hideLoader(componentLoader);
        Toast.makeText(getContext(), (String) message, Toast.LENGTH_SHORT).show();



    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == Store.APPOINTMENT_ID) {
            //goto appointment activity
            Toast.makeText(getContext(), "Appointment id", Toast.LENGTH_SHORT).show();
        } else if (id == Store.REPORTS_ID) {
            //goto reports activity
            Toast.makeText(getContext(), "Report id", Toast.LENGTH_SHORT).show();
        } else if (id == Store.PRESCRIPTION_ID) {
            //goto doctor activity
            Toast.makeText(getContext(), "Prescription id", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onItemClicked(int position) {
        Intent intent = new Intent(getActivity(), DoctorDetailsActivity.class);
        Bundle bundle = new Bundle();
        DoctorsData doctorsData = docList.get(position);
        bundle.putSerializable("doctor", doctorsData);
        intent.putExtras(bundle);
        startActivity(intent);

    }
}
