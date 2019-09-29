package com.example.puhscks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.String.*;

import com.google.firebase.auth.FirebaseAuth;

public class mall extends AppCompatActivity {
    RadioGroup radio;
    RadioButton b1;
    TextView e1;
    Button bt;
    int j=0;
    static String mallname=null;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall);
        radio = findViewById(R.id.radio);
        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                b1=findViewById(checkedId);
                mallname=b1.getText().toString();
                j=2;

            }
        });
        bt=findViewById(R.id.bt);
        e1=findViewById(R.id.el);
        firebaseAuth=FirebaseAuth.getInstance();
        e1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(mall.this,signup.class));
            }
        });
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (j==0) {
                    Toast.makeText(mall.this, " please select mall ", Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(new Intent(mall.this, scanner.class));
                }
            }
        });

    }

}
