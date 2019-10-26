package com.example.library;

public class Designation {

    private String mName;
    private String mEmail;
    private String mphoneNumber;
    private String mDepartment;
    private String mId;

    public Designation(String mName, String mEmail, String mphoneNumber, String mDepartment, String mId) {
        this.mName = mName;
        this.mEmail = mEmail;
        this.mphoneNumber = mphoneNumber;
        this.mDepartment = mDepartment;
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public String getmEmail() {
        return mEmail;
    }

    public String getMphoneNumber() {
        return mphoneNumber;
    }

    public String getmDepartment() {
        return mDepartment;
    }

    public String getmId() {
        return mId;
    }
}
