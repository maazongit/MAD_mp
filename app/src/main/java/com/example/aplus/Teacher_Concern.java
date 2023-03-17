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

public class Teacher_Concern extends AppCompatActivity {

    RecyclerView recyclerView;
    private SQLiteDatabase sqLiteDatabase;
    private SQLiteOpenHelper openHelper;
    ImageView empty_imageview;
    TextView no_data,title_txt,uname_parent_txt, Que_txt,uname_teacher;
    DatabaseHelper databaseHelper;
    ArrayList<String> Question,UnameParent, UnameTeacher,Response;
    Teacher_Concern_adp teacher_concern_adp;
    FloatingActionButton floatbtnstu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_concern);
        openHelper = new DatabaseHelper(this);
        sqLiteDatabase = openHelper.getReadableDatabase();
        recyclerView = findViewById(R.id.recyclerView);
        floatbtnstu=findViewById(R.id.floatbtnstu);
        empty_imageview = findViewById(R.id.empty_imageview);
        no_data = findViewById(R.id.no_data);

        databaseHelper = new DatabaseHelper(Teacher_Concern.this);
        UnameParent = new ArrayList<>();
        Question = new ArrayList<>();
        String name=getIntent().getStringExtra("message_key1");
        //Intent intent= new Intent(Teacher_Concern.this,Teacher_Concern_adp.class);
       // intent.putExtra("message_key",name);
        storeDataInArrays();

        teacher_concern_adp = new Teacher_Concern_adp(Teacher_Concern.this, this, UnameParent,Question,name);
        recyclerView.setAdapter(teacher_concern_adp);
        recyclerView.setLayoutManager(new LinearLayoutManager(Teacher_Concern.this));

        floatbtnstu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Teacher_Concern.this,Teacher_doubt_stu.class);
                startActivity(intent);
            }
        });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }

    void storeDataInArrays() {

        Cursor abc=sqLiteDatabase.rawQuery("Select UnameParent,Question from Concern ",null);
        if (abc.getCount() == 0) {
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }
        else
        {
            while (abc.moveToNext())
            {
                UnameParent.add(abc.getString(0));
                Question.add(abc.getString(1));

            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);

        }

    }

}