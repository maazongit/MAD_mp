package com.example.aplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Class_5_sub extends AppCompatActivity {

    private ImageButton t5eng,t5math,t5ss,t5sci;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class5_sub);
        t5sci=findViewById(R.id.t5sci);
        t5math=findViewById(R.id.t5math);
        t5eng=findViewById(R.id.t5eng);
        t5ss=findViewById(R.id.t5ss);

        t5sci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Class_5_sub.this,T_5_Sci.class);
                startActivity(intent);

            }
        });

        t5math.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Class_5_sub.this,T_5_Math.class);
                startActivity(intent);

            }
        });
        t5eng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Class_5_sub.this,T_5_Eng.class);
                startActivity(intent);

            }
        });
        t5ss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Class_5_sub.this,T_5_Ss.class);
                startActivity(intent);

            }
        });

    }
}