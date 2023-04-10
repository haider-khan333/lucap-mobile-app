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
import com.fyp.lucapp.Helper.Helper;
import com.fyp.lucapp.Helper.URL;
import com.fyp.lucapp.Interface.AdapterInterface;
import com.fyp.lucapp.BasicModels.Doctors;
import com.fyp.lucapp.Interface.ApiCallBack;
import com.fyp.lucapp.R;
import com.fyp.lucapp.Views.DoctorDetailsActivity;
import com.fyp.lucapp.databinding.ReportCardViewBinding;

import org.json.JSONArray;

import java.util.ArrayList;

public class DoctorListFragment extends Fragment implements AdapterInterface, ApiCallBack {

    private RecyclerView recyclerView;
    private URL url;

    private ArrayList<Doctors> docList;




    public DoctorListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doctor_list, container, false);
        url = new URL(getContext(), this);
        recyclerView = view.findViewById(R.id.doctorRecyclerView);
        DoctorListAdapterView doctorListAdapter = new DoctorListAdapterView();
        recyclerView.setAdapter(doctorListAdapter);

        DividerItemDecoration dividerItemDecoration = new
                DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.divider_item));
        recyclerView.addItemDecoration(dividerItemDecoration);

        url.getDoctors();
        return view;
    }


    @Override
    public void onDownloadClicked(ReportCardViewBinding binding, int position) {

    }

    @Override
    public void onClick(View view, int position) {
        //goto next activity
        Intent intent = new Intent(getActivity(), DoctorDetailsActivity.class);
        Bundle bundle = new Bundle();
        Doctors doctors = docList.get(position);
        bundle.putSerializable("doctor", doctors);
        intent.putExtras(bundle);
        startActivity(intent);

    }


    @Override
    public void onSuccess(Object object) {
        //on getting data from api, set the data to adapter and notify the adapter
        JSONArray jsonArray = (JSONArray) object;
        docList = Helper.getDoctors(jsonArray);
        DoctorListAdapterView doctorListAdapter = new DoctorListAdapterView(docList,
                this);
        recyclerView.setAdapter(doctorListAdapter);
        doctorListAdapter.notifyDataSetChanged();


    }

    @Override
    public void onError(Object message) {
        Toast.makeText(getContext(), (String) message, Toast.LENGTH_SHORT).show();

    }
}
