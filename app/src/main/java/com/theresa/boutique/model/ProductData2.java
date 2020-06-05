package com.theresa.boutique.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ProductData2 implements Parcelable {

    int id;
    String itemName;
    String imageUrl;
    int imageDrawable;
    int itemCount;

    protected ProductData2(Parcel in) {
        id = in.readInt();
        itemName = in.readString();
        imageUrl = in.readString();
        imageDrawable = in.readInt();
        itemCount = in.readInt();
    }

    public ProductData2(){

    }

    public static final Creator<ProductData> CREATOR = new Creator<ProductData>() {
        @Override
        public ProductData createFromParcel(Parcel in) {
            return new ProductData(in);
        }

        @Override
        public ProductData[] newArray(int size) {
            return new ProductData[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getImageDrawable() {
        return imageDrawable;
    }

    public void setImageDrawable(int imageDrawable) {
        this.imageDrawable = imageDrawable;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(itemName);
        dest.writeString(imageUrl);
        dest.writeInt(imageDrawable);
        dest.writeInt(itemCount);
    }
}
