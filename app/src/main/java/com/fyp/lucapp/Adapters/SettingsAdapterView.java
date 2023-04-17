package com.fyp.lucapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fyp.lucapp.BasicModels.SettingsData;
import com.fyp.lucapp.Components.ComponentSettingsCard;
import com.fyp.lucapp.Helper.Store;
import com.fyp.lucapp.R;

import java.util.List;

public class SettingsAdapterView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final View.OnClickListener onClickListener;
    private final List<SettingsData> settingsData;


    public SettingsAdapterView(View.OnClickListener onClickListener, List<SettingsData> settingsData) {
        this.onClickListener = onClickListener;
        this.settingsData = settingsData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == SettingsData.VIEW_TYPE_SETTING) {
            return new SettingsCardView(LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.item_setting_card, parent, false));
        } else {
            return new DividerViewHolder(LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.style_divider_line, parent, false));

        }

    }

    @Override
    public int getItemViewType(int position) {
        return settingsData.get(position).getViewType();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        SettingsData setting = settingsData.get(position);

        if (holder instanceof SettingsCardView) {
            if (setting.getId() == 0) {
                int uniqueID = View.generateViewId();
                ((SettingsCardView) holder).settingsCard.setID(uniqueID);
                ((SettingsCardView) holder).settingsCard.setSettingIcon(R.drawable.account_circle);
                Store.SETTINGS_EDIT_PROFILE_ID = uniqueID;
            } else if (setting.getId() == 1) {
                int uniqueID = View.generateViewId();
                ((SettingsCardView) holder).settingsCard.setID(uniqueID);
                ((SettingsCardView) holder).settingsCard.setSettingIcon(R.drawable.notifications);
                Store.SETTINGS_NOTIFICATIONS_ID = uniqueID;
            } else if (setting.getId() == 2) {
                int uniqueID = View.generateViewId();
                ((SettingsCardView) holder).settingsCard.setID(uniqueID);
                ((SettingsCardView) holder).settingsCard.setSettingIcon(R.drawable.calendar_month);
                Store.SETTINGS_APPOINTMENTS_ID = uniqueID;
            } else if (setting.getId() == 3) {
                int uniqueID = View.generateViewId();
                ((SettingsCardView) holder).settingsCard.setID(uniqueID);
                ((SettingsCardView) holder).settingsCard.setSettingIcon(R.drawable.photo_library);
                Store.SETTINGS_GALLERY_ID = uniqueID;
            } else if (setting.getId() == 4) {
                int uniqueID = View.generateViewId();
                ((SettingsCardView) holder).settingsCard.setID(uniqueID);
                ((SettingsCardView) holder).settingsCard.setSettingIcon(R.drawable.settings);
                Store.SETTINGS_ACCOUNT_ID = uniqueID;
            } else if (setting.getId() == 5) {
                int uniqueID = View.generateViewId();
                ((SettingsCardView) holder).settingsCard.setID(uniqueID);
                ((SettingsCardView) holder).settingsCard.setSettingIcon(R.drawable.question_mark);
                Store.SETTINGS_HELP_CENTER_ID = uniqueID;
            } else if (setting.getId() == 6) {
                int uniqueID = View.generateViewId();
                ((SettingsCardView) holder).settingsCard.setID(uniqueID);
                ((SettingsCardView) holder).settingsCard.setSettingIcon(R.drawable.logout);
                Store.SETTINGS_LOGOUT_ID = uniqueID;
            }
            ((SettingsCardView) holder).settingsCard.setSettingTitle(setting.getSettingTitle());
            ((SettingsCardView) holder).settingsCard.addClickListener(onClickListener);
        }


    }

    @Override
    public int getItemCount() {
        if (settingsData == null || settingsData.size() == 0) {
            return 0;
        } else {
            return settingsData.size();
        }
    }

    public static class SettingsCardView extends RecyclerView.ViewHolder {

        private final ComponentSettingsCard settingsCard;

        public SettingsCardView(@NonNull View itemView) {
            super(itemView);
            settingsCard = itemView.findViewById(R.id.component_setting);
        }
    }

    public static class DividerViewHolder extends RecyclerView.ViewHolder {
        public DividerViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
