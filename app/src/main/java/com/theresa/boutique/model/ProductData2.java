package com.theresa.boutique.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ProductData2 implements Parcelable {

    int id;
    int AutoId;
    int TokenNo;
    int RefNo;
    int TrDate;
    int OptDate;
    int AccId;
    String AccName;
    int AccCode;
    String IsAlteration;
    int MobileNo;
    int NoOfChurider;
    int NoOfAnarkali;
    int NoOfShawl;
    int NoOfSaree;
    int NoOfFrock;
    int NoOfBlouse;
    int NoOfTopSkirt;
    int NoOfGown;
    int NoOfOverCoat;
    int NoOfItem1;
    int NoOfItem2;
    int NoOfItem3;
    int NoOfItem4;
    int NoOfItem5;
    int TotBags;
    int CompanyId;
    int BranchId;
    int EmpId;
    int UserId;

    String itemName;
    String imageUrl;
    int imageDrawable;
    int itemCount;

    protected ProductData2(Parcel in) {
        //id = in.readInt();
        NoOfChurider=in.readInt();
        NoOfAnarkali=in.readInt();
        NoOfShawl=in.readInt();
        NoOfSaree=in.readInt();
        NoOfFrock=in.readInt();
        NoOfBlouse=in.readInt();
        NoOfTopSkirt=in.readInt();
        NoOfGown=in.readInt();
        NoOfOverCoat=in.readInt();
        NoOfItem1=in.readInt();
        NoOfItem2=in.readInt();
        NoOfItem3=in.readInt();
        NoOfItem4=in.readInt();
        NoOfItem5=in.readInt();
        TotBags=in.readInt();
        CompanyId=in.readInt();
        BranchId=in.readInt();
        EmpId=in.readInt();
        UserId=in.readInt();
        id=in.readInt();
        MobileNo=in.readInt();
        AutoId=in.readInt();
        TokenNo=in.readInt();
        RefNo=in.readInt();
        TrDate=in.readInt();
        OptDate=in.readInt();
        AccId=in.readInt();
        AccName=in.readString();
        AccCode=in.readInt();
        IsAlteration=in.readString();
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

    public int getAutoId() {
        return AutoId;
    }

    public void setAutoId(int autoId) {
        AutoId = autoId;
    }

    public int getTokenNo() {
        return TokenNo;
    }

    public void setTokenNo(int tokenNo) {
        TokenNo = tokenNo;
    }

    public int getTotBags() {
        return TotBags;
    }

    public void setTotBags(int totBags) {
        TotBags = totBags;
    }

    public int getEmpId() {
        return EmpId;
    }

    public void setEmpId(int empId) {
        EmpId = empId;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public int getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(int companyId) {
        CompanyId = companyId;
    }

    public int getBranchId() {
        return BranchId;
    }

    public void setBranchId(int branchId) {
        BranchId = branchId;
    }

    public int getNoOfChurider() {
        return NoOfChurider;
    }

    public void setNoOfChurider(int noOfChurider) {
        NoOfChurider = noOfChurider;
    }

    public int getNoOfAnarkali() {
        return NoOfAnarkali;
    }

    public void setNoOfAnarkali(int noOfAnarkali) {
        NoOfAnarkali = noOfAnarkali;
    }

    public int getNoOfShawl() {
        return NoOfShawl;
    }

    public void setNoOfShawl(int noOfShawl) {
        NoOfShawl = noOfShawl;
    }

    public int getNoOfSaree() {
        return NoOfSaree;
    }

    public void setNoOfSaree(int noOfSaree) {
        NoOfSaree = noOfSaree;
    }

    public int getNoOfFrock() {
        return NoOfFrock;
    }

    public void setNoOfFrock(int noOfFrock) {
        NoOfFrock = noOfFrock;
    }

    public int getNoOfBlouse() {
        return NoOfBlouse;
    }

    public void setNoOfBlouse(int noOfBlouse) {
        NoOfBlouse = noOfBlouse;
    }

    public int getNoOfTopSkirt() {
        return NoOfTopSkirt;
    }

    public void setNoOfTopSkirt(int noOfTopSkirt) {
        NoOfTopSkirt = noOfTopSkirt;
    }

    public int getNoOfGown() {
        return NoOfGown;
    }

    public void setNoOfGown(int noOfGown) {
        NoOfGown = noOfGown;
    }

    public int getNoOfOverCoat() {
        return NoOfOverCoat;
    }

    public void setNoOfOverCoat(int noOfOverCoat) {
        NoOfOverCoat = noOfOverCoat;
    }

    public int getNoOfItem1() {
        return NoOfItem1;
    }

    public void setNoOfItem1(int noOfItem1) {
        NoOfItem1 = noOfItem1;
    }

    public int getNoOfItem2() {
        return NoOfItem2;
    }

    public void setNoOfItem2(int noOfItem2) {
        NoOfItem2 = noOfItem2;
    }

    public int getNoOfItem3() {
        return NoOfItem3;
    }

    public void setNoOfItem3(int noOfItem3) {
        NoOfItem3 = noOfItem3;
    }

    public int getNoOfItem4() {
        return NoOfItem4;
    }

    public void setNoOfItem4(int noOfItem4) {
        NoOfItem4 = noOfItem4;
    }

    public int getNoOfItem5() {
        return NoOfItem5;
    }

    public void setNoOfItem5(int noOfItem5) {
        NoOfItem5 = noOfItem5;
    }

    public int getRefNo() {
        return RefNo;
    }

    public void setRefNo(int refNo) {
        RefNo = refNo;
    }

    public int getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(int mobileNo) {
        MobileNo = mobileNo;
    }

    public int getTrDate() {
        return TrDate;
    }

    public void setTrDate(int trDate) {
        TrDate = trDate;
    }

    public int getOptDate() {
        return OptDate;
    }

    public void setOptDate(int optDate) {
        OptDate = optDate;
    }

    public int getAccId() {
        return AccId;
    }

    public void setAccId(int accId) {
        AccId = accId;
    }

    public String getAccName() {
        return AccName;
    }

    public void setAccName(String accName) {
        AccName = accName;
    }

    public int getAccCode() {
        return AccCode;
    }

    public void setAccCode(int accCode) {
        AccCode = accCode;
    }

    public String getIsAlteration() {
        return IsAlteration;
    }

    public void setIsAlteration(String isAlteration) {
        IsAlteration = isAlteration;
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
        //dest.writeInt(Integer.parseInt(id));
        dest.writeInt(id);
        dest.writeInt(AutoId);
        dest.writeInt(TokenNo);
        dest.writeInt(RefNo);
        dest.writeInt(TrDate);
        dest.writeInt(OptDate);
        dest.writeInt(AccId);
        dest.writeString(AccName);
        dest.writeInt(AccCode);
        dest.writeString(IsAlteration);
        dest.writeInt(MobileNo);
        dest.writeInt(NoOfChurider);
        dest.writeInt(NoOfAnarkali);
        dest.writeInt(NoOfShawl);
        dest.writeInt(NoOfSaree);
        dest.writeInt(NoOfFrock);
        dest.writeInt(NoOfBlouse);
        dest.writeInt(NoOfTopSkirt);
        dest.writeInt(NoOfGown);
        dest.writeInt(NoOfOverCoat);
        dest.writeInt(NoOfItem1);
        dest.writeInt(NoOfItem2);
        dest.writeInt(NoOfItem3);
        dest.writeInt(NoOfItem4);
        dest.writeInt(NoOfItem5);
        dest.writeInt(TotBags);
        dest.writeInt(EmpId);
        dest.writeInt(CompanyId);
        dest.writeInt(BranchId);
        dest.writeInt(UserId);
        dest.writeString(itemName);
        dest.writeString(imageUrl);
        dest.writeInt(imageDrawable);
        dest.writeInt(itemCount);
    }
}
