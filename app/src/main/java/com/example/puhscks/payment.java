package com.example.puhscks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class payment extends AppCompatActivity {
Button b1,b2,b3,b4;
FirebaseAuth firebaseAuth;
TextView e1;
    ArrayList<String> pStringList;
    ArrayList<String>  mStringList;
    ArrayList<String>  qStringList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        b1=findViewById(R.id.b1) ;
        b2=findViewById(R.id.b2) ;
        b3=findViewById(R.id.b3) ;
        e1=findViewById(R.id.el);
        final Intent intent=getIntent();

        mStringList= intent.getExtras().getStringArrayList("value");
        Object[] mStringArray = mStringList.toArray();
        pStringList= intent.getExtras().getStringArrayList("price");
        Object[] pStringArray = pStringList.toArray();
        qStringList= intent.getExtras().getStringArrayList("qan");
        Object[] qStringArray = qStringList.toArray();
        firebaseAuth=FirebaseAuth.getInstance();
        b4=findViewById(R.id.b4) ;
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(payment.this,cod.class);

                startActivity(intent);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(payment.this,p_status.class);
                startActivity(intent);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(payment.this,credit_debit_card.class);
                intent.putExtra("value", mStringList);
                intent.putExtra("price", pStringList);
                intent.putExtra("qan", qStringList);
                startActivity(intent);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(payment.this,p_status.class);
                startActivity(intent);
            }
        });
        e1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(payment.this,signup.class));
            }
        });
    }
}
