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

public class T_5_Sci extends AppCompatActivity {

    public static final String TABLE_18="Class5sci";
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase sqLiteDatabase;
    private Button t5sciadd;
    private Cursor cursor;
    private TextView t5scititle,t5sciques,t5sciop1,t5sciop2,t5sciop3,t5scicrtop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t5_sci);

        openHelper = new DatabaseHelper(this);
        t5sciadd = findViewById(R.id.t5sciaddque);
        t5sciques = findViewById(R.id.t5sciq);
        t5scititle = findViewById(R.id.t5scititle);
        t5sciop1 = findViewById(R.id.t5sciop1);
        t5sciop2 = findViewById(R.id.t5sciop2);
        t5sciop3 = findViewById(R.id.t5sciop3);
        t5scicrtop = findViewById(R.id.t5scicrtop);

        t5sciadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqLiteDatabase = openHelper.getWritableDatabase();

                String t5scit = t5scititle.getText().toString();
                String t5sciq = t5sciques.getText().toString();
                String t5scio1 = t5sciop1.getText().toString();
                String t5scio2 = t5sciop2.getText().toString();
                String t5scio3 = t5sciop3.getText().toString();
                String t5scicop = t5scicrtop.getText().toString();
                String str="CREATE TABLE IF NOT EXISTS "+TABLE_18+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,Title TEXT,Question TEXT,Option1 TEXT,Option2 TEXT,Option3 TEXT,Correctans TEXT)";
                sqLiteDatabase.execSQL(str);
                if(!validateempty() |!validateans() )
                {
                    return;
                }
                else {
                    insertData(t5scit, t5sciq, t5scio1, t5scio2, t5scio3, t5scicop);
                    Toast.makeText(T_5_Sci.this, "Question Added Successful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private Boolean validateempty() {
        String t5scit = t5scititle.getText().toString();
        String t5sciq = t5sciques.getText().toString();
        String t5scio1 = t5sciop1.getText().toString();
        String t5scio2 = t5sciop2.getText().toString();
        String t5scio3 = t5sciop3.getText().toString();
        String t5scicop = t5scicrtop.getText().toString();
        if (t5scit.isEmpty() || t5sciq.isEmpty()|| t5scio1.isEmpty()|| t5scio2.isEmpty()|| t5scio3.isEmpty()|| t5scicop.isEmpty()) {
            Toast.makeText(T_5_Sci.this, "Please fill all the details", Toast.LENGTH_LONG).show();
            return false;
        }
        else {
            return true;
        }
    }

    private  Boolean validateans()
    {
        String t5scio1 = t5sciop1.getText().toString();
        String t5scio2 = t5sciop2.getText().toString();
        String t5scio3 = t5sciop3.getText().toString();
        String t5scicop = t5scicrtop.getText().toString();

        if (!(t5scicop.equals(t5scio1)||t5scicop.equals(t5scio2)||t5scicop.equals(t5scio3)))
        {
            t5scicrtop.setError("Answer does not match the given options");
            return false;
        }
        else
        {
            t5scicrtop.setError(null);
            //regPassword.setErrorEnabled(false);
            return true;
        }
    }
    public void insertData (String t5scit, String t5sciq, String t5scio1, String t5scio2, String t5scio3, String t5scicop){
        ContentValues values18 = new ContentValues();

        values18.put("Title", t5scit);
        values18.put("Question", t5sciq);
        values18.put("Option1", t5scio1);
        values18.put("Option2", t5scio2);
        values18.put("Option3", t5scio3);
        values18.put("Correctans", t5scicop);


        sqLiteDatabase.insert(TABLE_18, null, values18);
        t5scititle.setText("");
        t5sciques.setText("");
        t5sciop1.setText("");
        t5sciop2.setText("");
        t5sciop3.setText("");
        t5scicrtop.setText("");
    }
}