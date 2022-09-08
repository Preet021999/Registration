package com.example.registration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    EditText uname,pass;

    Button btnLogin;
    CheckBox checkBox;
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser mUser;

    String id,password;


    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Object ProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = this.getSharedPreferences("login",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        if(sharedPreferences.getString("isLogin","false").equals("true")){
            openDash();
        }

        TextView rPage=findViewById(R.id.textViewSignUp);
         uname=findViewById(R.id.inputEmail);
         pass=findViewById(R.id.inputPassword);
        btnLogin=findViewById(R.id.btnlogin);
        checkBox=findViewById(R.id.Spass);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        ProgressDialog = new ProgressDialog(this);
        //------------------v


       //------------------show password-----------
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
             if(b)
             {
                 pass.setTransformationMethod(null);
             }else{
                 pass.setTransformationMethod(new PasswordTransformationMethod());
             }

            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                PerforAuth();
                validateData();
            }
        });

        rPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });

    }

    private void PerforAuth() {
       id=uname.getText().toString();
       password = pass.getText().toString();
        if(id.isEmpty()){
            uname.setError("Please Fill this Filed");
            uname.requestFocus();
        }
        if(password.isEmpty()){
            pass.setError("Please Fill this Filed");
            pass.requestFocus();
        }
        else if(id.equals("abc") && password.equals("abc")){
            editor.putString("isLogin","true");
            editor.commit();
            openDash();
        }
        else {
                progressDialog.setMessage("Please wait while login");
                progressDialog.setTitle("Login");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
                mAuth.signInWithEmailAndPassword(id,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            progressDialog.dismiss();
                            sendUserToNextActivity();
                            Toast.makeText(LoginActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            progressDialog.dismiss();
                            Toast.makeText(LoginActivity.this,""+task.getException(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        }
    }

    private void sendUserToNextActivity() {
        Intent intent = new Intent(LoginActivity.this,customer_home.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void validateData() {
        id = uname.getText().toString();
        password = pass.getText().toString();

        if(id.isEmpty()){
            uname.setError("Please Fill this Filed");
            uname.requestFocus();
        }
        if(password.isEmpty()){
            pass.setError("Please Fill this Filed");
            pass.requestFocus();
        }
        else if(id.equals("abc") && password.equals("abc")){
            editor.putString("isLogin","true");
            editor.commit();
            openDash();
        }
        else {
            Toast.makeText(this,"Id & PassWord is Incorrect",Toast.LENGTH_SHORT);
        }
    }

    private void openDash() {

        startActivity(new Intent(LoginActivity.this,bid.class));
        finish();

    }


}
