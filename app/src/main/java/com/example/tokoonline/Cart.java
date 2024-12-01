package com.example.tokoonline;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private static List<Product> cart = new ArrayList<>();

    public static void addProduct(Product product) {
        cart.add(product);
    }

    public static List<Product> getCart() {
        return cart;
    }


}
