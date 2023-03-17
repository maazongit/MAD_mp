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

public class T_4_Math extends AppCompatActivity {
    public static final String TABLE_13="Class4math";
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase sqLiteDatabase;
    private Button t4maadd;
    private Cursor cursor;
    private TextView t4matitle,t4maques,t4maop1,t4maop2,t4maop3,t4macrtop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t4_math);
        openHelper = new DatabaseHelper(this);
        t4maadd = findViewById(R.id.t4maaddque);
        t4maques = findViewById(R.id.t4maq);
        t4matitle = findViewById(R.id.t4matitle);
        t4maop1 = findViewById(R.id.t4maop1);
        t4maop2 = findViewById(R.id.t4maop2);
        t4maop3 = findViewById(R.id.t4maop3);
        t4macrtop = findViewById(R.id.t4macrtop);

        t4maadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqLiteDatabase = openHelper.getWritableDatabase();

                String t4mat = t4matitle.getText().toString();
                String t4maq = t4maques.getText().toString();
                String t4mao1 = t4maop1.getText().toString();
                String t4mao2 = t4maop2.getText().toString();
                String t4mao3 = t4maop3.getText().toString();
                String t4macop = t4macrtop.getText().toString();
                String str="CREATE TABLE IF NOT EXISTS "+TABLE_13 +"(ID INTEGER PRIMARY KEY AUTOINCREMENT,Title TEXT,Question TEXT,Option1 TEXT,Option2 TEXT,Option3 TEXT,Correctans TEXT)";
                sqLiteDatabase.execSQL(str);
                if(!validateempty() |!validateans() )
                {
                    return;
                }
                else {
                    insertData(t4mat, t4maq, t4mao1, t4mao2, t4mao3, t4macop);
                    Toast.makeText(T_4_Math.this, "Question Added Successful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private Boolean validateempty() {
        String t4mat = t4matitle.getText().toString();
        String t4maq = t4maques.getText().toString();
        String t4mao1 = t4maop1.getText().toString();
        String t4mao2 = t4maop2.getText().toString();
        String t4mao3 = t4maop3.getText().toString();
        String t4macop = t4macrtop.getText().toString();
        if (t4mat.isEmpty() || t4maq.isEmpty()|| t4mao1.isEmpty()|| t4mao2.isEmpty()|| t4mao3.isEmpty()|| t4macop.isEmpty()) {
            Toast.makeText(T_4_Math.this, "Please fill all the details", Toast.LENGTH_LONG).show();
            return false;
        }
        else {
            return true;
        }
    }

    private  Boolean validateans()
    {
        String t4mao1 = t4maop1.getText().toString();
        String t4mao2 = t4maop2.getText().toString();
        String t4mao3 = t4maop3.getText().toString();
        String t4macop = t4macrtop.getText().toString();

        if (!(t4macop.equals(t4mao1)||t4macop.equals(t4mao2)||t4macop.equals(t4mao3)))
        {
            t4macrtop.setError("Answer does not match the given options");
            return false;
        }
        else
        {
            t4macrtop.setError(null);
            //regPassword.setErrorEnabled(false);
            return true;
        }
    }
    public void insertData (String t4mat, String t4maq, String t4mao1, String t4mao2, String t4mao3, String t4macop){
        ContentValues values13 = new ContentValues();

        values13.put("Title", t4mat);
        values13.put("Question", t4maq);
        values13.put("Option1", t4mao1);
        values13.put("Option2", t4mao2);
        values13.put("Option3", t4mao3);
        values13.put("Correctans", t4macop);


        sqLiteDatabase.insert(TABLE_13, null, values13);

        t4matitle.setText("");
        t4maques.setText("");
        t4maop1.setText("");
        t4maop2.setText("");
        t4maop3.setText("");
        t4macrtop.setText("");
    }
}