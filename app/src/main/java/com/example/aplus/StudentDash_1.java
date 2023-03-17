package com.example.aplus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

public class StudentDash_1 extends AppCompatActivity {
    private TextView textView;

   Student_home student_home=new Student_home();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dash1);
        textView=findViewById(R.id.stu_textView4);
        Intent intent=getIntent();
        String username=intent.getStringExtra("message_key1");
        textView.setText(username);
        getSupportFragmentManager().beginTransaction().replace(R.id.stu_frame_layout,student_home).commit();
        BottomNavigationView bottomNavigationView=(BottomNavigationView) findViewById(R.id.bottom_navigation_stu);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.page_5_stu:
                        Intent intent= new Intent(StudentDash_1.this,Student_Profile.class);
                        intent.putExtra("message_key1",username);
                        startActivity(intent);


                        return true;
                    case R.id.page_1_stu:
                        getSupportFragmentManager().beginTransaction().replace(R.id.stu_frame_layout,student_home).commit();
                        return true;

                    case R.id.page_2_stu:
                        Intent intent1= new Intent(StudentDash_1.this,Student_Concern.class);
                        intent1.putExtra("message_key1",username);
                        startActivity(intent1);
                        return true;

                    case R.id.page_4_stu:
                        Intent intent2= new Intent(StudentDash_1.this,Student_Progress.class);
                        intent2.putExtra("message_key1",username);
                        startActivity(intent2);
                        return true;

                }

                return false;
            }



        });

        FloatingActionButton floatingActionButton= findViewById(R.id.floatadd_stu);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(StudentDash_1.this,Student_Quiz_sub.class);
                intent.putExtra("message_key1",username);
                startActivity(intent);
            }
        });

    }
}