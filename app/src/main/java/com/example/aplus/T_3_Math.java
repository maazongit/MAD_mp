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

public class T_3_Math extends AppCompatActivity {

    public static final String TABLE_9="Class3math";
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase sqLiteDatabase;
    private Button t3maadd;
    private Cursor cursor;
    private TextView t3matitle,t3maques,t3maop1,t3maop2,t3maop3,t3macrtop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t3_math);
        openHelper = new DatabaseHelper(this);
        t3maadd = findViewById(R.id.t3maaddque);
        t3maques = findViewById(R.id.t3maq);
        t3matitle = findViewById(R.id.t3matitle);
        t3maop1 = findViewById(R.id.t3maop1);
        t3maop2 = findViewById(R.id.t3maop2);
        t3maop3 = findViewById(R.id.t3maop3);
        t3macrtop = findViewById(R.id.t3macrtop);

        t3maadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqLiteDatabase = openHelper.getWritableDatabase();

                String t3mat = t3matitle.getText().toString();
                String t3maq = t3maques.getText().toString();
                String t3mao1 = t3maop1.getText().toString();
                String t3mao2 = t3maop2.getText().toString();
                String t3mao3 = t3maop3.getText().toString();
                String t3macop = t3macrtop.getText().toString();
                String str="CREATE TABLE IF NOT EXISTS "+TABLE_9 +"(ID INTEGER PRIMARY KEY AUTOINCREMENT,Title TEXT,Question TEXT,Option1 TEXT,Option2 TEXT,Option3 TEXT,Correctans TEXT)";
                sqLiteDatabase.execSQL(str);
                if(!validateempty() |!validateans() )
                {
                    return;
                }
                else {
                    insertData(t3mat, t3maq, t3mao1, t3mao2, t3mao3, t3macop);
                    Toast.makeText(T_3_Math.this, "Question Added Successful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private Boolean validateempty() {
        String t3mat = t3matitle.getText().toString();
        String t3maq = t3maques.getText().toString();
        String t3mao1 = t3maop1.getText().toString();
        String t3mao2 = t3maop2.getText().toString();
        String t3mao3 = t3maop3.getText().toString();
        String t3macop = t3macrtop.getText().toString();
        if (t3mat.isEmpty() || t3maq.isEmpty()|| t3mao1.isEmpty()|| t3mao2.isEmpty()|| t3mao3.isEmpty()|| t3macop.isEmpty()) {
            Toast.makeText(T_3_Math.this, "Please fill all the details", Toast.LENGTH_LONG).show();
            return false;
        }
        else {
            return true;
        }
    }

    private  Boolean validateans()
    {
        String t3mao1 = t3maop1.getText().toString();
        String t3mao2 = t3maop2.getText().toString();
        String t3mao3 = t3maop3.getText().toString();
        String t3macop = t3macrtop.getText().toString();

        if (!(t3macop.equals(t3mao1)||t3macop.equals(t3mao2)||t3macop.equals(t3mao3)))
        {
            t3macrtop.setError("Answer does not match the given options");
            return false;
        }
        else
        {
            t3macrtop.setError(null);
            //regPassword.setErrorEnabled(false);
            return true;
        }
    }
    public void insertData (String t3mat, String t3maq, String t3mao1, String t3mao2, String t3mao3, String t3macop){
        ContentValues values9 = new ContentValues();

        values9.put("Title", t3mat);
        values9.put("Question", t3maq);
        values9.put("Option1", t3mao1);
        values9.put("Option2", t3mao2);
        values9.put("Option3", t3mao3);
        values9.put("Correctans", t3macop);


        sqLiteDatabase.insert(TABLE_9, null, values9);

        t3matitle.setText("");
        t3maques.setText("");
        t3maop1.setText("");
        t3maop2.setText("");
        t3maop3.setText("");
        t3macrtop.setText("");
    }
}