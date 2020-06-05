package com.theresa.boutique.model;

import android.os.Parcel;
import android.os.Parcelable;

public class FunctionItem implements Parcelable {

    String id, name;

    protected FunctionItem(Parcel in) {
        id = in.readString();
        name = in.readString();
    }

    public FunctionItem(){

    }

    public static final Creator<FunctionItem> CREATOR = new Creator<FunctionItem>() {
        @Override
        public FunctionItem createFromParcel(Parcel in) {
            return new FunctionItem(in);
        }

        @Override
        public FunctionItem[] newArray(int size) {
            return new FunctionItem[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
    }
}
