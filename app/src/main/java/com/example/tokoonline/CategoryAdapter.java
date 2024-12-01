package com.example.tokoonline;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private final Context context;
    private final List<String> categoryList;

    // Konstruktor
    public CategoryAdapter(Context context, List<String> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        String category = categoryList.get(position);

        // Set data kategori ke TextView
        holder.categoryTextView.setText(category);
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    // ViewHolder untuk memegang elemen UI dari item kategori
    public static class CategoryViewHolder extends RecyclerView.ViewHolder {
        final TextView categoryTextView;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryTextView = itemView.findViewById(R.id.categoryTextView);
        }
    }
}
