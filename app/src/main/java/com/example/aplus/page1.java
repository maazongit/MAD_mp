package com.example.aplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class page1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page1);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ImageView parent= findViewById(R.id.parent);
        ImageView teacher= findViewById(R.id.teacher);
        ImageView student= findViewById(R.id.student);
        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(page1.this,RegisterParent.class);
                startActivity(intent);
            }
        });

        teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(page1.this,RegisterTeacher.class);
                startActivity(intent);
            }

        });

        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(page1.this,RegisterStudent.class);
                startActivity(intent);
            }

        });
    }

}