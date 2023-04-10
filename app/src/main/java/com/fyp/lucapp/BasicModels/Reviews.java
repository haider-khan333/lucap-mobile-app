package com.fyp.lucapp.BasicModels;

import java.io.Serializable;

public class Reviews implements Serializable {

    private int id;
    private String profileImage;
    private String name;
    private String review;
    private String rating;
    private String daysAgo;


    public Reviews(String profileImage, String name, String review, String rating, String daysAgo) {
        this.profileImage = profileImage;
        this.name = name;
        this.review = review;
        this.rating = rating;
        this.daysAgo = daysAgo;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDaysAgo() {
        return daysAgo;
    }

    public void setDaysAgo(String daysAgo) {
        this.daysAgo = daysAgo;
    }

    @Override
    public String toString() {
        return "Reviews{" +
                "profileImage='" + profileImage + '\'' +
                ", name='" + name + '\'' +
                ", review='" + review + '\'' +
                ", rating='" + rating + '\'' +
                ", daysAgo='" + daysAgo + '\'' +
                '}';
    }
}
