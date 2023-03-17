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

public class T_4_Sci extends AppCompatActivity {
    public static final String TABLE_14="Class4sci";
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase sqLiteDatabase;
    private Button t4sciadd;
    private Cursor cursor;
    private TextView t4scititle,t4sciques,t4sciop1,t4sciop2,t4sciop3,t4scicrtop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t4_sci);

        openHelper = new DatabaseHelper(this);
        t4sciadd = findViewById(R.id.t4sciaddque);
        t4sciques = findViewById(R.id.t4sciq);
        t4scititle = findViewById(R.id.t4scititle);
        t4sciop1 = findViewById(R.id.t4sciop1);
        t4sciop2 = findViewById(R.id.t4sciop2);
        t4sciop3 = findViewById(R.id.t4sciop3);
        t4scicrtop = findViewById(R.id.t4scicrtop);

        t4sciadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqLiteDatabase = openHelper.getWritableDatabase();

                String t4scit = t4scititle.getText().toString();
                String t4sciq = t4sciques.getText().toString();
                String t4scio1 = t4sciop1.getText().toString();
                String t4scio2 = t4sciop2.getText().toString();
                String t4scio3 = t4sciop3.getText().toString();
                String t4scicop = t4scicrtop.getText().toString();
                String str="CREATE TABLE IF NOT EXISTS "+TABLE_14 +"(ID INTEGER PRIMARY KEY AUTOINCREMENT,Title TEXT,Question TEXT,Option1 TEXT,Option2 TEXT,Option3 TEXT,Correctans TEXT)";
                sqLiteDatabase.execSQL(str);
                if(!validateempty() |!validateans() )
                {
                    return;
                }
                else {
                    insertData(t4scit, t4sciq, t4scio1, t4scio2, t4scio3, t4scicop);
                    Toast.makeText(T_4_Sci.this, "Question Added Successful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private Boolean validateempty() {
        String t4scit = t4scititle.getText().toString();
        String t4sciq = t4sciques.getText().toString();
        String t4scio1 = t4sciop1.getText().toString();
        String t4scio2 = t4sciop2.getText().toString();
        String t4scio3 = t4sciop3.getText().toString();
        String t4scicop = t4scicrtop.getText().toString();
        if (t4scit.isEmpty() || t4sciq.isEmpty()|| t4scio1.isEmpty()|| t4scio2.isEmpty()|| t4scio3.isEmpty()|| t4scicop.isEmpty()) {
            Toast.makeText(T_4_Sci.this, "Please fill all the details", Toast.LENGTH_LONG).show();
            return false;
        }
        else {
            return true;
        }
    }

    private  Boolean validateans()
    {
        String t4scio1 = t4sciop1.getText().toString();
        String t4scio2 = t4sciop2.getText().toString();
        String t4scio3 = t4sciop3.getText().toString();
        String t4scicop = t4scicrtop.getText().toString();

        if (!(t4scicop.equals(t4scio1)||t4scicop.equals(t4scio2)||t4scicop.equals(t4scio3)))
        {
            t4scicrtop.setError("Answer does not match the given options");
            return false;
        }
        else
        {
            t4scicrtop.setError(null);
            //regPassword.setErrorEnabled(false);
            return true;
        }
    }
    public void insertData (String t4scit, String t4sciq, String t4scio1, String t4scio2, String t4scio3, String t4scicop){
        ContentValues values14 = new ContentValues();

        values14.put("Title", t4scit);
        values14.put("Question", t4sciq);
        values14.put("Option1", t4scio1);
        values14.put("Option2", t4scio2);
        values14.put("Option3", t4scio3);
        values14.put("Correctans", t4scicop);


        sqLiteDatabase.insert(TABLE_14, null, values14);
        t4scititle.setText("");
        t4sciques.setText("");
        t4sciop1.setText("");
        t4sciop2.setText("");
        t4sciop3.setText("");
        t4scicrtop.setText("");
    }
}