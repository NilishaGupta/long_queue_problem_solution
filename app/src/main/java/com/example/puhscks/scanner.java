package com.example.puhscks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;

public class scanner extends AppCompatActivity {
    Button b1,b2;
FirebaseAuth firebaseAuth;
String name;
    TextView e1;

    int k=0;
    static  int total=0;
     ArrayList<String>  mStringList= new ArrayList<String>();
    ArrayList<String>  pStringList= new ArrayList<String>();
    ArrayList<String>  qStringList= new ArrayList<String>();
    String s,productname=null,price=null ,quantity=null;
    private DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
       Toolbar toolbar=findViewById(R.id.t1);
        e1=findViewById(R.id.el);
        firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser user=firebaseAuth.getCurrentUser();
        if(user.getDisplayName()!=null) {
            name = user.getDisplayName();
        }
        b1 = findViewById(R.id.b1);
        b2=findViewById(R.id.b2);

        final Activity activity = this;
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setPrompt("scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                //  integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(k==0){
                        Toast.makeText(activity, "cart is empty", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Intent i = new Intent(scanner.this, shopping_cart.class);
                        i.putExtra("value", mStringList);
                        i.putExtra("price", pStringList);
                        i.putExtra("qan", qStringList);
                        startActivity(i);

                    }

            }

        });
        e1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(scanner.this,signup.class));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, result.getContents(), Toast.LENGTH_SHORT).show();
                s = result.getContents();

                myRef= FirebaseDatabase.getInstance().getReference().child(s);

                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        productname=dataSnapshot.child("productname").getValue().toString();
                        price=dataSnapshot.child("price").getValue().toString();
                        quantity=dataSnapshot.child("quantity").getValue().toString();
                        mStringList.add(productname);
                        pStringList.add(price);
                        qStringList.add(quantity);
                        k=2;

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }
}