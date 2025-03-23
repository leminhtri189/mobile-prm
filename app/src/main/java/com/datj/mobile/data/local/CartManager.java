package com.datj.mobile.data.local;

import com.datj.mobile.data.remote.model.CartItem;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static final List<CartItem> cartItems = new ArrayList<>();

    public static void addItem(CartItem item) {
        cartItems.add(item);
    }

    public static List<CartItem> getItems() {
        return cartItems;
    }

    public static int getTotalQuantity() {
        int total = 0;
        for (CartItem item : cartItems) {
            total += item.getQuantity();
        }
        return total;
    }
    public static void setItems(List<CartItem> items) {
        cartItems.clear();
        cartItems.addAll(items);
    }
    public static void clear() {
        cartItems.clear();
    }
}