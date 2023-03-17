package com.example.aplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Class_3_sub extends AppCompatActivity {
    private ImageButton t3eng,t3math,t3ss,t3sci;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class3_sub);
        t3sci=findViewById(R.id.t3sci);
        t3math=findViewById(R.id.t3math);
        t3eng=findViewById(R.id.t3eng);
        t3ss=findViewById(R.id.t3ss);
        t3sci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Class_3_sub.this,T_3_Sci.class);
                startActivity(intent);

            }
        });

        t3math.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Class_3_sub.this,T_3_Math.class);
                startActivity(intent);

            }
        });
        t3eng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Class_3_sub.this,T_3_Eng.class);
                startActivity(intent);

            }
        });
        t3ss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Class_3_sub.this,T_3_Ss.class);
                startActivity(intent);

            }
        });
    }
}