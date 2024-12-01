package com.example.tokoonline;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CartAdapter cartAdapter;
    private Button checkoutButton;
    private TextView totalPriceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = findViewById(R.id.recyclerViewCart);
        totalPriceTextView = findViewById(R.id.tvTotalPrice);
        Button checkoutButton = findViewById(R.id.checkoutButton); // Tombol checkout

        List<Product> cartProducts = CartManager.getInstance().getCartProducts();

        cartAdapter = new CartAdapter(this, cartProducts, this::updateTotalPrice);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(cartAdapter);

        updateTotalPrice();

        // Listener untuk tombol checkout
        checkoutButton.setOnClickListener(v -> {
            List<Product> selectedProducts = CartManager.getInstance().getSelectedProducts();
            if (selectedProducts.isEmpty()) {
                Toast.makeText(this, "Pilih barang untuk checkout!", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(CartActivity.this, CheckoutActivity.class);
                startActivity(intent);
            }
        });
    }

    private void updateTotalPrice() {
        double totalPrice = CartManager.getInstance().calculateSelectedTotalPrice();
        totalPriceTextView.setText(String.format("Total: Rp %.2f", totalPrice));
    }
}
