package com.example.registration;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class test extends AppCompatActivity {

    public CheckBox checkBox;
    public Button button;
    public MaterialAlertDialogBuilder materialAlertDialogBuilder;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);



        checkBox = findViewById(R.id.c1);
        button=findViewById(R.id.b1);

       // materialAlertDialogBuilder =new MaterialAlertDialogBuilder(this);
        builder = new AlertDialog.Builder(this);


        button.setEnabled(false);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(test.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                 if(b){

                     builder.setTitle("HIIIII");
                     builder.setMessage("CAME CHO");
                     builder.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialogInterface, int i) {
                             button.setEnabled(true);
                             dialogInterface.dismiss();

                         }
                     });
/*
                     builder.setTitle("HIIII")
                             .setMessage("darpan")
                             .setCancelable(true)
                             .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                                         @Override
                                                         public void onClick(DialogInterface dialogInterface, int i) {
                                                             finish();
                                                         }
                                                     })
                             .setNegativeButton("no", new DialogInterface.OnClickListener() {
                                 @Override
                                 public void onClick(DialogInterface dialogInterface, int i) {
                                     dialogInterface.cancel();
                                 }
                             })
                             .show();*/

                        builder.setNegativeButton("Decline", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                checkBox.setChecked(false);
                            }
                        });
                        builder.show();

                 }else{
                     button.setEnabled(false);
                 }

            }
        });
    }
}