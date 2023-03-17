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

public class T_2_Sci extends AppCompatActivity {
    public static final String TABLE_4="Class2Sci";
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase sqLiteDatabase;
private Button t2sciadd;
    private Cursor cursor;
   private TextView t2scititle,t2sciques,t2sciop1,t2sciop2,t2sciop3,t2scicrtop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t2_sci);
        openHelper = new DatabaseHelper(this);
        t2sciadd = findViewById(R.id.t2sciaddque);
        t2sciques = findViewById(R.id.t2sciq);
        t2scititle = findViewById(R.id.t2scititle);
        t2sciop1 = findViewById(R.id.t2sciop1);
        t2sciop2 = findViewById(R.id.t2sciop2);
        t2sciop3 = findViewById(R.id.t2sciop3);
        t2scicrtop = findViewById(R.id.t2scicrtop);

        t2sciadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqLiteDatabase = openHelper.getWritableDatabase();

                String t2scit = t2scititle.getText().toString();
                String t2sciq = t2sciques.getText().toString();
                String t2scio1 = t2sciop1.getText().toString();
                String t2scio2 = t2sciop2.getText().toString();
                String t2scio3 = t2sciop3.getText().toString();
                String t2scicop = t2scicrtop.getText().toString();
                String str="CREATE TABLE IF NOT EXISTS "+TABLE_4 +"(ID INTEGER PRIMARY KEY AUTOINCREMENT,Title TEXT,Question TEXT,Option1 TEXT,Option2 TEXT,Option3 TEXT,Correctans TEXT)";
                sqLiteDatabase.execSQL(str);
                if(!validateempty() |!validateans() )
                {
                    return;
                }
                else {
                    insertData(t2scit, t2sciq, t2scio1, t2scio2, t2scio3, t2scicop);
                    Toast.makeText(T_2_Sci.this, "Question Added Successful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private Boolean validateempty() {
        String t2scit = t2scititle.getText().toString();
        String t2sciq = t2sciques.getText().toString();
        String t2scio1 = t2sciop1.getText().toString();
        String t2scio2 = t2sciop2.getText().toString();
        String t2scio3 = t2sciop3.getText().toString();
        String t2scicop = t2scicrtop.getText().toString();
        if (t2scit.isEmpty() || t2sciq.isEmpty()|| t2scio1.isEmpty()|| t2scio2.isEmpty()|| t2scio3.isEmpty()|| t2scicop.isEmpty()) {
            Toast.makeText(T_2_Sci.this, "Please fill all the details", Toast.LENGTH_LONG).show();
            return false;
        }
        else {
            return true;
        }
    }

    private  Boolean validateans()
    {
        String t2scio1 = t2sciop1.getText().toString();
        String t2scio2 = t2sciop2.getText().toString();
        String t2scio3 = t2sciop3.getText().toString();
        String t2scicop = t2scicrtop.getText().toString();

        if (!(t2scicop.equals(t2scio1)||t2scicop.equals(t2scio2)||t2scicop.equals(t2scio3)))
        {
            t2scicrtop.setError("Answer does not match the given options");
            return false;
        }
        else
        {
            t2scicrtop.setError(null);
            //regPassword.setErrorEnabled(false);
            return true;
        }
    }
       public void insertData (String t2scit, String t2sciq, String t2scio1, String t2scio2, String t2scio3, String t2scicop){
        ContentValues values4 = new ContentValues();

        values4.put("Title", t2scit);
        values4.put("Question", t2sciq);
        values4.put("Option1", t2scio1);
        values4.put("Option2", t2scio2);
        values4.put("Option3", t2scio3);
        values4.put("Correctans", t2scicop);


            sqLiteDatabase.insert(TABLE_4, null, values4);
           t2scititle.setText("");
           t2sciques.setText("");
           t2sciop1.setText("");
           t2sciop2.setText("");
           t2sciop3.setText("");
           t2scicrtop.setText("");

   }
}