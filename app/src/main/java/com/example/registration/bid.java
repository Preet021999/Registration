package com.example.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class bid extends AppCompatActivity {

    TextView C_bid;
    EditText Amount;
    Button Exit,E_Amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bid);

        C_bid = findViewById(R.id.C_bid);
        Amount = findViewById(R.id.Amount);
        Exit = findViewById(R.id.exit);
        E_Amount = findViewById(R.id.Enter_amount);

        Exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(bid.this,driver_home.class));
            }

        });
        E_Amount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                C_bid.setText(Amount.getText()+"  $");
            }
        });
    }
}