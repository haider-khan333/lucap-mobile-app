package com.fyp.lucapp.Helper;

import com.fyp.lucapp.BasicModels.DPatient;
import com.fyp.lucapp.BasicModels.DSettings;
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

    public static DPatient LOGGED_IN_PATIENT = null;

    public static String EMAIL = "sudowolf786@gmail.com";

    //add items to setting list
    public static List<DSettings> getSettingsList() {
        List<DSettings> settingsList = new ArrayList<>();
        settingsList.add(new DSettings(0, DSettings.VIEW_TYPE_SETTING, Strings.EDIT_PROFILE));
//        settingsList.add(new DSettings(1, DSettings.VIEW_TYPE_SETTING, Strings.NOTIFICATIONS));
//        settingsList.add(new DSettings(2, DSettings.VIEW_TYPE_SETTING, Strings.APPOINTMENTS));
//        settingsList.add(new DSettings(3, DSettings.VIEW_TYPE_SETTING, Strings.GALLERY));
        settingsList.add(new DSettings(DSettings.VIEW_TYPE_DIVIDER));
//        settingsList.add(new DSettings(4, DSettings.VIEW_TYPE_SETTING, Strings.ACCOUNT));
//        settingsList.add(new DSettings(5, DSettings.VIEW_TYPE_SETTING, Strings.HELP_CENTER));
        settingsList.add(new DSettings(6, DSettings.VIEW_TYPE_SETTING, Strings.LOGOUT));
        return settingsList;
    }


}
