package com.theresa.boutique.model;

public class OrderDress {

    String id, name, AccName, AccCode, AutoId;

    int image,bodyMesurementType;


    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public int getBodyMesurementType() {
        return bodyMesurementType;
    }

    public void setBodyMesurementType(int bodyMesurementType) {
        this.bodyMesurementType = bodyMesurementType;
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

    public String getAutoId() {
        return AutoId;
    }

    public void setAutoId(String autoId) {
        AutoId = autoId;
    }


}
