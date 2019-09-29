package com.example.puhscks;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class credit_debit_card extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
Spinner sp1,sp2;
String m,y,no,cvv;
EditText e1,e2;
    ArrayList<String> pStringList;
    ArrayList<String>  mStringList;
    ArrayList<String>  qStringList;
Button b1;
int i=0;
    String[] month = { "jan","feb","mar","apr","may","jun","jul","aug","sep","oct","nov","dec"};
    String[] year={"2019","2020","2021","2022","2023","2024","2025","2026","2027","2028","2029","2030"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_debit_card);
        e1=findViewById(R.id.et1);
        e2=findViewById(R.id.et2);
        b1=findViewById(R.id.b1);
        final Intent intent=getIntent();
        mStringList= intent.getExtras().getStringArrayList("value");
        Object[] mStringArray = mStringList.toArray();
        pStringList= intent.getExtras().getStringArrayList("price");
        Object[] pStringArray = pStringList.toArray();
        qStringList= intent.getExtras().getStringArrayList("qan");
        Object[] qStringArray = qStringList.toArray();
        sp1 = (Spinner) findViewById(R.id.sp1);
        sp1.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        ArrayAdapter<String> c = new ArrayAdapter <String> (this,android.R.layout.simple_spinner_item,month);
        c.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp1.setAdapter(c);
        sp2 = (Spinner) findViewById(R.id.sp2);
        sp2.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        ArrayAdapter<String> c1 = new ArrayAdapter <String> (this,android.R.layout.simple_spinner_item,year);
        c.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp2.setAdapter(c1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                no=e1.getText().toString();
                cvv=e2.getText().toString();
                if(no.length()<16){
                    e1.setError("enter 16 digit number");
                    i=2;
                }
               else if(no.length()<3){
                    e2.setError("enter 3 digit number");
                    i=2;
                }
                else {
                    i=0;
                }
                if (i==0){
                    Intent intent=new Intent(credit_debit_card.this,bill.class);
                    intent.putExtra("value", mStringList);
                    intent.putExtra("price", pStringList);
                    intent.putExtra("qan", qStringList);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    m=sp1.getSelectedItem().toString();
    y=sp2.getSelectedItem().toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
