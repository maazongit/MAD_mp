package com.example.aplus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class ParentDash_1 extends AppCompatActivity {
    private TextView textView;

    Parent_home parent_home=new Parent_home();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_dash1);
        textView=findViewById(R.id.parent_textView4);
        Intent intent=getIntent();
        String username=intent.getStringExtra("message_key1");
        textView.setText(username);
        getSupportFragmentManager().beginTransaction().replace(R.id.parent_frame_layout,parent_home).commit();
        BottomNavigationView bottomNavigationView=(BottomNavigationView) findViewById(R.id.bottom_navigation_parent);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.page_5_parent:
                        Intent intent= new Intent(ParentDash_1.this,Parent_Profile.class);
                        intent.putExtra("message_key1",username);
                        startActivity(intent);
                        return true;
                    case R.id.page_1_parent:
                        getSupportFragmentManager().beginTransaction().replace(R.id.parent_frame_layout,parent_home).commit();
                        return true;

                    case R.id.page_2_parent:
                        Intent intent1= new Intent(ParentDash_1.this,Parent_Concern.class);
                        intent1.putExtra("message_key1",username);
                        startActivity(intent1);
                        return true;

                    case R.id.page_4_parent:
                        Intent intent2= new Intent(ParentDash_1.this,Parent_Progress.class);
                        intent2.putExtra("message_key1",username);
                        startActivity(intent2);
                        return true;


                }

                return false;
            }



        });


    }
}