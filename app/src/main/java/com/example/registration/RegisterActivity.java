package com.example.registration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private CheckBox checkBox,checkBox2;


    EditText UserName,PhoneNumber,Address,Email,Password,C_Password;
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    TextView back;
    Button btn_driver;
    Button btn_reg;
    AlertDialog.Builder builder;

    String uname,phone,address,email,pass,c_pass;
    private Object ProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

      //REGISTER - ALREADY HAVE ACC - DRIVER REGISTRATION  CLICKS

       UserName = findViewById(R.id.inputUsername);
       PhoneNumber = findViewById(R.id.inputPhone);
       Address = findViewById(R.id.inputAddress);
       Email = findViewById(R.id.inputEmail);
       Password = findViewById(R.id.inputPassword);
       C_Password = findViewById(R.id.inputConformPassword);

        back=findViewById(R.id.alreadyHaveAccount);
        btn_driver=findViewById(R.id.r_driver);
        btn_reg = findViewById(R.id.c_btnRegister);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        ProgressDialog = new ProgressDialog(this);
        

        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });
        btn_driver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,RegisterActivity2.class));
            }
        });

        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerforAuth();
                validation();
            }
        });


        //CHECKBOX TERMS and CONDITION

        checkBox = findViewById(R.id.terms1);


        builder = new AlertDialog.Builder(this);

        btn_reg.setEnabled(false);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    builder.setTitle("Terms And Conditions");
                    builder.setMessage("This is Your Description");

                    builder.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            btn_reg.setEnabled(true);
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
                    btn_reg.setEnabled(false);
                }
            }
        });

        //Password Show
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


    }

    private void PerforAuth() {
        uname = UserName.getText().toString();
        phone = PhoneNumber.getText().toString();
        address = Address.getText().toString();
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
        if (address.isEmpty()){
            Address.setError("Please Fill this Filed");
            Address.requestFocus();
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
                progressDialog.setMessage("Please wait while Registration");
                progressDialog.setTitle("Registration");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            progressDialog.dismiss();
                            sendUserToNextActivity();
                            Toast.makeText(RegisterActivity.this, "Registration Successfull", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            progressDialog.dismiss();
                            Toast.makeText(RegisterActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        }
    }

    private void sendUserToNextActivity() {
        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    private void validation() {
        uname = UserName.getText().toString();
        phone = PhoneNumber.getText().toString();
        address = Address.getText().toString();
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
        if (address.isEmpty()){
            Address.setError("Please Fill this Filed");
            Address.requestFocus();
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
            startActivity(new Intent(RegisterActivity.this,customer_home.class));
        }
    }
}
