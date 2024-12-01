package com.example.tokoonline;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private final Context context;
    private final List<Product> productList;
    private final OnProductClickListener onProductClickListener;

    public ProductAdapter(Context context, List<Product> productList, OnProductClickListener onProductClickListener) {
        this.context = context;
        this.productList = productList;
        this.onProductClickListener = onProductClickListener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);

        // Set data produk ke elemen UI
        holder.nameTextView.setText(product.getName());
        holder.descriptionTextView.setText(product.getDescription());
        holder.priceTextView.setText(String.format("Rp %.2f", product.getPrice()));
        Glide.with(context).load(product.getImageUrl()).into(holder.productImageView);

        // Listener untuk klik produk
        holder.itemView.setOnClickListener(v -> {
            if (onProductClickListener != null) {
                onProductClickListener.onProductClick(product);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        final TextView nameTextView, descriptionTextView, priceTextView;
        final ImageView productImageView;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
            productImageView = itemView.findViewById(R.id.productImageView);
        }
    }

    public interface OnProductClickListener {
        void onProductClick(Product product);
    }
}
