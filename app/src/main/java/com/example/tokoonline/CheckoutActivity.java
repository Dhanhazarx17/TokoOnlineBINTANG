package com.example.tokoonline;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CheckoutActivity extends AppCompatActivity {

    private RecyclerView checkoutRecyclerView;
    private EditText addressEditText, noteEditText;
    private TextView checkoutTotalPrice;
    private CheckoutAdapter checkoutAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        // Inisialisasi elemen UI
        checkoutRecyclerView = findViewById(R.id.checkoutRecyclerView);
        addressEditText = findViewById(R.id.addressEditText);
        noteEditText = findViewById(R.id.noteEditText);
        checkoutTotalPrice = findViewById(R.id.checkoutTotalPrice);
        Button confirmCheckoutButton = findViewById(R.id.confirmCheckoutButton);

        // Ambil produk yang dipilih dari CartManager
        List<Product> selectedProducts = CartManager.getInstance().getSelectedProducts();

        // Set up RecyclerView untuk daftar produk yang di-checkout
        checkoutAdapter = new CheckoutAdapter(this, selectedProducts);
        checkoutRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        checkoutRecyclerView.setAdapter(checkoutAdapter);

        // Hitung dan tampilkan total harga
        double totalPrice = CartManager.getInstance().calculateSelectedTotalPrice();
        checkoutTotalPrice.setText(String.format("Total: Rp %.2f", totalPrice));

        // Listener untuk tombol konfirmasi checkout
        confirmCheckoutButton.setOnClickListener(v -> {
            String address = addressEditText.getText().toString().trim();
            String note = noteEditText.getText().toString().trim();

            if (address.isEmpty()) {
                Toast.makeText(this, "Alamat tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Lakukan logika checkout (misalnya simpan ke database atau kirim ke server)
            Toast.makeText(this, "Checkout berhasil!\nAlamat: " + address + "\nCatatan: " + note, Toast.LENGTH_LONG).show();

            // Bersihkan produk yang sudah di-checkout
            CartManager.getInstance().clearSelectedProducts();
            finish(); // Tutup halaman
        });
    }
}
