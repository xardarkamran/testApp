package com.example.testapp.Utils;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

//@SuppressLint("ParcelCreator")
public class ModelClass implements Serializable {

    public String username;
    public String password;

    public ModelClass(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /*@Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }*/
}
