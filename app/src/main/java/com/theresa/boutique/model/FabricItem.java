package com.theresa.boutique.model;

import android.os.Parcel;
import android.os.Parcelable;

public class FabricItem implements Parcelable {

    String id, name, ids, code;


    public FabricItem(){

    }

    protected FabricItem(Parcel in) {
        id = in.readString();
        name = in.readString();
        ids = in.readString();
        code = in.readString();
    }

    public static final Creator<FabricItem> CREATOR = new Creator<FabricItem>() {
        @Override
        public FabricItem createFromParcel(Parcel in) {
            return new FabricItem(in);
        }

        @Override
        public FabricItem[] newArray(int size) {
            return new FabricItem[size];
        }
    };

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

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
        dest.writeString(ids);
        dest.writeString(code);
    }
}
