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

public class T_2_Ss extends AppCompatActivity {
    public static final String TABLE_7="Class2ss";
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase sqLiteDatabase;
    private Button t2ssadd;
    private Cursor cursor;
    private TextView t2sstitle,t2ssques,t2ssop1,t2ssop2,t2ssop3,t2sscrtop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t2_ss);
        openHelper = new DatabaseHelper(this);
        t2ssadd = findViewById(R.id.t2ssaddque);
        t2ssques = findViewById(R.id.t2ssq);
        t2sstitle = findViewById(R.id.t2sstitle);
        t2ssop1 = findViewById(R.id.t2ssop1);
        t2ssop2 = findViewById(R.id.t2ssop2);
        t2ssop3 = findViewById(R.id.t2ssop3);
        t2sscrtop = findViewById(R.id.t2sscrtop);

        t2ssadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqLiteDatabase = openHelper.getWritableDatabase();

                String t2sst = t2sstitle.getText().toString();
                String t2ssq = t2ssques.getText().toString();
                String t2sso1 = t2ssop1.getText().toString();
                String t2sso2 = t2ssop2.getText().toString();
                String t2sso3 = t2ssop3.getText().toString();
                String t2sscop = t2sscrtop.getText().toString();
                String str="CREATE TABLE IF NOT EXISTS "+TABLE_7 +"(ID INTEGER PRIMARY KEY AUTOINCREMENT,Title TEXT,Question TEXT,Option1 TEXT,Option2 TEXT,Option3 TEXT,Correctans TEXT)";
                sqLiteDatabase.execSQL(str);
                if(!validateempty() |!validateans() )
                {
                    return;
                }
                else {
                    insertData(t2sst, t2ssq, t2sso1, t2sso2, t2sso3, t2sscop);
                    Toast.makeText(T_2_Ss.this, "Question Added Successful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private Boolean validateempty() {
        String t2sst = t2sstitle.getText().toString();
        String t2ssq = t2ssques.getText().toString();
        String t2sso1 = t2ssop1.getText().toString();
        String t2sso2 = t2ssop2.getText().toString();
        String t2sso3 = t2ssop3.getText().toString();
        String t2sscop = t2sscrtop.getText().toString();
        if (t2sst.isEmpty() || t2ssq.isEmpty()|| t2sso1.isEmpty()|| t2sso2.isEmpty()|| t2sso3.isEmpty()|| t2sscop.isEmpty()) {
            Toast.makeText(T_2_Ss.this, "Please fill all the details", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            return true;
        }
    }

    private  Boolean validateans()
    {
        String t2sso1 = t2ssop1.getText().toString();
        String t2sso2 = t2ssop2.getText().toString();
        String t2sso3 = t2ssop3.getText().toString();
        String t2sscop = t2sscrtop.getText().toString();
        if (!(t2sscop.equals(t2sso1)||t2sscop.equals(t2sso2)||t2sscop.equals(t2sso3)))
        {
            t2sscrtop.setError("Answer does not match the given options");
            return false;
        }
        else
        {
            t2sscrtop.setError(null);
            //regPassword.setErrorEnabled(false);
            return true;
        }
    }
    public void insertData (String t2sst, String t2ssq, String t2sso1, String t2sso2, String t2sso3, String t2sscop){
        ContentValues values7 = new ContentValues();

        values7.put("Title", t2sst);
        values7.put("Question", t2ssq);
        values7.put("Option1", t2sso1);
        values7.put("Option2", t2sso2);
        values7.put("Option3", t2sso3);
        values7.put("Correctans", t2sscop);


        sqLiteDatabase.insert(TABLE_7, null, values7);
        t2sstitle.setText("");
        t2ssques.setText("");
        t2ssop1.setText("");
        t2ssop2.setText("");
        t2ssop3.setText("");
        t2sscrtop.setText("");

    }
}