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

public class T_3_Eng extends AppCompatActivity {

    public static final String TABLE_10="Class3eng";
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase sqLiteDatabase;
    private Button t3engadd;
    private Cursor cursor;
    private TextView t3engtitle,t3engques,t3engop1,t3engop2,t3engop3,t3engcrtop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t3_eng);
        openHelper = new DatabaseHelper(this);
        t3engadd = findViewById(R.id.t3engaddque);
        t3engques = findViewById(R.id.t3engq);
        t3engtitle = findViewById(R.id.t3engtitle);
        t3engop1 = findViewById(R.id.t3engop1);
        t3engop2 = findViewById(R.id.t3engop2);
        t3engop3 = findViewById(R.id.t3engop3);
        t3engcrtop = findViewById(R.id.t3engcrtop);

        t3engadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqLiteDatabase = openHelper.getWritableDatabase();

                String t3engt = t3engtitle.getText().toString();
                String t3engq = t3engques.getText().toString();
                String t3engo1 = t3engop1.getText().toString();
                String t3engo2 = t3engop2.getText().toString();
                String t3engo3 = t3engop3.getText().toString();
                String t3engcop = t3engcrtop.getText().toString();
                String str="CREATE TABLE IF NOT EXISTS "+TABLE_10 +"(ID INTEGER PRIMARY KEY AUTOINCREMENT,Title TEXT,Question TEXT,Option1 TEXT,Option2 TEXT,Option3 TEXT,Correctans TEXT)";
                sqLiteDatabase.execSQL(str);
                if(!validateempty() |!validateans() )
                {
                    return;
                }
                else {
                    insertData(t3engt, t3engq, t3engo1, t3engo2, t3engo3, t3engcop);
                    Toast.makeText(T_3_Eng.this, "Question Added Successful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private Boolean validateempty() {
        String t3engt = t3engtitle.getText().toString();
        String t3engq = t3engques.getText().toString();
        String t3engo1 = t3engop1.getText().toString();
        String t3engo2 = t3engop2.getText().toString();
        String t3engo3 = t3engop3.getText().toString();
        String t3engcop = t3engcrtop.getText().toString();
        if (t3engt.isEmpty() || t3engq.isEmpty()|| t3engo1.isEmpty()|| t3engo2.isEmpty()|| t3engo3.isEmpty()|| t3engcop.isEmpty()) {
            Toast.makeText(T_3_Eng.this, "Please fill all the details", Toast.LENGTH_LONG).show();
            return false;
        }
        else {
            return true;
        }
    }

    private  Boolean validateans()
    {
        String t3engo1 = t3engop1.getText().toString();
        String t3engo2 = t3engop2.getText().toString();
        String t3engo3 = t3engop3.getText().toString();
        String t3engcop = t3engcrtop.getText().toString();

        if (!(t3engcop.equals(t3engo1)||t3engcop.equals(t3engo2)||t3engcop.equals(t3engo3)))
        {
            t3engcrtop.setError("Answer does not match the given options");
            return false;
        }
        else
        {
            t3engcrtop.setError(null);
            //regPassword.setErrorEnabled(false);
            return true;
        }
    }
    public void insertData (String t3engt, String t3engq, String t3engo1, String t3engo2, String t3engo3, String t3engcop){
        ContentValues values10 = new ContentValues();

        values10.put("Title", t3engt);
        values10.put("Question", t3engq);
        values10.put("Option1", t3engo1);
        values10.put("Option2", t3engo2);
        values10.put("Option3", t3engo3);
        values10.put("Correctans", t3engcop);


        sqLiteDatabase.insert(TABLE_10, null, values10);

        t3engtitle.setText("");
        t3engques.setText("");
        t3engop1.setText("");
        t3engop2.setText("");
        t3engop3.setText("");
        t3engcrtop.setText("");
    }
}