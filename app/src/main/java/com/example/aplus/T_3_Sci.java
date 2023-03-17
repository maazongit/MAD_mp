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

public class T_3_Sci extends AppCompatActivity {
    public static final String TABLE_8="Class3sci";
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase sqLiteDatabase;
    private Button t3sciadd;
    private Cursor cursor;
    private TextView t3scititle,t3sciques,t3sciop1,t3sciop2,t3sciop3,t3scicrtop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t3_sci);

        openHelper = new DatabaseHelper(this);
        t3sciadd = findViewById(R.id.t3sciaddque);
        t3sciques = findViewById(R.id.t3sciq);
        t3scititle = findViewById(R.id.t3scititle);
        t3sciop1 = findViewById(R.id.t3sciop1);
        t3sciop2 = findViewById(R.id.t3sciop2);
        t3sciop3 = findViewById(R.id.t3sciop3);
        t3scicrtop = findViewById(R.id.t3scicrtop);

        t3sciadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqLiteDatabase = openHelper.getWritableDatabase();

                String t3scit = t3scititle.getText().toString();
                String t3sciq = t3sciques.getText().toString();
                String t3scio1 = t3sciop1.getText().toString();
                String t3scio2 = t3sciop2.getText().toString();
                String t3scio3 = t3sciop3.getText().toString();
                String t3scicop = t3scicrtop.getText().toString();
                String str="CREATE TABLE IF NOT EXISTS "+TABLE_8 +"(ID INTEGER PRIMARY KEY AUTOINCREMENT,Title TEXT,Question TEXT,Option1 TEXT,Option2 TEXT,Option3 TEXT,Correctans TEXT)";
                sqLiteDatabase.execSQL(str);
                if(!validateempty() |!validateans() )
                {
                    return;
                }
                else {
                    insertData(t3scit, t3sciq, t3scio1, t3scio2, t3scio3, t3scicop);
                    Toast.makeText(T_3_Sci.this, "Question Added Successful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private Boolean validateempty() {
        String t3scit = t3scititle.getText().toString();
        String t3sciq = t3sciques.getText().toString();
        String t3scio1 = t3sciop1.getText().toString();
        String t3scio2 = t3sciop2.getText().toString();
        String t3scio3 = t3sciop3.getText().toString();
        String t3scicop = t3scicrtop.getText().toString();
        if (t3scit.isEmpty() || t3sciq.isEmpty()|| t3scio1.isEmpty()|| t3scio2.isEmpty()|| t3scio3.isEmpty()|| t3scicop.isEmpty()) {
            Toast.makeText(T_3_Sci.this, "Please fill all the details", Toast.LENGTH_LONG).show();
            return false;
        }
        else {
            return true;
        }
    }

    private  Boolean validateans()
    {
        String t3scio1 = t3sciop1.getText().toString();
        String t3scio2 = t3sciop2.getText().toString();
        String t3scio3 = t3sciop3.getText().toString();
        String t3scicop = t3scicrtop.getText().toString();

        if (!(t3scicop.equals(t3scio1)||t3scicop.equals(t3scio2)||t3scicop.equals(t3scio3)))
        {
            t3scicrtop.setError("Answer does not match the given options");
            return false;
        }
        else
        {
            t3scicrtop.setError(null);
            //regPassword.setErrorEnabled(false);
            return true;
        }
    }
    public void insertData (String t3scit, String t3sciq, String t3scio1, String t3scio2, String t3scio3, String t3scicop){
        ContentValues values8 = new ContentValues();

        values8.put("Title", t3scit);
        values8.put("Question", t3sciq);
        values8.put("Option1", t3scio1);
        values8.put("Option2", t3scio2);
        values8.put("Option3", t3scio3);
        values8.put("Correctans", t3scicop);


        sqLiteDatabase.insert(TABLE_8, null, values8);
        t3scititle.setText("");
        t3sciques.setText("");
        t3sciop1.setText("");
        t3sciop2.setText("");
        t3sciop3.setText("");
        t3scicrtop.setText("");
    }
}