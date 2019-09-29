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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signup extends AppCompatActivity implements View.OnClickListener{
    private Button b1,b2;
    EditText e1,e2;

    ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        b2=findViewById(R.id.b2);
        b1=findViewById(R.id.b1);
        e1=findViewById(R.id.e1);
        e2=findViewById(R.id.e2);
        firebaseAuth=FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(getApplicationContext(),mall.class));
        }
        progressDialog =new ProgressDialog(this);
        b1.setOnClickListener(signup.this);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 startActivity(new Intent(signup.this,login.class));
            }
            });

    }
    @Override
    public void onClick(View view) {
        if(view==b1){
            userlogin();
        }
    }

    private void userlogin() {

        String email=e1.getText().toString();
        String password=e2.getText().toString();
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

        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if(task.isSuccessful()){
                    finish();
                    startActivity(new Intent(getApplicationContext(),mall.class));
                }
            }
        });
    }
}
