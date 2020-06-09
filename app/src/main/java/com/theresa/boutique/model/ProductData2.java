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

    protected ProductData2(Parcel in) {
        //id = in.readInt();
        NoOfChurider=in.readString();
        NoOfAnarkali=in.readString();
        NoOfShawl=in.readString();
        NoOfSaree=in.readString();
        NoOfFrock=in.readString();
        NoOfBlouse=in.readString();
        NoOfTopSkirt=in.readString();
        NoOfGown=in.readString();
        NoOfOverCoat=in.readString();
        NoOfItem1=in.readString();
        NoOfItem2=in.readString();
        NoOfItem3=in.readString();
        NoOfItem4=in.readString();
        NoOfItem5=in.readString();
        TotBags=in.readString();
        CompanyId=in.readString();
        BranchId=in.readString();
        EmpId=in.readString();
        UserId=in.readString();
        id=in.readString();
        MobileNo=in.readString();
        AutoId=in.readString();
        TokenNo=in.readString();
        RefNo=in.readString();
        TrDate=in.readString();
        OptDate=in.readString();
        AccId=in.readString();
        AccName=in.readString();
        AccCode=in.readString();
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

    public String getTotBags() {
        return TotBags;
    }

    public void setTotBags(String totBags) {
        TotBags = totBags;
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

    public String getRefNo() {
        return RefNo;
    }

    public void setRefNo(String refNo) {
        RefNo = refNo;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
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
        dest.writeString(id);
        dest.writeString(AutoId);
        dest.writeString(TokenNo);
        dest.writeString(RefNo);
        dest.writeString(TrDate);
        dest.writeString(OptDate);
        dest.writeString(AccId);
        dest.writeString(AccName);
        dest.writeString(AccCode);
        dest.writeString(IsAlteration);
        dest.writeString(MobileNo);
        dest.writeString(NoOfChurider);
        dest.writeString(NoOfAnarkali);
        dest.writeString(NoOfShawl);
        dest.writeString(NoOfSaree);
        dest.writeString(NoOfFrock);
        dest.writeString(NoOfBlouse);
        dest.writeString(NoOfTopSkirt);
        dest.writeString(NoOfGown);
        dest.writeString(NoOfOverCoat);
        dest.writeString(NoOfItem1);
        dest.writeString(NoOfItem2);
        dest.writeString(NoOfItem3);
        dest.writeString(NoOfItem4);
        dest.writeString(NoOfItem5);
        dest.writeString(TotBags);
        dest.writeString(EmpId);
        dest.writeString(CompanyId);
        dest.writeString(BranchId);
        dest.writeString(UserId);
        dest.writeString(itemName);
        dest.writeString(imageUrl);
        dest.writeInt(imageDrawable);
        dest.writeInt(itemCount);
    }
}
