package com.datj.mobile.data.remote.model;

public class CartItem {
    private Accessory accessory;
    private String selectedSize;
    private int quantity;

    public CartItem(Accessory accessory, String selectedSize, int quantity) {
        this.accessory = accessory;
        this.selectedSize = selectedSize;
        this.quantity = quantity;
    }

    public Accessory getAccessory() { return accessory; }
    public String getSelectedSize() { return selectedSize; }
    public int getQuantity() { return quantity; }
}