package com.example.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class driver_home extends AppCompatActivity {

    Spinner city;
    String[] citys = {"SELECT CITY", "VADODARA","SURAT","RAJKOT","AMDAVAD"};
    Button btnLogout;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_home);

        sharedPreferences = this.getSharedPreferences("login",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        if(sharedPreferences.getString("isLogin","false").equals("false")){
            openLogin();
        }


        city = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,citys);
        city.setAdapter(adapter);



       btnLogout=findViewById(R.id.BtnDriver_logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editor.putString("isLogin","false");
                editor.commit();
                openLogin();


            }
        });
    }

    private void openLogin() {
        startActivity(new Intent(driver_home.this,LoginActivity.class));

    }
}