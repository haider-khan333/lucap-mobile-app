package com.fyp.lucapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.fyp.lucapp.Adapters.DoctorListAdapterView;
import com.fyp.lucapp.Adapters.MainCardAdapter;
import com.fyp.lucapp.BasicModels.MainCardData;
import com.fyp.lucapp.Components.ComponentLoader;
import com.fyp.lucapp.Helper.Helper;
import com.fyp.lucapp.Helper.Store;
import com.fyp.lucapp.Helper.URL;
import com.fyp.lucapp.Interface.AdapterInterface;
import com.fyp.lucapp.BasicModels.Doctors;
import com.fyp.lucapp.Interface.ApiCallBack;
import com.fyp.lucapp.Interface.InterfaceDoctorList;
import com.fyp.lucapp.R;
import com.fyp.lucapp.Views.DoctorDetailsActivity;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class DoctorListFragment extends Fragment implements InterfaceDoctorList, ApiCallBack,
        View.OnClickListener {

    private RecyclerView recyclerView;
    private ViewGroup viewGroup;
    private ComponentLoader componentLoader;

    private ArrayList<Doctors> docList;


    public DoctorListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doctor_list, container, false);
        viewGroup = container;

        URL url = new URL(getContext(), this);
        componentLoader = view.findViewById(R.id.component_loader);


        recyclerView = view.findViewById(R.id.doctorRecyclerView);

        DoctorListAdapterView doctorListAdapter = new DoctorListAdapterView();
        recyclerView.setAdapter(doctorListAdapter);

        DividerItemDecoration dividerItemDecoration = new
                DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider_item));
        recyclerView.addItemDecoration(dividerItemDecoration);


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
        showLoader();
        url.getDoctors();
        return view;
    }

    public void showLoader() {
        if (componentLoader != null) {
            componentLoader.setVisibility(View.VISIBLE);
            componentLoader.playAnimation();
        }
    }

    public void hideLoader() {
        if (componentLoader != null) {
            componentLoader.setVisibility(View.GONE);
            componentLoader.pauseAnimation();
        }
    }


    @Override
    public void onSuccess(Object object) {
        //on getting data from api, set the data to adapter and notify the adapter
        hideLoader();
        JSONArray jsonArray = (JSONArray) object;
        docList = Helper.getDoctors(jsonArray);
        DoctorListAdapterView doctorListAdapter = new DoctorListAdapterView(docList,
                this);
        recyclerView.setAdapter(doctorListAdapter);
        doctorListAdapter.notifyDataSetChanged();


    }

    @Override
    public void onError(Object message) {
        hideLoader();
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
    public void onDoctorItemClicked(int position) {
        Intent intent = new Intent(getActivity(), DoctorDetailsActivity.class);
        Bundle bundle = new Bundle();
        Doctors doctors = docList.get(position);
        bundle.putSerializable("doctor", doctors);
        intent.putExtras(bundle);
        startActivity(intent);

    }
}
