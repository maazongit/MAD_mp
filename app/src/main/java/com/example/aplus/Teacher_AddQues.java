package com.example.aplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Teacher_AddQues extends AppCompatActivity {
Button btnclass2,btnclass3,btnclass4,btncllass5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_add_ques);
        btnclass2=findViewById(R.id.btnclass2);
        btnclass3=findViewById(R.id.btnclass3);
        btnclass4=findViewById(R.id.btnclass4);
        btncllass5=findViewById(R.id.btnclass5);

        btnclass2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Teacher_AddQues.this,Class_2_sub.class);
                startActivity(intent);
            }
        });
        btnclass3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Teacher_AddQues.this,Class_3_sub.class);
                startActivity(intent);
            }
        });
        btnclass4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Teacher_AddQues.this,Class_4_sub.class);
                startActivity(intent);
            }
        });
        btncllass5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Teacher_AddQues.this,Class_5_sub.class);
                startActivity(intent);
            }
        });
    }

}