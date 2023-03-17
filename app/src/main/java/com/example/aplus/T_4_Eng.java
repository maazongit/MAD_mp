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

public class T_4_Eng extends AppCompatActivity {
    public static final String TABLE_12="Class4eng";
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase sqLiteDatabase;
    private Button t4engadd;
    private Cursor cursor;
    private TextView t4engtitle,t4engques,t4engop1,t4engop2,t4engop3,t4engcrtop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t4_eng);
        openHelper = new DatabaseHelper(this);
        t4engadd = findViewById(R.id.t4engaddque);
        t4engques = findViewById(R.id.t4engq);
        t4engtitle = findViewById(R.id.t4engtitle);
        t4engop1 = findViewById(R.id.t4engop1);
        t4engop2 = findViewById(R.id.t4engop2);
        t4engop3 = findViewById(R.id.t4engop3);
        t4engcrtop = findViewById(R.id.t4engcrtop);
        t4engadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqLiteDatabase = openHelper.getWritableDatabase();

                String t4engt = t4engtitle.getText().toString();
                String t4engq = t4engques.getText().toString();
                String t4engo1 = t4engop1.getText().toString();
                String t4engo2 = t4engop2.getText().toString();
                String t4engo3 = t4engop3.getText().toString();
                String t4engcop = t4engcrtop.getText().toString();
                String str="CREATE TABLE IF NOT EXISTS "+TABLE_12 +"(ID INTEGER PRIMARY KEY AUTOINCREMENT,Title TEXT,Question TEXT,Option1 TEXT,Option2 TEXT,Option3 TEXT,Correctans TEXT)";
                sqLiteDatabase.execSQL(str);
                if(!validateempty() |!validateans() )
                {
                    return;
                }
                else {
                    insertData(t4engt, t4engq, t4engo1, t4engo2, t4engo3, t4engcop);
                    Toast.makeText(T_4_Eng.this, "Question Added Successful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private Boolean validateempty() {
        String t4engt = t4engtitle.getText().toString();
        String t4engq = t4engques.getText().toString();
        String t4engo1 = t4engop1.getText().toString();
        String t4engo2 = t4engop2.getText().toString();
        String t4engo3 = t4engop3.getText().toString();
        String t4engcop = t4engcrtop.getText().toString();
        if (t4engt.isEmpty() || t4engq.isEmpty()|| t4engo1.isEmpty()|| t4engo2.isEmpty()|| t4engo3.isEmpty()|| t4engcop.isEmpty()) {
            Toast.makeText(T_4_Eng.this, "Please fill all the details", Toast.LENGTH_LONG).show();
            return false;
        }
        else {
            return true;
        }
    }

    private  Boolean validateans()
    {
        String t4engo1 = t4engop1.getText().toString();
        String t4engo2 = t4engop2.getText().toString();
        String t4engo3 = t4engop3.getText().toString();
        String t4engcop = t4engcrtop.getText().toString();

        if (!(t4engcop.equals(t4engo1)||t4engcop.equals(t4engo2)||t4engcop.equals(t4engo3)))
        {
            t4engcrtop.setError("Answer does not match the given options");
            return false;
        }
        else
        {
            t4engcrtop.setError(null);
            //regPassword.setErrorEnabled(false);
            return true;
        }
    }
    public void insertData (String t4engt, String t4engq, String t4engo1, String t4engo2, String t4engo3, String t4engcop) {
        ContentValues values12 = new ContentValues();

        values12.put("Title", t4engt);
        values12.put("Question", t4engq);
        values12.put("Option1", t4engo1);
        values12.put("Option2", t4engo2);
        values12.put("Option3", t4engo3);
        values12.put("Correctans", t4engcop);


        sqLiteDatabase.insert(TABLE_12, null, values12);

        t4engtitle.setText("");
        t4engques.setText("");
        t4engop1.setText("");
        t4engop2.setText("");
        t4engop3.setText("");
        t4engcrtop.setText("");
    }
}