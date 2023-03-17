package com.example.aplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Class_2_sub extends AppCompatActivity {
    private ImageButton t2eng,t2math,t2ss,t2sci;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class2_sub);
        t2sci=findViewById(R.id.t2sci);
        t2math=findViewById(R.id.t2math);
        t2eng=findViewById(R.id.t2eng);
        t2ss=findViewById(R.id.t2ss);

        t2sci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Class_2_sub.this,T_2_Sci.class);
                startActivity(intent);

            }
        });

        t2math.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Class_2_sub.this,T_2_Math.class);
                startActivity(intent);

            }
        });
        t2eng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Class_2_sub.this,T_2_Eng.class);
                startActivity(intent);

            }
        });
        t2ss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Class_2_sub.this,T_2_Ss.class);
                startActivity(intent);

            }
        });

    }


}