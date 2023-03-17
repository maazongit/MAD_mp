package com.example.aplus;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Student_Concern extends AppCompatActivity {

    public static final String TABLE_23="Doubt";
    RecyclerView recyclerView;
    private SQLiteDatabase sqLiteDatabase;
    private SQLiteOpenHelper openHelper;
    ImageView empty_imageview;
    TextView no_data,uname_parent_txt, Que_txt,response_teacher,uname_teacher,uname_txt;
    DatabaseHelper databaseHelper;
    ArrayList<String> Question,UnameStudent, UnameTeacher,Reply;
    Student_Concern_adp student_concern_adp;
    FloatingActionButton floatingActionButton2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_concern);
        openHelper = new DatabaseHelper(this);
        sqLiteDatabase = openHelper.getReadableDatabase();
        recyclerView = findViewById(R.id.recyclerView);


        floatingActionButton2=findViewById(R.id.floatingActionButton2);
        empty_imageview = findViewById(R.id.empty_imageview);
        no_data = findViewById(R.id.no_data);

        databaseHelper = new DatabaseHelper(Student_Concern.this);
        UnameStudent = new ArrayList<>();
        Question=new ArrayList<>();
        UnameTeacher=new ArrayList<>();
        Reply=new ArrayList<>();
     //   storeDataInArrays();

        student_concern_adp = new Student_Concern_adp(Student_Concern.this, this,UnameStudent ,Question, UnameTeacher,Reply);
        recyclerView.setAdapter(student_concern_adp);
        recyclerView.setLayoutManager(new LinearLayoutManager(Student_Concern.this));

        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder
                        = new AlertDialog.Builder(Student_Concern.this);
                builder.setTitle("Query");

                // set the custom layout
                final View customLayout = getLayoutInflater().inflate(R.layout.custom_layout, null);
                builder.setView(customLayout);

                // add a button
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        EditText editText = customLayout.findViewById(R.id.editText);
                        sendDialogDataToActivity(editText.getText().toString());
                    }
                });

                // create and show
                // the alert dialog
                AlertDialog dialog = builder.create();
                dialog.show();
            }
            //Intent intent= new Intent(Parent_Concern.this,Parent_Query.class);
            // intent.putExtra("message_key1",username);
            // startActivity(intent);
            //  sqLiteDatabase = openHelper.getWritableDatabase();

        });
    }



    private void sendDialogDataToActivity(String data)
    {
        String username=getIntent().getStringExtra("message_key1");
        String str="CREATE TABLE IF NOT EXISTS "+TABLE_23 +"(ID INTEGER PRIMARY KEY AUTOINCREMENT,UnameStudent TEXT,Question TEXT)";
        sqLiteDatabase.execSQL(str);
        insertData(username,data);
        Toast.makeText(Student_Concern.this, "QUERY Added Successful", Toast.LENGTH_SHORT).show();
    }


    public void insertData (String username, String data) {
        ContentValues values6 = new ContentValues();

        values6.put("UnameStudent", username);
        values6.put("Question", data);
        sqLiteDatabase.insert(TABLE_23, null, values6);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }
  // void storeDataInArrays() {
/*
        Cursor abc = sqLiteDatabase.rawQuery("Select UnameParent,Question from Concern ", null);
        if (abc.getCount() == 0) {
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }
        else
        {
            while (abc.moveToNext() ) {
                UnameParent.add(abc.getString(0));
                Question.add(abc.getString(1));
                //UnameTeacher.add(abc1.getString(0));
                //Reply.add(abc1.getString(1));

            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }

 */
     /*  Cursor abc1 = sqLiteDatabase.rawQuery("Select UnameStudent,Question,UnameTeacher,Reply from TtoSReply ", null);

        if (abc1.getCount() == 0)
        {
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }
        else
        {
            while (abc1.moveToNext())
            {
                UnameStudent.add(abc1.getString(0));
                Question.add(abc1.getString(1));
                UnameTeacher.add(abc1.getString(2));
                Reply.add(abc1.getString(3));

            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }



    }*/


}
