package com.example.aplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Class_4_sub extends AppCompatActivity {
    private ImageButton t4eng,t4math,t4ss,t4sci;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class4_sub);
        t4sci=findViewById(R.id.t4sci);
        t4math=findViewById(R.id.t4math);
        t4eng=findViewById(R.id.t4eng);
        t4ss=findViewById(R.id.t4ss);

        t4sci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Class_4_sub.this,T_4_Sci.class);
                startActivity(intent);

            }
        });

        t4math.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Class_4_sub.this,T_4_Math.class);
                startActivity(intent);

            }
        });
        t4eng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Class_4_sub.this,T_4_Eng.class);
                startActivity(intent);

            }
        });
        t4ss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Class_4_sub.this, T_4_Ss.class);
                startActivity(intent);

            }
        });
    }
}