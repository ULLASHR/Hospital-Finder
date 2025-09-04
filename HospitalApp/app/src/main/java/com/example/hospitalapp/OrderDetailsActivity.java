package com.example.hospitalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderDetailsActivity extends AppCompatActivity {

    private String[][] order_details = {};

    HashMap<String, String> item;
    ArrayList list;
    SimpleAdapter sa;
    ListView lst;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        // Check if the user is logged in
        SharedPreferences sharedpreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedpreferences.getString("username", "");

        if (username.isEmpty()) {
            // User is not logged in, redirect to LoginActivity
            startActivity(new Intent(OrderDetailsActivity.this, LoginActivity.class));
            finish();  // Close the current activity to prevent going back to it
            return;     // Stop further execution of this activity
        }

        Button btn = findViewById(R.id.buttonLTBack);
        ListView lst = findViewById(R.id.listViewLT);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate back to HomeActivity
                startActivity(new Intent(OrderDetailsActivity.this, HomeActivity.class));
            }
        });

        // Now proceed to fetch the order details and populate the ListView
        Database db = new Database(getApplicationContext(), "healthcare", null, 1);
        ArrayList<String> dbData = db.getOrderData(username);
        order_details = new String[dbData.size()][];

        for (int i = 0; i < order_details.length; i++) {
            order_details[i] = new String[5];
            String arrData = dbData.get(i).toString();
            String[] strData = arrData.split(java.util.regex.Pattern.quote("$"));
            // Full name (strData[0])
            order_details[i][0] = strData[0];
            // Address (strData[1])
            order_details[i][1] = strData[1];
            // Price (strData[6])
            order_details[i][2] = "Rs." + strData[6];
            // Delivery info
            if (strData[7].compareTo("medicine") == 0) {
                // If the order type is 'medicine'
                order_details[i][3] = "Del: " + strData[4];  // Delivery date is in strData[4]
            } else {
                // If the order type is 'lab' or any other order type
                order_details[i][3] = "Del: " + strData[4] + ", " + strData[5];  // Delivery date in strData[4], additional info in strData[5]
            }
            // Order type (strData[7])
            order_details[i][4] = strData[7];
        }
        // Populate the ListView with order details
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        for (int i = 0; i < order_details.length; i++) {
            HashMap<String, String> item = new HashMap<>();
            item.put("line1", order_details[i][0]);
            item.put("line2", order_details[i][1]);
            item.put("line3", order_details[i][2]);
            item.put("line4", order_details[i][3]);
            item.put("line5", order_details[i][4]);
            list.add(item);
        }
        SimpleAdapter sa = new SimpleAdapter(this, list,
                R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});
        lst.setAdapter(sa);
    }

}
