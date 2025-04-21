package com.example.task1_activitylifecyclemethods.models;

public class Data {
    private int imageResId;
    private String topText;
    private String bottomText;

    public Data(int imageResId, String topText, String bottomText) {
        this.imageResId = imageResId;
        this.topText = topText;
        this.bottomText = bottomText;
    }
    public int getImageResId() { return imageResId; }
    public String getTopText() { return topText; }
    public String getBottomText() { return bottomText; }
}
