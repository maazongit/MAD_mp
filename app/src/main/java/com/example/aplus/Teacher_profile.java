package com.example.aplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Teacher_profile extends AppCompatActivity {
    private Cursor cursor;
    private SQLiteDatabase sqLiteDatabase;
    private SQLiteOpenHelper openHelper;
    private TextView textView,textView1,textView2,textView3,usernamet;
    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_profile);
        textView=findViewById(R.id.teacheruser);
        textView1=findViewById(R.id.teacheruserl);
        textView2=findViewById(R.id.teacheruserp);
        textView3=findViewById(R.id.teacherusere);
        usernamet=findViewById(R.id.unameprof);
        logout=findViewById(R.id.button7);
        openHelper = new DatabaseHelper(this);
        sqLiteDatabase = openHelper.getReadableDatabase();

        Intent intent=getIntent();
        String name=intent.getStringExtra("message_key1");
        usernamet.setText(name);

       // String username1=usernamet.getText().toString();
        //cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_3 + " WHERE UserName='"+usernamet.getText().toString()+"'");
Cursor abc=sqLiteDatabase.rawQuery("Select * from teacher where Username='"+usernamet.getText().toString()+"'",null);

        //while(cursor.moveToNext())
        if(abc.moveToFirst())
        {
            textView.setText("First Name : " + abc.getString(1));
            textView1.setText("Last Name : " + abc.getString(2));

            textView2.setText("Contact: " + abc.getString(5));
            textView3.setText("Email: " + abc.getString(6));




        }
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }


}