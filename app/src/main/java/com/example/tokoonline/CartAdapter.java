package com.example.tokoonline;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private final Context context;
    private final List<Product> cartProducts;
    private final List<Product> selectedProducts = new ArrayList<>();
    private final OnQuantityChangeListener quantityChangeListener;

    public CartAdapter(Context context, List<Product> cartProducts, OnQuantityChangeListener quantityChangeListener) {
        this.context = context;
        this.cartProducts = cartProducts;
        this.quantityChangeListener = quantityChangeListener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Product product = cartProducts.get(position);

        // Set data produk ke elemen UI
        holder.cartProductNameTextView.setText(product.getName());
        holder.cartProductPriceTextView.setText(String.format("Rp %.2f", product.getPrice()));
        holder.cartProductQuantityTextView.setText(String.valueOf(product.getQuantity()));

        // Load gambar menggunakan Glide
        Glide.with(context).load(product.getImageUrl()).into(holder.cartProductImageView);

        // Set checkbox state
        holder.cartItemCheckbox.setChecked(selectedProducts.contains(product));

        // Listener untuk checkbox
        holder.cartItemCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                CartManager.getInstance().selectProduct(product);
            } else {
                CartManager.getInstance().deselectProduct(product);
            }
            quantityChangeListener.onQuantityChanged(); // Memperbarui total harga
        });

        // Listener untuk tombol kurang
        holder.decreaseQuantityButton.setOnClickListener(v -> {
            if (product.getQuantity() > 1) {
                product.setQuantity(product.getQuantity() - 1);
                notifyItemChanged(position);
                quantityChangeListener.onQuantityChanged();
            }
        });

        // Listener untuk tombol tambah
        holder.increaseQuantityButton.setOnClickListener(v -> {
            product.setQuantity(product.getQuantity() + 1);
            notifyItemChanged(position);
            quantityChangeListener.onQuantityChanged();
        });
    }

    @Override
    public int getItemCount() {
        return cartProducts.size();
    }

    public List<Product> getSelectedProducts() {
        return selectedProducts;
    }

    public double calculateSelectedTotalPrice() {
        double totalPrice = 0;
        for (Product product : selectedProducts) {
            totalPrice += product.getPrice() * product.getQuantity();
        }
        return totalPrice;
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        final TextView cartProductNameTextView, cartProductPriceTextView, cartProductQuantityTextView;
        final ImageView cartProductImageView;
        final Button decreaseQuantityButton, increaseQuantityButton;
        final CheckBox cartItemCheckbox;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            cartProductNameTextView = itemView.findViewById(R.id.cartProductNameTextView);
            cartProductPriceTextView = itemView.findViewById(R.id.cartProductPriceTextView);
            cartProductQuantityTextView = itemView.findViewById(R.id.cartProductQuantityTextView);
            cartProductImageView = itemView.findViewById(R.id.cartProductImageView);
            decreaseQuantityButton = itemView.findViewById(R.id.decreaseQuantityButton);
            increaseQuantityButton = itemView.findViewById(R.id.increaseQuantityButton);
            cartItemCheckbox = itemView.findViewById(R.id.cartItemCheckbox);
        }
    }

    public interface OnQuantityChangeListener {
        void onQuantityChanged();
    }
}
