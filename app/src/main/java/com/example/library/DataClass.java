package com.example.library;

import android.content.Intent;

public class DataClass {

    private int image;
    private String title;
    private Intent intent;
    public DataClass(int image, String title, Intent intent) {
        this.image = image;
        this.title = title;
        this.intent = intent;
    }
    public int getImage() {
        return image;
    }
    public String getTitle() {
        return title;
    }

    public Intent getIntent()
    {
        return intent;
    }
}
