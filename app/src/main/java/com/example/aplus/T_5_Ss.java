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

public class T_5_Ss extends AppCompatActivity {
    public static final String TABLE_19 = "Class5ss";
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase sqLiteDatabase;
    private Button t5ssadd;
    private Cursor cursor;
    private TextView t5sstitle, t5ssques, t5ssop1, t5ssop2, t5ssop3, t5sscrtop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t5_ss);
        openHelper = new DatabaseHelper(this);
        t5ssadd = findViewById(R.id.t5ssaddque);
        t5ssques = findViewById(R.id.t5ssq);
        t5sstitle = findViewById(R.id.t5sstitle);
        t5ssop1 = findViewById(R.id.t5ssop1);
        t5ssop2 = findViewById(R.id.t5ssop2);
        t5ssop3 = findViewById(R.id.t5ssop3);
        t5sscrtop = findViewById(R.id.t5sscrtop);

        t5ssadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqLiteDatabase = openHelper.getWritableDatabase();

                String t5sst = t5sstitle.getText().toString();
                String t5ssq = t5ssques.getText().toString();
                String t5sso1 = t5ssop1.getText().toString();
                String t5sso2 = t5ssop2.getText().toString();
                String t5sso3 = t5ssop3.getText().toString();
                String t5sscop = t5sscrtop.getText().toString();
                String str = "CREATE TABLE IF NOT EXISTS " + TABLE_19 + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,Title TEXT,Question TEXT,Option1 TEXT,Option2 TEXT,Option3 TEXT,Correctans TEXT)";
                sqLiteDatabase.execSQL(str);
                if(!validateempty() |!validateans() )
                {
                    return;
                }
                else {
                    insertData(t5sst, t5ssq, t5sso1, t5sso2, t5sso3, t5sscop);
                    Toast.makeText(T_5_Ss.this, "Question Added Successful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private Boolean validateempty() {
        String t5sst = t5sstitle.getText().toString();
        String t5ssq = t5ssques.getText().toString();
        String t5sso1 = t5ssop1.getText().toString();
        String t5sso2 = t5ssop2.getText().toString();
        String t5sso3 = t5ssop3.getText().toString();
        String t5sscop = t5sscrtop.getText().toString();
        if (t5sst.isEmpty() || t5ssq.isEmpty()|| t5sso1.isEmpty()|| t5sso2.isEmpty()|| t5sso3.isEmpty()|| t5sscop.isEmpty()) {
            Toast.makeText(T_5_Ss.this, "Please fill all the details", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            return true;
        }
    }

    private  Boolean validateans()
    {
        String t5sso1 = t5ssop1.getText().toString();
        String t5sso2 = t5ssop2.getText().toString();
        String t5sso3 = t5ssop3.getText().toString();
        String t5sscop = t5sscrtop.getText().toString();
        if (!(t5sscop.equals(t5sso1)||t5sscop.equals(t5sso2)||t5sscop.equals(t5sso3)))
        {
            t5sscrtop.setError("Answer does not match the given options");
            return false;
        }
        else
        {
            t5sscrtop.setError(null);
            //regPassword.setErrorEnabled(false);
            return true;
        }
    }
    public void insertData (String t5sst, String t5ssq, String t5sso1, String t5sso2, String t5sso3, String t5sscop){
        ContentValues values19 = new ContentValues();

        values19.put("Title", t5sst);
        values19.put("Question", t5ssq);
        values19.put("Option1", t5sso1);
        values19.put("Option2", t5sso2);
        values19.put("Option3", t5sso3);
        values19.put("Correctans", t5sscop);


        sqLiteDatabase.insert(TABLE_19, null, values19);
        t5sstitle.setText("");
        t5ssques.setText("");
        t5ssop1.setText("");
        t5ssop2.setText("");
        t5ssop3.setText("");
        t5sscrtop.setText("");
    }
}