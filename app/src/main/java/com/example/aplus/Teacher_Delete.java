package com.example.aplus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Teacher_Delete extends AppCompatActivity {
    TextView texttitle,textque,textclass,textsub;
    Button btndel,btncancel;
    private SQLiteDatabase sqLiteDatabase;
    private SQLiteOpenHelper openHelper;
    DatabaseHelper databaseHelper;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_delete);
        openHelper = new DatabaseHelper(this);
        sqLiteDatabase = openHelper.getReadableDatabase();
        textclass=findViewById(R.id.txtclasstech);
        textsub=findViewById(R.id.txtsubtech);
        textque=findViewById(R.id.txtquetech);
        texttitle=findViewById(R.id.txttitletech);
        btndel=findViewById(R.id.btndelete);
        btncancel=findViewById(R.id.btncancel);
        String question= getIntent().getStringExtra("Question");
        String titles= getIntent().getStringExtra("title");
        String class1=getIntent().getStringExtra("class");
        String sub=getIntent().getStringExtra("sub");
        texttitle.setText(titles);
        textque.setText(question);
        textsub.setText(sub);
        textclass.setText(class1);


        btndel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(textclass.getText().toString().equals("2") && textsub.getText().toString().equals("English"))
                {
                    Cursor abc=sqLiteDatabase.rawQuery("Delete from Class2Eng where Question='"+textque.getText().toString()+"' ",null);


                    if (abc.getCount() == -1) {
                        Toast.makeText(getApplicationContext(), "Failed to Delete.", Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Successfully Deleted.", Toast.LENGTH_SHORT).show();

                    }

                }
                else if(textclass.getText().toString().equals("2")&&textsub.getText().toString().equals("Math")) {
                    Cursor abc=sqLiteDatabase.rawQuery("Delete from Class2Math where Question='"+textque.getText().toString()+"'",null);
                    if (abc.getCount() == -1) {
                        Toast.makeText(getApplicationContext(), "Failed to Delete.", Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Successfully Deleted.", Toast.LENGTH_SHORT).show();

                    }


                }
            }
        });

        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }



}