package com.theresa.boutique.model;

import android.os.Parcel;
import android.os.Parcelable;

public class PriorityItem implements Parcelable {

    String id, name;

    public PriorityItem(){

    }

    protected PriorityItem(Parcel in) {
        id = in.readString();
        name = in.readString();
    }

    public static final Creator<PriorityItem> CREATOR = new Creator<PriorityItem>() {
        @Override
        public PriorityItem createFromParcel(Parcel in) {
            return new PriorityItem(in);
        }

        @Override
        public PriorityItem[] newArray(int size) {
            return new PriorityItem[size];
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
