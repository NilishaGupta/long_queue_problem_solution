package com.example.puhscks;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity implements View.OnClickListener {
private Button b1,b2;
EditText e1,e2,e3,e4,e5;
static String name=null,mobileno=null,add=null;
ProgressDialog progressDialog;
private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        b1=findViewById(R.id.b1);
        b2=findViewById(R.id.b2);
        e1=findViewById(R.id.e1);
        e2=findViewById(R.id.e2);
        e3=findViewById(R.id.e3);
        e4=findViewById(R.id.e4);
        e5=findViewById(R.id.e5);

        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog =new ProgressDialog(this);
        b1.setOnClickListener(this);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(login.this, signup.class);
                startActivity(i);
               // Toast.makeText(login.this, name, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        if(view==b1){
            registeruser();
        }
    }

    private void registeruser() {

        String email=e1.getText().toString();
        String password=e2.getText().toString();
        name  = e3.getText().toString();
        mobileno = e4.getText().toString();
        add = e5.getText().toString();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "please enter email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "please enter password", Toast.LENGTH_SHORT).show();
            return;
        }
       // progressDialog.setMessage("register user..");
        //progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<com.google.firebase.auth.AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<com.google.firebase.auth.AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(login.this, "register sucessful", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(login.this, "enter valid email or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
