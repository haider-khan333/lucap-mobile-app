package com.fyp.lucapp.Helper;

import com.fyp.lucapp.BasicModels.SettingsData;
import com.fyp.lucapp.Strings.Strings;

import java.util.ArrayList;
import java.util.List;

public class Store {
    public static int APPOINTMENT_ID = 0;
    public static int REPORTS_ID = 0;
    public static int PRESCRIPTION_ID = 0;

    public static int SETTINGS_EDIT_PROFILE_ID = 0;
    public static int SETTINGS_ACCOUNT_ID = 0;
    public static int SETTINGS_NOTIFICATIONS_ID = 0;
    public static int SETTINGS_APPOINTMENTS_ID = 0;
    public static int SETTINGS_HELP_CENTER_ID = 0;
    public static int SETTINGS_GALLERY_ID = 0;
    public static int SETTINGS_LOGOUT_ID = 0;

    public static String EMAIL = "sudowolf786@gmail.com";

    //add items to setting list
    public static List<SettingsData> getSettingsList() {
        List<SettingsData> settingsList = new ArrayList<>();
        settingsList.add(new SettingsData(0, SettingsData.VIEW_TYPE_SETTING, Strings.EDIT_PROFILE));
        settingsList.add(new SettingsData(1, SettingsData.VIEW_TYPE_SETTING, Strings.NOTIFICATIONS));
        settingsList.add(new SettingsData(2, SettingsData.VIEW_TYPE_SETTING, Strings.APPOINTMENTS));
        settingsList.add(new SettingsData(3, SettingsData.VIEW_TYPE_SETTING, Strings.GALLERY));
        settingsList.add(new SettingsData(SettingsData.VIEW_TYPE_DIVIDER));
        settingsList.add(new SettingsData(4, SettingsData.VIEW_TYPE_SETTING, Strings.ACCOUNT));
        settingsList.add(new SettingsData(5, SettingsData.VIEW_TYPE_SETTING, Strings.HELP_CENTER));
        settingsList.add(new SettingsData(6, SettingsData.VIEW_TYPE_SETTING, Strings.LOGOUT));
        return settingsList;
    }


}
