package com.example.aplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class T_5_Eng extends AppCompatActivity {
    public static final String TABLE_16="Class5eng";
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase sqLiteDatabase;
    private Button t5engadd;
    private Cursor cursor;
    private TextView t5engtitle,t5engques,t5engop1,t5engop2,t5engop3,t5engcrtop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t5_eng);
        openHelper = new DatabaseHelper(this);
        t5engadd = findViewById(R.id.t5engaddque);
        t5engques = findViewById(R.id.t5engq);
        t5engtitle = findViewById(R.id.t5engtitle);
        t5engop1 = findViewById(R.id.t5engop1);
        t5engop2 = findViewById(R.id.t5engop2);
        t5engop3 = findViewById(R.id.t5engop3);
        t5engcrtop = findViewById(R.id.t5engcrtop);

        t5engadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqLiteDatabase = openHelper.getWritableDatabase();

                String t5engt = t5engtitle.getText().toString();
                String t5engq = t5engques.getText().toString();
                String t5engo1 = t5engop1.getText().toString();
                String t5engo2 = t5engop2.getText().toString();
                String t5engo3 = t5engop3.getText().toString();
                String t5engcop = t5engcrtop.getText().toString();
                String str="CREATE TABLE IF NOT EXISTS "+TABLE_16 +"(ID INTEGER PRIMARY KEY AUTOINCREMENT,Title TEXT,Question TEXT,Option1 TEXT,Option2 TEXT,Option3 TEXT,Correctans TEXT)";
                sqLiteDatabase.execSQL(str);
                if(!validateempty() |!validateans() )
                {
                    return;
                }
                else {
                    insertData(t5engt, t5engq, t5engo1, t5engo2, t5engo3, t5engcop);
                    Toast.makeText(T_5_Eng.this, "Question Added Successful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private Boolean validateempty() {
        String t5engt = t5engtitle.getText().toString();
        String t5engq = t5engques.getText().toString();
        String t5engo1 = t5engop1.getText().toString();
        String t5engo2 = t5engop2.getText().toString();
        String t5engo3 = t5engop3.getText().toString();
        String t5engcop = t5engcrtop.getText().toString();
        if (t5engt.isEmpty() || t5engq.isEmpty()|| t5engo1.isEmpty()|| t5engo2.isEmpty()|| t5engo3.isEmpty()|| t5engcop.isEmpty()) {
            Toast.makeText(T_5_Eng.this, "Please fill all the details", Toast.LENGTH_LONG).show();
            return false;
        }
        else {
            return true;
        }
    }

    private  Boolean validateans()
    {
        String t5engo1 = t5engop1.getText().toString();
        String t5engo2 = t5engop2.getText().toString();
        String t5engo3 = t5engop3.getText().toString();
        String t5engcop = t5engcrtop.getText().toString();

        if (!(t5engcop.equals(t5engo1)||t5engcop.equals(t5engo2)||t5engcop.equals(t5engo3)))
        {
            t5engcrtop.setError("Answer does not match the given options");
            return false;
        }
        else
        {
            t5engcrtop.setError(null);
            //regPassword.setErrorEnabled(false);
            return true;
        }
    }
    public void insertData (String t5engt, String t5engq, String t5engo1, String t5engo2, String t5engo3, String t5engcop) {
        ContentValues values12 = new ContentValues();

        values12.put("Title", t5engt);
        values12.put("Question", t5engq);
        values12.put("Option1", t5engo1);
        values12.put("Option2", t5engo2);
        values12.put("Option3", t5engo3);
        values12.put("Correctans", t5engcop);


        sqLiteDatabase.insert(TABLE_16, null, values12);

        t5engtitle.setText("");
        t5engques.setText("");
        t5engop1.setText("");
        t5engop2.setText("");
        t5engop3.setText("");
        t5engcrtop.setText("");
    }
}