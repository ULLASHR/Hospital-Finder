package com.example.hospitalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {
    private String[][] doctor_details1 = {
            {"Doctor Name : Ajit Saste", "Mobile N0 : 9898989898", "Exp : 5 yrs", "", "400"},
            {"Doctor Name : Prasad Pawar", "Mobile No : 789969898", "Exp : 15 yrs", "", "900"},
            {"Doctor Name : Swapnil Kale", "Mobile No : 8898345628", "Exp : 8 yrs", "", "300"},
            {"Doctor Name : Deepak Deshmukh", "Mobile No : 9898000000", "Exp : 6 yrs", "", "500"},
            {"Doctor Name : Ashok Panda", "Mobile No : 7799850099", "Exp : 7 yrs", "", "800"}
    };
    private String[][] doctor_details2 = {
            {"Doctor Name : Neelam Patil", "Mobile N0 : 9898989898", "Exp : 5 yrs", "", "400"},
            {"Doctor Name : Swati Pawar", "Mobile No : 789969898", "Exp : 15 yrs", "", "900"},
            {"Doctor Name : Neeraja Kale", "Mobile No : 8898345628", "Exp : 8 yrs", "", "300"},
            {"Doctor Name : Mayuri Deshmukh", "Mobile No : 9898000000", "Exp : 6 yrs", "", "500"},
            {"Doctor Name : Minakshi Panda", "Mobile No : 7799850099", "Exp : 7 yrs", "", "800"}
    };
    private String[][] doctor_details3 = {
            {"Doctor Name : Seema Patil", "Mobile N0 : 9898989898", "Exp : 5 yrs", "", "400"},
            {"Doctor Name : Pankaj Parab", "Mobile No : 789969898", "Exp : 15 yrs", "", "900"},
            {"Doctor Name : Monish Jain", "Mobile No : 8898345628", "Exp : 8 yrs", "", "300"},
            {"Doctor Name : Vishal Deshmukh", "Mobile No : 9898000000", "Exp : 6 yrs", "", "500"},
            {"Doctor Name : Shrikant Panda", "Mobile No : 7799850099", "Exp : 7 yrs", "", "800"}
    };
    private String[][] doctor_details4 = {
            {"Doctor Name : Ajit Saste", "Mobile N0 : 9898989898", "Exp : 5 yrs", "", "400"},
            {"Doctor Name : Prasad Pawar", "Mobile No : 789969898", "Exp : 15 yrs", "", "900"},
            {"Doctor Name : Swapnil Kale", "Mobile No : 8898345628", "Exp : 8 yrs", "", "300"},
            {"Doctor Name : Deepak Deshmukh", "Mobile No : 9898000000", "Exp : 6 yrs", "", "500"},
            {"Doctor Name : Ashok Panda", "Mobile No : 7799850099", "Exp : 7 yrs", "", "800"}
    };
    private String[][] doctor_details5 = {
            {"Doctor Name : Amol Gawade", "Mobile N0 : 9898989898", "Exp : 5 yrs", "", "400"},
            {"Doctor Name : Prasad Pawar", "Mobile No : 789969898", "Exp : 15 yrs", "", "900"},
            {"Doctor Name : Nitesh Kale", "Mobile No : 8898345628", "Exp : 8 yrs", "", "300"},
            {"Doctor Name : Deepak Deshmukh", "Mobile No : 9898000000", "Exp : 6 yrs", "", "500"},
            {"Doctor Name : Ashok Singh", "Mobile No : 7799850099", "Exp : 7 yrs", "", "800"}
    };
    TextView tv;
    Button btn;
    String[][] doctor_details = {};
    HashMap<String,String> item;
    ArrayList list;
    ListView lst;
    SimpleAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        tv = findViewById(R.id.textViewDDTitle);
        btn = findViewById(R.id.bottomDDBack);
        lst=findViewById(R.id.listViewDD);

        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        if(title.compareTo("Family Physicians")==0)
            doctor_details = doctor_details1;
        else
        if(title.compareTo("Dietician")==0)
            doctor_details = doctor_details2;
        else
        if(title.compareTo("Dantist")==0)
            doctor_details = doctor_details3;
        else
        if(title.compareTo("Surgron")==0)
            doctor_details = doctor_details4;
        else
            doctor_details = doctor_details5;

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorDetailsActivity.this, FindDoctorActivity.class));
            }
        });

        list = new ArrayList();
        for(int i=0;i<doctor_details.length;i++){
            item=new HashMap<String,String>();
            item.put("line1",doctor_details[i][0]);
            item.put("line2",doctor_details[i][1]);
            item.put("line3",doctor_details[i][2]);
            item.put("line4",doctor_details[i][3]);
            item.put("line5", "Cons Fees:"+doctor_details[i][4]+"/-");
            list.add(item);
        }
        sa=new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[] {"line1","line2","line3","line4","line5"},
                new int[] {R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e,});
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Log the data being passed to verify it's correct
                //Log.d("DoctorDetails", "Clicked item: " + packages[i][0] + " - " + package_details[i] + " - " + packages[i][4]);

                // Create and start an Intent to go to the details screen
                Intent it = new Intent(DoctorDetailsActivity.this, BookAppointmentActivity.class);
                it.putExtra("text1", title); // Package name
                it.putExtra("text2", doctor_details[i][0]);
                it.putExtra("text3", doctor_details[i][1]);
                it.putExtra("text4", doctor_details[i][2]);
                //it.putExtra("text5", doctor_details[i][3]);
                it.putExtra("text5", doctor_details[i][4]);
                startActivity(it);  // Start activity
            }
        });
    }
}