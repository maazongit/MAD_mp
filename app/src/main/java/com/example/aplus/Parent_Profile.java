package com.example.aplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Parent_Profile extends AppCompatActivity {
    private Cursor cursor;
    private SQLiteDatabase sqLiteDatabase;
    private SQLiteOpenHelper openHelper;
    private TextView textView, textView1, textView2, textView3, textView4, usernamet;
    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_profile);
        textView = findViewById(R.id.parentuser);
        textView1 = findViewById(R.id.parentuserl);
        textView2 = findViewById(R.id.parentuserp);
        textView3 = findViewById(R.id.parentusere);
        textView4 = findViewById(R.id.parentuserc);
        usernamet = findViewById(R.id.unameprofs);
        logout=findViewById(R.id.button6);
        openHelper = new DatabaseHelper(this);
        sqLiteDatabase = openHelper.getReadableDatabase();

        Intent intent = getIntent();
        String name = intent.getStringExtra("message_key1");
        usernamet.setText(name);

        Cursor abc = sqLiteDatabase.rawQuery("Select * from parent where Username='" + usernamet.getText().toString() + "'", null);

        //while(cursor.moveToNext())
        if (abc.moveToFirst()) {
            textView.setText("First Name : " + abc.getString(1));
            textView1.setText("Last Name : " + abc.getString(2));
            textView4.setText("Child's Name : " + abc.getString(3));
            textView2.setText("Contact: " + abc.getString(4));
            textView3.setText("Email: " + abc.getString(5));
        }
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }
}