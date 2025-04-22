package com.example.task1_activitylifecyclemethods.models;

import android.graphics.Bitmap;

public class UserProfile {
    private String name;
    private String email;
    private String phone;
    private String dob;
    private Bitmap profileImage;

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getDob() { return dob; }
    public void setDob(String dob) { this.dob = dob; }
    public Bitmap getProfileImage() { return profileImage; }
    public void setProfileImage(Bitmap profileImage) { this.profileImage = profileImage; }
}