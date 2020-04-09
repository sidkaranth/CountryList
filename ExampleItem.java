package com.example.android.countrylist;

public class ExampleItem {
    private String mImageUrl;
    private String mCountry;
    private String mCapital;

    public ExampleItem(String mImageUrl, String mCountry, String mCapital) {
        this.mImageUrl = mImageUrl;
        this.mCountry = mCountry;
        this.mCapital = mCapital;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public String getmCountry() {
        return mCountry;
    }

    public String getmCapital() {
        return mCapital;
    }
}
