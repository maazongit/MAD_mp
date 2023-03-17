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

public class T_3_Ss extends AppCompatActivity {
    public static final String TABLE_11="Class3ss";
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase sqLiteDatabase;
    private Button t3ssadd;
    private Cursor cursor;
    private TextView t3sstitle,t3ssques,t3ssop1,t3ssop2,t3ssop3,t3sscrtop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t3_ss);
        openHelper = new DatabaseHelper(this);
        t3ssadd = findViewById(R.id.t3ssaddque);
        t3ssques = findViewById(R.id.t3ssq);
        t3sstitle = findViewById(R.id.t3sstitle);
        t3ssop1 = findViewById(R.id.t3ssop1);
        t3ssop2 = findViewById(R.id.t3ssop2);
        t3ssop3 = findViewById(R.id.t3ssop3);
        t3sscrtop = findViewById(R.id.t3sscrtop);

        t3ssadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqLiteDatabase = openHelper.getWritableDatabase();

                String t3sst = t3sstitle.getText().toString();
                String t3ssq = t3ssques.getText().toString();
                String t3sso1 = t3ssop1.getText().toString();
                String t3sso2 = t3ssop2.getText().toString();
                String t3sso3 = t3ssop3.getText().toString();
                String t3sscop = t3sscrtop.getText().toString();
                String str="CREATE TABLE IF NOT EXISTS "+TABLE_11 +"(ID INTEGER PRIMARY KEY AUTOINCREMENT,Title TEXT,Question TEXT,Option1 TEXT,Option2 TEXT,Option3 TEXT,Correctans TEXT)";
                sqLiteDatabase.execSQL(str);
                if(!validateempty() |!validateans() )
                {
                    return;
                }
                else {
                    insertData(t3sst, t3ssq, t3sso1, t3sso2, t3sso3, t3sscop);
                    Toast.makeText(T_3_Ss.this, "Question Added Successful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private Boolean validateempty() {
        String t3sst = t3sstitle.getText().toString();
        String t3ssq = t3ssques.getText().toString();
        String t3sso1 = t3ssop1.getText().toString();
        String t3sso2 = t3ssop2.getText().toString();
        String t3sso3 = t3ssop3.getText().toString();
        String t3sscop = t3sscrtop.getText().toString();
        if (t3sst.isEmpty() || t3ssq.isEmpty()|| t3sso1.isEmpty()|| t3sso2.isEmpty()|| t3sso3.isEmpty()|| t3sscop.isEmpty()) {
            Toast.makeText(T_3_Ss.this, "Please fill all the details", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            return true;
        }
    }

    private  Boolean validateans()
    {
        String t3sso1 = t3ssop1.getText().toString();
        String t3sso2 = t3ssop2.getText().toString();
        String t3sso3 = t3ssop3.getText().toString();
        String t3sscop = t3sscrtop.getText().toString();
        if (!(t3sscop.equals(t3sso1)||t3sscop.equals(t3sso2)||t3sscop.equals(t3sso3)))
        {
            t3sscrtop.setError("Answer does not match the given options");
            return false;
        }
        else
        {
            t3sscrtop.setError(null);
            //regPassword.setErrorEnabled(false);
            return true;
        }
    }
    public void insertData (String t3sst, String t3ssq, String t3sso1, String t3sso2, String t3sso3, String t3sscop){
        ContentValues values11 = new ContentValues();

        values11.put("Title", t3sst);
        values11.put("Question", t3ssq);
        values11.put("Option1", t3sso1);
        values11.put("Option2", t3sso2);
        values11.put("Option3", t3sso3);
        values11.put("Correctans", t3sscop);


        sqLiteDatabase.insert(TABLE_11, null, values11);
        t3sstitle.setText("");
        t3ssques.setText("");
        t3ssop1.setText("");
        t3ssop2.setText("");
        t3ssop3.setText("");
        t3sscrtop.setText("");
    }
}