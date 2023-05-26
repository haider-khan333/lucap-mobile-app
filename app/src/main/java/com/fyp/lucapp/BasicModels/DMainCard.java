package com.fyp.lucapp.BasicModels;

public class DMainCard {
    private String heading;
    private int ID;
    private String subHeading;
    private String buttonText;
    private int imageResId;

    public DMainCard(int ID, String heading, String subHeading, String buttonText, int imageResId) {
        this.heading = heading;
        this.ID = ID;
        this.subHeading = subHeading;
        this.buttonText = buttonText;
        this.imageResId = imageResId;
    }

    public String getHeading() {
        return heading;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getSubHeading() {
        return subHeading;
    }

    public void setSubHeading(String subHeading) {
        this.subHeading = subHeading;
    }

    public String getButtonText() {
        return buttonText;
    }

    public void setButtonText(String buttonText) {
        this.buttonText = buttonText;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }
}
