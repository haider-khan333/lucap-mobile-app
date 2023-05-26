package com.fyp.lucapp.BasicModels;

public class DSettings {

    private String settingTitle;
    private int id;

    public static final int VIEW_TYPE_SETTING = 0;
    public static final int VIEW_TYPE_DIVIDER = 1;
    private int viewType;

    public DSettings(int id, int viewType, String settingTitle) {
        this.settingTitle = settingTitle;
        this.id = id;
        this.viewType = viewType;

    }

    public DSettings(int viewType) {
        this.viewType = viewType;
    }


    public String getSettingTitle() {
        return settingTitle;
    }

    public void setSettingTitle(String settingTitle) {
        this.settingTitle = settingTitle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getViewType() {
        return viewType;
    }


}
