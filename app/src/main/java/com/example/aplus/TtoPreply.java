package com.example.aplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TtoPreply extends AppCompatActivity {
    public static final String TABLE_22=" TtoPReply";
    private SQLiteDatabase sqLiteDatabase;
    private SQLiteOpenHelper openHelper;
    TextView title, txtque,uteacher;
    EditText response;
    Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tto_preply);

        openHelper = new DatabaseHelper(this);
        sqLiteDatabase = openHelper.getReadableDatabase();
        title=findViewById(R.id.UnameParent);
        txtque=findViewById(R.id.Question);
        response=findViewById(R.id.response);
        submit=findViewById(R.id.submit);
        uteacher=findViewById(R.id.uteacher);
        String name=getIntent().getStringExtra("name");
        String UnameParent=getIntent().getStringExtra("UnameParent");
        String Question=getIntent().getStringExtra("Question");
        title.setText(UnameParent);
        txtque.setText(Question);
        uteacher.setText(name);
       submit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
String utech=uteacher.getText().toString();
               String ques=txtque.getText().toString();
               String unameparent=title.getText().toString();
               String reply=response.getText().toString();
               String str="CREATE TABLE IF NOT EXISTS "+TABLE_22 +"(ID INTEGER PRIMARY KEY AUTOINCREMENT,UnameParent TEXT,Question TEXT,UnameTeacher TEXT,Reply TEXT)";
               sqLiteDatabase.execSQL(str);
               insertData(unameparent,ques,utech,reply);
               Toast.makeText(TtoPreply.this, "Response Added Successful", Toast.LENGTH_SHORT).show();

           }
       });





    }
    public void insertData (String unameparent, String ques, String utech, String reply) {
        ContentValues values22 = new ContentValues();

        values22.put("UnameParent", unameparent);
        values22.put("Question", ques);
        values22.put("UnameTeacher", utech);
        values22.put("Reply", reply);



        sqLiteDatabase.insert(TABLE_22, null, values22);
    }
}