package com.example.hospitalapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;  // Adding log for debugging
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BuyMedicineDetailsActivity extends AppCompatActivity {

    TextView tvPackageName, tvTotalCost;
    EditText edDetails;
    Button btnAddToCart, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_details);

        // Initialize views
        tvPackageName = findViewById(R.id.textViewBMDPackageName);
        tvTotalCost = findViewById(R.id.textViewBMDTotalCost);
        edDetails = findViewById(R.id.editTextBMDMultiLine);
        edDetails.setKeyListener(null);  // Disable editing
        btnAddToCart = findViewById(R.id.buttonBMDAddToCart);
        btnBack = findViewById(R.id.buttonBMDGoBack);

        // Get data from the Intent and log it for debugging
        Intent intent = getIntent();
        String packageName = intent.getStringExtra("text1");
        String packageDetails = intent.getStringExtra("text2");
        String totalCost = intent.getStringExtra("text3");

        // Log received data for debugging
        Log.d("BuyMedicineDetailsActivity", "Received data: " + packageName + " - " + packageDetails + " - " + totalCost);

        // Set data to the views
        tvPackageName.setText(packageName);
        edDetails.setText(packageDetails);
        tvTotalCost.setText("Total Cost: " + totalCost + "/-");

        // Back button functionality
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineDetailsActivity.this, BuyMedicineActivity.class));
            }
        });

        // Add to cart functionality
        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedpreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedpreferences.getString("username", "");

                // Get product name and price
                String product = tvPackageName.getText().toString();
                float price = Float.parseFloat(totalCost);

                // Log for debugging
                Log.d("BuyMedicineDetailsActivity", "Adding to cart: " + product + " - " + price);

                // Handle adding to cart (Database interaction here)
                Database db = new Database(getApplicationContext(), "healthcare", null, 1);
                if (db.checkCart(username, product) == 1) {
                    Toast.makeText(getApplicationContext(), "Product Already Added", Toast.LENGTH_SHORT).show();
                } else {
                    db.addCart(username, product, price, "medicine");
                    Toast.makeText(getApplicationContext(), "Record Inserted to Cart", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BuyMedicineDetailsActivity.this, BuyMedicineActivity.class));
                }
            }
        });
    }
}

