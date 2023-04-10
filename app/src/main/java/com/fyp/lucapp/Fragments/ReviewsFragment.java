package com.fyp.lucapp.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.fyp.lucapp.Adapters.ReviewListAdapterView;
import com.fyp.lucapp.Interface.AdapterInterface;
import com.fyp.lucapp.R;
import com.fyp.lucapp.databinding.ReportCardViewBinding;

public class ReviewsFragment extends Fragment implements AdapterInterface {

    private static int selectedPosition = 0;


    public ReviewsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("onCreate");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        System.out.println("onCreateView");
        View view = inflater.inflate(R.layout.reviews_view, container, false);


        if (view instanceof RecyclerView) {

            RecyclerView recyclerView = (RecyclerView) view;
            ReviewListAdapterView adapter = new ReviewListAdapterView();
            recyclerView.setAdapter(adapter);
            System.out.println();

        }
        return view;
    }

    @Override
    public void onDownloadClicked(ReportCardViewBinding binding, int position) {

    }


    @Override
    public void onClick(View view, int position) {
        System.out.println("Clicked in reviews fragment");
        selectedPosition = position;

    }
}