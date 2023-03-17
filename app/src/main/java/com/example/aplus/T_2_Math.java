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

public class T_2_Math extends AppCompatActivity {
    public static final String TABLE_5="Class2math";
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase sqLiteDatabase;
    private Button t2maadd;
    private Cursor cursor;
    private TextView t2matitle,t2maques,t2maop1,t2maop2,t2maop3,t2macrtop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t2_math);
        openHelper = new DatabaseHelper(this);
        t2maadd = findViewById(R.id.t2maaddque);
        t2maques = findViewById(R.id.t2maq);
        t2matitle = findViewById(R.id.t2matitle);
        t2maop1 = findViewById(R.id.t2maop1);
        t2maop2 = findViewById(R.id.t2maop2);
        t2maop3 = findViewById(R.id.t2maop3);
        t2macrtop = findViewById(R.id.t2macrtop);

        t2maadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqLiteDatabase = openHelper.getWritableDatabase();

                String t2mat = t2matitle.getText().toString();
                String t2maq = t2maques.getText().toString();
                String t2mao1 = t2maop1.getText().toString();
                String t2mao2 = t2maop2.getText().toString();
                String t2mao3 = t2maop3.getText().toString();
                String t2macop = t2macrtop.getText().toString();
                String str="CREATE TABLE IF NOT EXISTS "+TABLE_5 +"(ID INTEGER PRIMARY KEY AUTOINCREMENT,Title TEXT,Question TEXT,Option1 TEXT,Option2 TEXT,Option3 TEXT,Correctans TEXT)";
                sqLiteDatabase.execSQL(str);
                if(!validateempty() |!validateans() )
                {
                    return;
                }
                else {
                    insertData(t2mat, t2maq, t2mao1, t2mao2, t2mao3, t2macop);
                    Toast.makeText(T_2_Math.this, "Question Added Successful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private Boolean validateempty() {
        String t2mat = t2matitle.getText().toString();
        String t2maq = t2maques.getText().toString();
        String t2mao1 = t2maop1.getText().toString();
        String t2mao2 = t2maop2.getText().toString();
        String t2mao3 = t2maop3.getText().toString();
        String t2macop = t2macrtop.getText().toString();
        if (t2mat.isEmpty() || t2maq.isEmpty()|| t2mao1.isEmpty()|| t2mao2.isEmpty()|| t2mao3.isEmpty()|| t2macop.isEmpty()) {
            Toast.makeText(T_2_Math.this, "Please fill all the details", Toast.LENGTH_LONG).show();
            return false;
        }
        else {
            return true;
        }
    }

    private  Boolean validateans()
    {
        String t2mao1 = t2maop1.getText().toString();
        String t2mao2 = t2maop2.getText().toString();
        String t2mao3 = t2maop3.getText().toString();
        String t2macop = t2macrtop.getText().toString();

        if (!(t2macop.equals(t2mao1)||t2macop.equals(t2mao2)||t2macop.equals(t2mao3)))
        {
            t2macrtop.setError("Answer does not match the given options");
            return false;
        }
        else
        {
            t2macrtop.setError(null);
            //regPassword.setErrorEnabled(false);
            return true;
        }
    }
    public void insertData (String t2mat, String t2maq, String t2mao1, String t2mao2, String t2mao3, String t2macop){
        ContentValues values5 = new ContentValues();

        values5.put("Title", t2mat);
        values5.put("Question", t2maq);
        values5.put("Option1", t2mao1);
        values5.put("Option2", t2mao2);
        values5.put("Option3", t2mao3);
        values5.put("Correctans", t2macop);


        sqLiteDatabase.insert(TABLE_5, null, values5);

        t2matitle.setText("");
        t2maques.setText("");
        t2maop1.setText("");
        t2maop2.setText("");
        t2maop3.setText("");
        t2macrtop.setText("");
    }
}