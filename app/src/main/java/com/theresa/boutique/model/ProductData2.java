package com.theresa.boutique.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ProductData2 implements Parcelable {

    String id;
    String AutoId;
    String TokenNo;
    String RefNo;
    String TrDate;
    String OptDate;
    String AccId;
    String AccName;
    String AccCode;
    String IsAlteration;
    String MobileNo;
    String NoOfChurider;
    String NoOfAnarkali;
    String NoOfShawl;
    String NoOfSaree;
    String NoOfFrock;
    String NoOfBlouse;
    String NoOfTopSkirt;
    String NoOfGown;
    String NoOfOverCoat;
    String NoOfItem1;
    String NoOfItem2;
    String NoOfItem3;
    String NoOfItem4;
    String NoOfItem5;
    String TotBags;
    String CompanyId;
    String BranchId;
    String EmpId;
    String UserId;

    String itemName;
    String imageUrl;
    int imageDrawable;
    int itemCount;




    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAutoId() {
        return AutoId;
    }

    public void setAutoId(String autoId) {
        AutoId = autoId;
    }

    public String getTokenNo() {
        return TokenNo;
    }

    public void setTokenNo(String tokenNo) {
        TokenNo = tokenNo;
    }

    public String getRefNo() {
        return RefNo;
    }

    public void setRefNo(String refNo) {
        RefNo = refNo;
    }

    public String getTrDate() {
        return TrDate;
    }

    public void setTrDate(String trDate) {
        TrDate = trDate;
    }

    public String getOptDate() {
        return OptDate;
    }

    public void setOptDate(String optDate) {
        OptDate = optDate;
    }

    public String getAccId() {
        return AccId;
    }

    public void setAccId(String accId) {
        AccId = accId;
    }

    public String getAccName() {
        return AccName;
    }

    public void setAccName(String accName) {
        AccName = accName;
    }

    public String getAccCode() {
        return AccCode;
    }

    public void setAccCode(String accCode) {
        AccCode = accCode;
    }

    public String getIsAlteration() {
        return IsAlteration;
    }

    public void setIsAlteration(String isAlteration) {
        IsAlteration = isAlteration;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }

    public String getNoOfChurider() {
        return NoOfChurider;
    }

    public void setNoOfChurider(String noOfChurider) {
        NoOfChurider = noOfChurider;
    }

    public String getNoOfAnarkali() {
        return NoOfAnarkali;
    }

    public void setNoOfAnarkali(String noOfAnarkali) {
        NoOfAnarkali = noOfAnarkali;
    }

    public String getNoOfShawl() {
        return NoOfShawl;
    }

    public void setNoOfShawl(String noOfShawl) {
        NoOfShawl = noOfShawl;
    }

    public String getNoOfSaree() {
        return NoOfSaree;
    }

    public void setNoOfSaree(String noOfSaree) {
        NoOfSaree = noOfSaree;
    }

    public String getNoOfFrock() {
        return NoOfFrock;
    }

    public void setNoOfFrock(String noOfFrock) {
        NoOfFrock = noOfFrock;
    }

    public String getNoOfBlouse() {
        return NoOfBlouse;
    }

    public void setNoOfBlouse(String noOfBlouse) {
        NoOfBlouse = noOfBlouse;
    }

    public String getNoOfTopSkirt() {
        return NoOfTopSkirt;
    }

    public void setNoOfTopSkirt(String noOfTopSkirt) {
        NoOfTopSkirt = noOfTopSkirt;
    }

    public String getNoOfGown() {
        return NoOfGown;
    }

    public void setNoOfGown(String noOfGown) {
        NoOfGown = noOfGown;
    }

    public String getNoOfOverCoat() {
        return NoOfOverCoat;
    }

    public void setNoOfOverCoat(String noOfOverCoat) {
        NoOfOverCoat = noOfOverCoat;
    }

    public String getNoOfItem1() {
        return NoOfItem1;
    }

    public void setNoOfItem1(String noOfItem1) {
        NoOfItem1 = noOfItem1;
    }

    public String getNoOfItem2() {
        return NoOfItem2;
    }

    public void setNoOfItem2(String noOfItem2) {
        NoOfItem2 = noOfItem2;
    }

    public String getNoOfItem3() {
        return NoOfItem3;
    }

    public void setNoOfItem3(String noOfItem3) {
        NoOfItem3 = noOfItem3;
    }

    public String getNoOfItem4() {
        return NoOfItem4;
    }

    public void setNoOfItem4(String noOfItem4) {
        NoOfItem4 = noOfItem4;
    }

    public String getNoOfItem5() {
        return NoOfItem5;
    }

    public void setNoOfItem5(String noOfItem5) {
        NoOfItem5 = noOfItem5;
    }

    public String getTotBags() {
        return TotBags;
    }

    public void setTotBags(String totBags) {
        TotBags = totBags;
    }

    public String getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(String companyId) {
        CompanyId = companyId;
    }

    public String getBranchId() {
        return BranchId;
    }

    public void setBranchId(String branchId) {
        BranchId = branchId;
    }

    public String getEmpId() {
        return EmpId;
    }

    public void setEmpId(String empId) {
        EmpId = empId;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
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
        dest.writeString(this.id);
        dest.writeString(this.AutoId);
        dest.writeString(this.TokenNo);
        dest.writeString(this.RefNo);
        dest.writeString(this.TrDate);
        dest.writeString(this.OptDate);
        dest.writeString(this.AccId);
        dest.writeString(this.AccName);
        dest.writeString(this.AccCode);
        dest.writeString(this.IsAlteration);
        dest.writeString(this.MobileNo);
        dest.writeString(this.NoOfChurider);
        dest.writeString(this.NoOfAnarkali);
        dest.writeString(this.NoOfShawl);
        dest.writeString(this.NoOfSaree);
        dest.writeString(this.NoOfFrock);
        dest.writeString(this.NoOfBlouse);
        dest.writeString(this.NoOfTopSkirt);
        dest.writeString(this.NoOfGown);
        dest.writeString(this.NoOfOverCoat);
        dest.writeString(this.NoOfItem1);
        dest.writeString(this.NoOfItem2);
        dest.writeString(this.NoOfItem3);
        dest.writeString(this.NoOfItem4);
        dest.writeString(this.NoOfItem5);
        dest.writeString(this.TotBags);
        dest.writeString(this.CompanyId);
        dest.writeString(this.BranchId);
        dest.writeString(this.EmpId);
        dest.writeString(this.UserId);
        dest.writeString(this.itemName);
        dest.writeString(this.imageUrl);
        dest.writeInt(this.imageDrawable);
        dest.writeInt(this.itemCount);
    }

    public ProductData2() {
    }

    protected ProductData2(Parcel in) {
        this.id = in.readString();
        this.AutoId = in.readString();
        this.TokenNo = in.readString();
        this.RefNo = in.readString();
        this.TrDate = in.readString();
        this.OptDate = in.readString();
        this.AccId = in.readString();
        this.AccName = in.readString();
        this.AccCode = in.readString();
        this.IsAlteration = in.readString();
        this.MobileNo = in.readString();
        this.NoOfChurider = in.readString();
        this.NoOfAnarkali = in.readString();
        this.NoOfShawl = in.readString();
        this.NoOfSaree = in.readString();
        this.NoOfFrock = in.readString();
        this.NoOfBlouse = in.readString();
        this.NoOfTopSkirt = in.readString();
        this.NoOfGown = in.readString();
        this.NoOfOverCoat = in.readString();
        this.NoOfItem1 = in.readString();
        this.NoOfItem2 = in.readString();
        this.NoOfItem3 = in.readString();
        this.NoOfItem4 = in.readString();
        this.NoOfItem5 = in.readString();
        this.TotBags = in.readString();
        this.CompanyId = in.readString();
        this.BranchId = in.readString();
        this.EmpId = in.readString();
        this.UserId = in.readString();
        this.itemName = in.readString();
        this.imageUrl = in.readString();
        this.imageDrawable = in.readInt();
        this.itemCount = in.readInt();
    }

    public static final Parcelable.Creator<ProductData2> CREATOR = new Parcelable.Creator<ProductData2>() {
        @Override
        public ProductData2 createFromParcel(Parcel source) {
            return new ProductData2(source);
        }

        @Override
        public ProductData2[] newArray(int size) {
            return new ProductData2[size];
        }
    };
}
