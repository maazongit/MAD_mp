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

public class T_5_Math extends AppCompatActivity {

    public static final String TABLE_17="Class5math";
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase sqLiteDatabase;
    private Button t5maadd;
    private Cursor cursor;
    private TextView t5matitle,t5maques,t5maop1,t5maop2,t5maop3,t5macrtop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t5_math);
        openHelper = new DatabaseHelper(this);
        t5maadd = findViewById(R.id.t5maaddque);
        t5maques = findViewById(R.id.t5maq);
        t5matitle = findViewById(R.id.t5matitle);
        t5maop1 = findViewById(R.id.t5maop1);
        t5maop2 = findViewById(R.id.t5maop2);
        t5maop3 = findViewById(R.id.t5maop3);
        t5macrtop = findViewById(R.id.t5macrtop);

        t5maadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqLiteDatabase = openHelper.getWritableDatabase();

                String t5mat = t5matitle.getText().toString();
                String t5maq = t5maques.getText().toString();
                String t5mao1 = t5maop1.getText().toString();
                String t5mao2 = t5maop2.getText().toString();
                String t5mao3 = t5maop3.getText().toString();
                String t5macop = t5macrtop.getText().toString();
                String str="CREATE TABLE IF NOT EXISTS "+TABLE_17 +"(ID INTEGER PRIMARY KEY AUTOINCREMENT,Title TEXT,Question TEXT,Option1 TEXT,Option2 TEXT,Option3 TEXT,Correctans TEXT)";
                sqLiteDatabase.execSQL(str);
                if(!validateempty() |!validateans() )
                {
                    return;
                }
                else {
                    insertData(t5mat, t5maq, t5mao1, t5mao2, t5mao3, t5macop);
                    Toast.makeText(T_5_Math.this, "Question Added Successful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private Boolean validateempty() {
        String t5mat = t5matitle.getText().toString();
        String t5maq = t5maques.getText().toString();
        String t5mao1 = t5maop1.getText().toString();
        String t5mao2 = t5maop2.getText().toString();
        String t5mao3 = t5maop3.getText().toString();
        String t5macop = t5macrtop.getText().toString();
        if (t5mat.isEmpty() || t5maq.isEmpty()|| t5mao1.isEmpty()|| t5mao2.isEmpty()|| t5mao3.isEmpty()|| t5macop.isEmpty()) {
            Toast.makeText(T_5_Math.this, "Please fill all the details", Toast.LENGTH_LONG).show();
            return false;
        }
        else {
            return true;
        }
    }

    private  Boolean validateans()
    {
        String t5mao1 = t5maop1.getText().toString();
        String t5mao2 = t5maop2.getText().toString();
        String t5mao3 = t5maop3.getText().toString();
        String t5macop = t5macrtop.getText().toString();

        if (!(t5macop.equals(t5mao1)||t5macop.equals(t5mao2)||t5macop.equals(t5mao3)))
        {
            t5macrtop.setError("Answer does not match the given options");
            return false;
        }
        else
        {
            t5macrtop.setError(null);
            //regPassword.setErrorEnabled(false);
            return true;
        }
    }
    public void insertData (String t5mat, String t5maq, String t5mao1, String t5mao2, String t5mao3, String t5macop){
        ContentValues values17 = new ContentValues();

        values17.put("Title", t5mat);
        values17.put("Question", t5maq);
        values17.put("Option1", t5mao1);
        values17.put("Option2", t5mao2);
        values17.put("Option3", t5mao3);
        values17.put("Correctans", t5macop);


        sqLiteDatabase.insert(TABLE_17, null, values17);

        t5matitle.setText("");
        t5maques.setText("");
        t5maop1.setText("");
        t5maop2.setText("");
        t5maop3.setText("");
        t5macrtop.setText("");
    }
}