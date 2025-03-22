package com.datj.mobile.data.remote.model;

import java.io.Serializable;
import java.util.List;

public class Accessory implements Serializable {
    private int accessoryId;
    private int karat;
    private double materialWeight;
    private String name;
    private int quantity;
    private AccessoryType accessoryType;
    private boolean isHidden;
    private Shape shape;
    private List<AccessoryImage> accessoryImages;

    public int getAccessoryId() { return accessoryId; }
    public String getName() { return name; }
    public List<AccessoryImage> getAccessoryImages() { return accessoryImages; }
    public AccessoryType getAccessoryType() {
        return accessoryType;
    }

    public int getKarat() {
        return karat;
    }

    public double getMaterialWeight() {
        return materialWeight;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public Shape getShape() {
        return shape;
    }
}
