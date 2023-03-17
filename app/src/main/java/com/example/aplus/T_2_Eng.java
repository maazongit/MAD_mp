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

public class T_2_Eng extends AppCompatActivity {
    public static final String TABLE_6="Class2eng";
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase sqLiteDatabase;
    private Button t2engadd;
    private Cursor cursor;
    private TextView t2engtitle,t2engques,t2engop1,t2engop2,t2engop3,t2engcrtop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t2_eng);
        openHelper = new DatabaseHelper(this);
        t2engadd = findViewById(R.id.t2engaddque);
        t2engques = findViewById(R.id.t2engq);
        t2engtitle = findViewById(R.id.t2engtitle);
        t2engop1 = findViewById(R.id.t2engop1);
        t2engop2 = findViewById(R.id.t2engop2);
        t2engop3 = findViewById(R.id.t2engop3);
        t2engcrtop = findViewById(R.id.t2engcrtop);

        t2engadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqLiteDatabase = openHelper.getWritableDatabase();

                String t2engt = t2engtitle.getText().toString();
                String t2engq = t2engques.getText().toString();
                String t2engo1 = t2engop1.getText().toString();
                String t2engo2 = t2engop2.getText().toString();
                String t2engo3 = t2engop3.getText().toString();
                String t2engcop = t2engcrtop.getText().toString();
                String str="CREATE TABLE IF NOT EXISTS "+TABLE_6 +"(ID INTEGER PRIMARY KEY AUTOINCREMENT,Title TEXT,Question TEXT,Option1 TEXT,Option2 TEXT,Option3 TEXT,Correctans TEXT)";
                sqLiteDatabase.execSQL(str);
                if(!validateempty() |!validateans() )
                {
                    return;
                }
                else {
                    insertData(t2engt, t2engq, t2engo1, t2engo2, t2engo3, t2engcop);
                    Toast.makeText(T_2_Eng.this, "Question Added Successful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private Boolean validateempty() {
        String t2engt = t2engtitle.getText().toString();
        String t2engq = t2engques.getText().toString();
        String t2engo1 = t2engop1.getText().toString();
        String t2engo2 = t2engop2.getText().toString();
        String t2engo3 = t2engop3.getText().toString();
        String t2engcop = t2engcrtop.getText().toString();
        if (t2engt.isEmpty() || t2engq.isEmpty()|| t2engo1.isEmpty()|| t2engo2.isEmpty()|| t2engo3.isEmpty()|| t2engcop.isEmpty()) {
            Toast.makeText(T_2_Eng.this, "Please fill all the details", Toast.LENGTH_LONG).show();
            return false;
        }
        else {
            return true;
        }
    }

    private  Boolean validateans()
    {
        String t2engo1 = t2engop1.getText().toString();
        String t2engo2 = t2engop2.getText().toString();
        String t2engo3 = t2engop3.getText().toString();
        String t2engcop = t2engcrtop.getText().toString();

        if (!(t2engcop.equals(t2engo1)||t2engcop.equals(t2engo2)||t2engcop.equals(t2engo3)))
        {
            t2engcrtop.setError("Answer does not match the given options");
            return false;
        }
        else
        {
            t2engcrtop.setError(null);
            //regPassword.setErrorEnabled(false);
            return true;
        }
    }
    public void insertData (String t2engt, String t2engq, String t2engo1, String t2engo2, String t2engo3, String t2engcop){
        ContentValues values6 = new ContentValues();

        values6.put("Title", t2engt);
        values6.put("Question", t2engq);
        values6.put("Option1", t2engo1);
        values6.put("Option2", t2engo2);
        values6.put("Option3", t2engo3);
        values6.put("Correctans", t2engcop);


        sqLiteDatabase.insert(TABLE_6, null, values6);

        t2engtitle.setText("");
        t2engques.setText("");
        t2engop1.setText("");
        t2engop2.setText("");
        t2engop3.setText("");
        t2engcrtop.setText("");
    }
}