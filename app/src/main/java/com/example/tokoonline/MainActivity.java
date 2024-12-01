package com.example.tokoonline;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView productRecyclerView;
    private EditText searchEditText;
    private ProductAdapter productAdapter;
    private List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productRecyclerView = findViewById(R.id.productRecyclerView);
        searchEditText = findViewById(R.id.searchEditText);
        ImageButton cartIconButton = findViewById(R.id.cartIconButton);

        // Dummy data produk
        productList = new ArrayList<>();
        productList.add(new Product(1, "Laptop", "Laptop Gaming", 15000000, "https://example.com/laptop.jpg"));
        productList.add(new Product(2, "Smartphone", "Smartphone Android", 5000000, "https://example.com/smartphone.jpg"));

        // Set up produk RecyclerView
        productAdapter = new ProductAdapter(this, productList, product -> {
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("productId", product.getId()); // Pastikan ID produk dikirim
            intent.putExtra("productName", product.getName());
            intent.putExtra("productDescription", product.getDescription());
            intent.putExtra("productPrice", product.getPrice());
            intent.putExtra("productImage", product.getImageUrl());
            startActivity(intent);
        });

        productRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        productRecyclerView.setAdapter(productAdapter);

        // Aksi ikon keranjang
        cartIconButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CartActivity.class);
            startActivity(intent);
        });
    }
}
