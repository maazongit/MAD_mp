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

public class T_4_Ss extends AppCompatActivity {
    public static final String TABLE_15 = "Class4ss";
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase sqLiteDatabase;
    private Button t4ssadd;
    private Cursor cursor;
    private TextView t4sstitle, t4ssques, t4ssop1, t4ssop2, t4ssop3, t4sscrtop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t4_ss);
        openHelper = new DatabaseHelper(this);
        t4ssadd = findViewById(R.id.t4ssaddque);
        t4ssques = findViewById(R.id.t4ssq);
        t4sstitle = findViewById(R.id.t4sstitle);
        t4ssop1 = findViewById(R.id.t4ssop1);
        t4ssop2 = findViewById(R.id.t4ssop2);
        t4ssop3 = findViewById(R.id.t4ssop3);
        t4sscrtop = findViewById(R.id.t4sscrtop);

        t4ssadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqLiteDatabase = openHelper.getWritableDatabase();

                String t4sst = t4sstitle.getText().toString();
                String t4ssq = t4ssques.getText().toString();
                String t4sso1 = t4ssop1.getText().toString();
                String t4sso2 = t4ssop2.getText().toString();
                String t4sso3 = t4ssop3.getText().toString();
                String t4sscop = t4sscrtop.getText().toString();
                String str = "CREATE TABLE IF NOT EXISTS " + TABLE_15 + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,Title TEXT,Question TEXT,Option1 TEXT,Option2 TEXT,Option3 TEXT,Correctans TEXT)";
                sqLiteDatabase.execSQL(str);
                if(!validateempty() |!validateans() )
                {
                    return;
                }
                else {
                    insertData(t4sst, t4ssq, t4sso1, t4sso2, t4sso3, t4sscop);
                    Toast.makeText(T_4_Ss.this, "Question Added Successful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private Boolean validateempty() {
        String t4sst = t4sstitle.getText().toString();
        String t4ssq = t4ssques.getText().toString();
        String t4sso1 = t4ssop1.getText().toString();
        String t4sso2 = t4ssop2.getText().toString();
        String t4sso3 = t4ssop3.getText().toString();
        String t4sscop = t4sscrtop.getText().toString();
        if (t4sst.isEmpty() || t4ssq.isEmpty()|| t4sso1.isEmpty()|| t4sso2.isEmpty()|| t4sso3.isEmpty()|| t4sscop.isEmpty()) {
            Toast.makeText(T_4_Ss.this, "Please fill all the details", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            return true;
        }
    }

    private  Boolean validateans()
    {
        String t4sso1 = t4ssop1.getText().toString();
        String t4sso2 = t4ssop2.getText().toString();
        String t4sso3 = t4ssop3.getText().toString();
        String t4sscop = t4sscrtop.getText().toString();
        if (!(t4sscop.equals(t4sso1)||t4sscop.equals(t4sso2)||t4sscop.equals(t4sso3)))
        {
            t4sscrtop.setError("Answer does not match the given options");
            return false;
        }
        else
        {
            t4sscrtop.setError(null);
            //regPassword.setErrorEnabled(false);
            return true;
        }
    }
    public void insertData (String t4sst, String t4ssq, String t4sso1, String t4sso2, String t4sso3, String t4sscop){
        ContentValues values15 = new ContentValues();

        values15.put("Title", t4sst);
        values15.put("Question", t4ssq);
        values15.put("Option1", t4sso1);
        values15.put("Option2", t4sso2);
        values15.put("Option3", t4sso3);
        values15.put("Correctans", t4sscop);


        sqLiteDatabase.insert(TABLE_15, null, values15);
        t4sstitle.setText("");
        t4ssques.setText("");
        t4ssop1.setText("");
        t4ssop2.setText("");
        t4ssop3.setText("");
        t4sscrtop.setText("");
    }
}