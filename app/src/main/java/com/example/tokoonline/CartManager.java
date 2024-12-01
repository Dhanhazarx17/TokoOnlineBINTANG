package com.example.tokoonline;

import java.util.ArrayList;
import java.util.List;

public class CartManager {

    private static CartManager instance;
    private final List<Product> cartProducts = new ArrayList<>();
    private final List<Product> selectedProducts = new ArrayList<>();

    private CartManager() {
    }

    public static CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    public void addProduct(Product product) {
        boolean productExists = false;

        for (Product existingProduct : cartProducts) {
            if (existingProduct.getId() == product.getId()) {
                existingProduct.setQuantity(existingProduct.getQuantity() + product.getQuantity());
                productExists = true;
                break;
            }
        }

        if (!productExists) {
            cartProducts.add(product);
        }
    }

    public List<Product> getCartProducts() {
        return new ArrayList<>(cartProducts);
    }

    public List<Product> getSelectedProducts() {
        return new ArrayList<>(selectedProducts);
    }

    public void selectProduct(Product product) {
        if (!selectedProducts.contains(product)) {
            selectedProducts.add(product);
        }
    }

    public void deselectProduct(Product product) {
        selectedProducts.remove(product);
    }

    public double calculateSelectedTotalPrice() {
        double totalPrice = 0;
        for (Product product : selectedProducts) {
            totalPrice += product.getPrice() * product.getQuantity();
        }
        return totalPrice;
    }

    public void clearSelectedProducts() {
        for (Product product : selectedProducts) {
            cartProducts.remove(product);
        }
        selectedProducts.clear();
    }
}
