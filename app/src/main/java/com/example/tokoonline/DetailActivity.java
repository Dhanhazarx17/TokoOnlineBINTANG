package com.example.tokoonline;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Ambil data dari Intent
        int productId = getIntent().getIntExtra("productId", -1); // Default ID -1 jika tidak ditemukan
        String productName = getIntent().getStringExtra("productName");
        String productDescription = getIntent().getStringExtra("productDescription");
        double productPrice = getIntent().getDoubleExtra("productPrice", 0.0);
        String productImage = getIntent().getStringExtra("productImage");

        // Inisialisasi elemen UI
        TextView nameTextView = findViewById(R.id.detailNameTextView);
        TextView descriptionTextView = findViewById(R.id.detailDescriptionTextView);
        TextView priceTextView = findViewById(R.id.detailPriceTextView);
        ImageView productImageView = findViewById(R.id.detailProductImageView);
        Button addToCartButton = findViewById(R.id.addToCartButton);

        // Set data ke elemen UI
        nameTextView.setText(productName);
        descriptionTextView.setText(productDescription);
        priceTextView.setText(String.format("Rp %.2f", productPrice));
        Glide.with(this).load(productImage).into(productImageView);

        // Tambahkan listener untuk tombol "Tambah ke Keranjang"
        addToCartButton.setOnClickListener(v -> {
            if (productId != -1) { // Pastikan ID valid
                Product product = new Product(productId, productName, productDescription, productPrice, productImage);
                product.setQuantity(1); // Set jumlah default menjadi 1
                CartManager.getInstance().addProduct(product);
                Toast.makeText(DetailActivity.this, "Barang ditambahkan ke keranjang", Toast.LENGTH_SHORT).show();
                finish(); // Kembali ke halaman sebelumnya
            } else {
                Toast.makeText(DetailActivity.this, "ID produk tidak valid", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
