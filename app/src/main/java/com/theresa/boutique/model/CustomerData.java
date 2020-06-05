package com.theresa.boutique.model;

import android.os.Parcel;
import android.os.Parcelable;

public class CustomerData implements Parcelable {
    private String Code,MobileNo, CustName, CustId, HouseName, CO, Place, PhoneNo, DoB, Facebook, Whatsapp, ContactAdd, EmailId, Remarks;

    protected CustomerData(Parcel in) {
        Code = in.readString();
        MobileNo = in.readString();
        CustName = in.readString();
        CustId = in.readString();
        HouseName = in.readString();
        CO = in.readString();
        Place = in.readString();
        PhoneNo = in.readString();
        DoB = in.readString();
        Facebook = in.readString();
        Whatsapp = in.readString();
        ContactAdd = in.readString();
        EmailId = in.readString();
        Remarks = in.readString();
    }

    public static final Creator<CustomerData> CREATOR = new Creator<CustomerData>() {
        @Override
        public CustomerData createFromParcel(Parcel in) {
            return new CustomerData(in);
        }

        @Override
        public CustomerData[] newArray(int size) {
            return new CustomerData[size];
        }
    };

    public String getCustId() {
        return CustId;
    }

    public void setCustId(String custId) {
        CustId = custId;
    }

    public CustomerData(){

    }



    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }

    public String getCustName() {
        return CustName;
    }

    public void setCustName(String custName) {
        CustName = custName;
    }

    public String getHouseName() {
        return HouseName;
    }

    public void setHouseName(String houseName) {
        HouseName = houseName;
    }

    public String getCO() {
        return CO;
    }

    public void setCO(String CO) {
        this.CO = CO;
    }

    public String getPlace() {
        return Place;
    }

    public void setPlace(String place) {
        Place = place;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }

    public String getDoB() {
        return DoB;
    }

    public void setDoB(String doB) {
        DoB = doB;
    }

    public String getFacebook() {
        return Facebook;
    }

    public void setFacebook(String facebook) {
        Facebook = facebook;
    }

    public String getWhatsapp() {
        return Whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        Whatsapp = whatsapp;
    }

    public String getContactAdd() {
        return ContactAdd;
    }

    public void setContactAdd(String contactAdd) {
        ContactAdd = contactAdd;
    }

    public String getEmailId() {
        return EmailId;
    }

    public void setEmailId(String emailId) {
        EmailId = emailId;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Code);
        dest.writeString(MobileNo);
        dest.writeString(CustName);
        dest.writeString(CustId);
        dest.writeString(HouseName);
        dest.writeString(CO);
        dest.writeString(Place);
        dest.writeString(PhoneNo);
        dest.writeString(DoB);
        dest.writeString(Facebook);
        dest.writeString(Whatsapp);
        dest.writeString(ContactAdd);
        dest.writeString(EmailId);
        dest.writeString(Remarks);
    }
}
