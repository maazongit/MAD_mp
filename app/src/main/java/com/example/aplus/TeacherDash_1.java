package com.example.aplus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

public class TeacherDash_1 extends AppCompatActivity {
    private TextView textView;

    Teacher_home teacher_home=new Teacher_home();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_dash1);
        textView=findViewById(R.id.textView4);
        Intent intent=getIntent();
        String name=intent.getStringExtra("message_key1");
        textView.setText(name);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,teacher_home).commit();
BottomNavigationView bottomNavigationView=(BottomNavigationView) findViewById(R.id.bottom_navigation);
bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.page_5:
                Intent intent= new Intent(TeacherDash_1.this,Teacher_profile.class);
                intent.putExtra("message_key1",name);
                startActivity(intent);
                return true;
            case R.id.page_1:
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,teacher_home).commit();
                return true;

            case R.id.page_2:
                Intent intent1= new Intent(TeacherDash_1.this,Teacher_Concern.class);
                intent1.putExtra("message_key1",name);
                startActivity(intent1);
return true;
            case R.id.page_4:
                Intent intent2= new Intent(TeacherDash_1.this,Teacher_Progress.class);
                intent2.putExtra("message_key1",name);
                startActivity(intent2);
                return true;

        }

        return false;
    }



});

        FloatingActionButton floatingActionButton= findViewById(R.id.floatadd);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(TeacherDash_1.this,Teacher_AddQues.class);
                startActivity(intent);
            }
        });

    }
}
