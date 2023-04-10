package com.fyp.lucapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fyp.lucapp.Interface.AdapterInterface;
import com.fyp.lucapp.BasicModels.Reviews;
import com.fyp.lucapp.databinding.ReviewsViewBinding;

import java.util.List;

public class ReviewListAdapterView extends RecyclerView.Adapter<ReviewListAdapterView.ViewHolder> {
    private List<Reviews> mValues;
    private AdapterInterface adapterInterface;

    public ReviewListAdapterView() {

    }

    public ReviewListAdapterView(List<Reviews> items
            , AdapterInterface adapterInterface) {
        mValues = items;
        this.adapterInterface = adapterInterface;
    }

    @Override
    public ReviewListAdapterView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ReviewListAdapterView.ViewHolder
                (ReviewsViewBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false), adapterInterface);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(mValues.get(position).getName());
        holder.review.setText(mValues.get(position).getReview());
        holder.daysAgo.setText(mValues.get(position).getDaysAgo());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public de.hdodenhof.circleimageview.CircleImageView profileImage;
        public TextView name;
        public TextView review;
        //        public TextView rating;
        public TextView daysAgo;

        public final AdapterInterface adapterInterface;

        public ViewHolder(ReviewsViewBinding binding,
                          AdapterInterface adapterInterface) {
            super(binding.getRoot());
            this.profileImage = binding.reviewImage;
            this.name = binding.reviewName;
            this.review = binding.reviewDescription;
//            this.rating = binding.reviewRating;
            this.daysAgo = binding.reviewDaysAgo;

            this.adapterInterface = adapterInterface;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            adapterInterface.onClick(view, getAdapterPosition());

        }
    }
}
