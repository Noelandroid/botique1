package com.theresa.boutique.model;

import android.os.Parcel;
import android.os.Parcelable;

public class AlterItem implements Parcelable{

    String id, ids, name, code;

    protected AlterItem(Parcel in) {
        id = in.readString();
        ids = in.readString();
        name = in.readString();
        code = in.readString();
    }

    public AlterItem(){

    }

    public static final Creator<AlterItem> CREATOR = new Creator<AlterItem>() {
        @Override
        public AlterItem createFromParcel(Parcel in) {
            return new AlterItem(in);
        }

        @Override
        public AlterItem[] newArray(int size) {
            return new AlterItem[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(ids);
        dest.writeString(name);
        dest.writeString(code);
    }
}
