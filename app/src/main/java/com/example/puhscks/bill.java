package com.example.puhscks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import static com.example.puhscks.mall.mallname;
import static com.example.puhscks.scanner.total;

public class bill extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<list_Item> listItems;
    String t;
    Button b1;
    Button b2;
    FirebaseAuth firebaseAuth;
    TextView e1,tv1,tv2,tv3;
    int tt=0;
    ArrayList<String>  pStringList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        recyclerView=findViewById(R.id.rc1);
        firebaseAuth=FirebaseAuth.getInstance();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final Intent intent=getIntent();

        ArrayList<String>  mStringList= intent.getExtras().getStringArrayList("value");
        Object[] mStringArray = mStringList.toArray();
        pStringList= intent.getExtras().getStringArrayList("price");
        Object[] pStringArray = pStringList.toArray();
        ArrayList<String>  qStringList= intent.getExtras().getStringArrayList("qan");
        Object[] qStringArray = qStringList.toArray();
        listItems=new ArrayList<>();
        /*for (int i=0;i<=10;i++){
            list_Item listItem=new list_Item("heading"+(i+1),
                    "hello");
            listItems.add(listItem);

        }*/
        for(int i = 0; i < mStringArray.length ; i++){
            // Log.d("string is",(String)mStringArray[i]);
            list_Item listItem=new list_Item((String)mStringArray[i], (String) pStringArray[i], (String) qStringArray[i]);
            listItems.add(listItem);
        }
        adapter=new myAdapter(listItems,this);
        recyclerView.setAdapter(adapter);
        b1=findViewById(R.id.b1);

        e1=findViewById(R.id.el);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(bill.this, mall.class);
                startActivity(intent);
            }

        });

        e1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(bill.this,signup.class));
            }
        });




    }

    @Override
    protected void onStart() {
        super.onStart();
        tv1=findViewById(R.id.tv1);
        tv2=findViewById(R.id.tv2);
        tv3=findViewById(R.id.tv_t);
        tv1.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
        Object[] pStringArray = pStringList.toArray();
        for (int i = 0; i < pStringArray.length; i++) {
            tt=tt+ Integer.parseInt(String.valueOf(pStringArray[i]));
        }
        tv2.setText(mallname);
        tv3.setText(String.valueOf(tt));
    }
}
