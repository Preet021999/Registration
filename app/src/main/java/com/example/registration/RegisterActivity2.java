package com.example.registration;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity2 extends AppCompatActivity {

    AlertDialog.Builder builder;
    Dialog dialog;
     CheckBox checkBox,checkBox2;
    Button btn_d_reg,add;

    EditText UserName,PhoneNumber,Email,Password,C_Password;

    String uname,phone,email,pass,c_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        TextView btn=findViewById(R.id.alreadyHaveAccount);
         btn_d_reg=findViewById(R.id.d_btnRegister);
         UserName = findViewById(R.id.inputUsername);
         PhoneNumber = findViewById(R.id.inputPhone);
        Email = findViewById(R.id.inputEmail);
        Password = findViewById(R.id.inputPassword);
        C_Password = findViewById(R.id.inputConformPassword);
        add = findViewById(R.id.add);




       //--------------------------Add Document-----------------
        
        dialog = new Dialog(this);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                document();
            }
        });
        
        
        
        //------------------------------------------------------
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity2.this,LoginActivity.class));
            }
        });

        btn_d_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validation();
                Toast.makeText(RegisterActivity2.this, "Registration Successful", Toast.LENGTH_SHORT).show();

            }
        });

        //------------------------------------------------------------------



        checkBox2 = findViewById(R.id.Spass);
        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                  Password.setTransformationMethod(null);
                    C_Password.setTransformationMethod(null);
                }else{
                    Password.setTransformationMethod(new PasswordTransformationMethod());
                    C_Password.setTransformationMethod(new PasswordTransformationMethod());
                }

            }
        });

       //------------------------------------------------------------------
        checkBox = findViewById(R.id.terms1);
        builder = new AlertDialog.Builder(this);
        btn_d_reg.setEnabled(false);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    builder.setTitle("Terms And Conditions");
                    builder.setMessage("This is Your Description");

                    builder.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            btn_d_reg.setEnabled(true);
                            dialogInterface.dismiss();
                        }
                    });
                    builder.setNegativeButton("Decline", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            checkBox.setChecked(false);
                        }
                    });
                    builder.show();
                }else {
                    btn_d_reg.setEnabled(false);
                }
            }
        });


    }

    private void document() {
        dialog.setContentView(R.layout.documents);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.blue(5)));

        Button submit = dialog.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                Toast.makeText(RegisterActivity2.this, "Data Submited", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();



    }

    private void validation() {
        uname = UserName.getText().toString();
        phone = PhoneNumber.getText().toString();

        email = Email.getText().toString();
        pass = Password.getText().toString();
        c_pass= C_Password.getText().toString();

        if (uname.isEmpty()){
            UserName.setError("Please Fill this Filed");
            UserName.requestFocus();
        }
        if (phone.isEmpty()){
            PhoneNumber.setError("Please Fill this Filed");
            PhoneNumber.requestFocus();
        }

        if (email.isEmpty()){
            Email.setError("Please Fill this Filed");
            Email.requestFocus();
        }
        if (pass.isEmpty()){
            Password.setError("Please Fill this Filed");
            Password.requestFocus();
        }
        if (c_pass.isEmpty()){
            C_Password.setError("Please Fill this Filed");
            C_Password.requestFocus();
        }
        else {
            startActivity(new Intent(RegisterActivity2.this,LoginActivity.class));
        }
    }
}


