package com.fyp.lucapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fyp.lucapp.BasicModels.MainCardData;
import com.fyp.lucapp.Components.ComponentMainCard;
import com.fyp.lucapp.Helper.Store;
import com.fyp.lucapp.R;

import java.util.List;

public class MainCardAdapter extends RecyclerView.Adapter<MainCardAdapter.MainCardViewHolder> {
    private final List<MainCardData> mainCardDataList;
    private final View.OnClickListener onClickListener;

    public MainCardAdapter(List<MainCardData> mainCardDataList,
                           View.OnClickListener onClickListener) {
        this.mainCardDataList = mainCardDataList;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public MainCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_card,
                parent, false);
        return new MainCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainCardViewHolder holder, int position) {
        MainCardData cardData = mainCardDataList.get(position);

        if (cardData.getID() == 0) {
            int uniqueID = View.generateViewId();
            holder.componentMainCard.setId(uniqueID);
            Store.APPOINTMENT_ID = uniqueID;
        } else if (cardData.getID() == 1) {
            int uniqueID = View.generateViewId();
            holder.componentMainCard.setId(uniqueID);
            Store.REPORTS_ID = uniqueID;
        } else if (cardData.getID() == 2) {
            int uniqueID = View.generateViewId();
            holder.componentMainCard.setId(uniqueID);
            Store.PRESCRIPTION_ID = uniqueID;
        }


        holder.componentMainCard.setHeading(cardData.getHeading());
        holder.componentMainCard.setSubHeading(cardData.getSubHeading());
        holder.componentMainCard.setButton(cardData.getButtonText());
        holder.componentMainCard.setImageView(cardData.getImageResId());
        holder.componentMainCard.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemCount() {
        if (mainCardDataList == null)
            return 0;
        return mainCardDataList.size();
    }

    public static class MainCardViewHolder extends RecyclerView.ViewHolder {
        private final ComponentMainCard componentMainCard;
        public MainCardViewHolder(@NonNull View itemView) {
            super(itemView);
            componentMainCard = itemView.findViewById(R.id.main_card);
        }
    }
}
