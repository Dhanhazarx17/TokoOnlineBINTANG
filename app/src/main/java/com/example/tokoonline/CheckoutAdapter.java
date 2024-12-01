package com.example.tokoonline;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CheckoutAdapter extends RecyclerView.Adapter<CheckoutAdapter.CheckoutViewHolder> {

    private final Context context;
    private final List<Product> productList;

    public CheckoutAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public CheckoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_checkout, parent, false);
        return new CheckoutViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CheckoutViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.nameTextView.setText(product.getName());
        holder.priceTextView.setText(String.format("Rp %.2f", product.getPrice()));
        holder.quantityTextView.setText(String.format("x%d", product.getQuantity()));

        Glide.with(context).load(product.getImageUrl()).into(holder.productImageView);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class CheckoutViewHolder extends RecyclerView.ViewHolder {
        final TextView nameTextView, priceTextView, quantityTextView;
        final ImageView productImageView;

        public CheckoutViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.checkoutProductName);
            priceTextView = itemView.findViewById(R.id.checkoutProductPrice);
            quantityTextView = itemView.findViewById(R.id.checkoutProductQuantity);
            productImageView = itemView.findViewById(R.id.checkoutProductImage);
        }
    }
}
