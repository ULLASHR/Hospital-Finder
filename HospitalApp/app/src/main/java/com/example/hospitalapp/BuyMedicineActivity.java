package com.example.hospitalapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;  // Adding log for debugging
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class BuyMedicineActivity extends AppCompatActivity {

    private String[][] packages = {
            {"Uprise-03 1000IU Capsule", "", "", "", "50"},
            {"HealthVit Chromium Picolinate 200mcg Capsule", "", "", "", "305"},
            {"Vitamin B Complex Capsules", "", "", "", "448"},
            {"Inlife Vitamin E Wheat Germ Oil Capsule", "", "", "", "539"},
            {"Dolo 650 Tablet", "", "", "", "30"},
            {"Crocin 650 Advance Tablet", "", "", "", "50"},
            {"Strepsils Medicated Lozenges for Sore Throat", "", "", "", "40"},
            {"Tata 1mg Calcium + Vitamin D3", "", "", "", "30"},
            {"Feronia -XT Tablet", "", "", "", "130"},
    };

    private String[] package_details = {
            "Building and keeping the bones & teeth strong\nReducing Fatigue/stress and muscular pains\nBoosting immunity and increasing resistance against infection",
            "Chromium is an essential trace mineral that helps insulin regulate blood glucose",
            "Provides relief from vitamin B deficiencies\nHelps in formation of red blood cells\nMaintains healthy nervous system",
            "Promotes health as well as skin benefit.\nHelps reduce skin blemish and pigmentation.",
            "Dolo 650 Tablet helps relieve pain and fever by blocking the release of certain chemical messengers responsible for fever and pain",
            "Helps relieve fever and bring down a high temperature\nSuitable for people with a heart condition or high blood pressure",
            "Relieves symptoms of bacterial throat infections and soothes recovery\nProvides a warm and comforting feeling during sore throat",
            "Reduces the risk of calcium deficiency, Rickets, and Osteoporosis\nPromotes mobility and flexibility of joints",
            "Helps reduce iron deficiency due to chronic blood loss or low intake of iron"
    };

    private ArrayList<HashMap<String, String>> list;
    private SimpleAdapter sa;
    private ListView lst;
    private Button btnBack, btnGoToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine);

        lst = findViewById(R.id.listViewBM);
        btnBack = findViewById(R.id.buttonBMBack);
        btnGoToCart = findViewById(R.id.buttonBMGoToCart);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Go to home screen
                startActivity(new Intent(BuyMedicineActivity.this, HomeActivity.class));
            }
        });

        // Prepare the data for the list view
        list = new ArrayList<>();
        for (int i = 0; i < packages.length; i++) {
            HashMap<String, String> item = new HashMap<>();
            item.put("line1", packages[i][0]);
            item.put("line2", packages[i][1]);
            item.put("line3", packages[i][2]);
            item.put("line4", packages[i][3]);
            item.put("line5", "Total Cost: " + packages[i][4] + "/-");
            list.add(item);
        }

        // Bind data to the ListView using SimpleAdapter
        sa = new SimpleAdapter(this, list, R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});
        lst.setAdapter(sa);

        // Set up an item click listener to navigate to the details screen
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Log the data being passed to verify it's correct
                Log.d("BuyMedicineActivity", "Clicked item: " + packages[i][0] + " - " + package_details[i] + " - " + packages[i][4]);

                // Create and start an Intent to go to the details screen
                Intent it = new Intent(BuyMedicineActivity.this, BuyMedicineDetailsActivity.class);
                it.putExtra("text1", packages[i][0]); // Package name
                it.putExtra("text2", package_details[i]); // Package details
                it.putExtra("text3", packages[i][4]); // Total cost
                startActivity(it);  // Start activity
            }
        });

        // Go to Cart functionality
        btnGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineActivity.this, CartBuyMedicineActivity.class));
            }
        });
    }
}
