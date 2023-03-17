package com.example.aplus;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Teacher_doubt_stu extends AppCompatActivity {
    FloatingActionButton floatbtntea;
    RecyclerView recyclerView;
    private SQLiteDatabase sqLiteDatabase;
    private SQLiteOpenHelper openHelper;
    ImageView empty_imageview;
    TextView no_data,title_txt,uname_parent_txt, Que_txt,uname_teacher;
    DatabaseHelper databaseHelper;
    ArrayList<String> Question,UnameStudent, UnameTeacher,Response;
    Teacher_Doubt_adp teacher_doubt_adp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_doubt_stu);
        floatbtntea=findViewById(R.id.floatbtntea);
        openHelper = new DatabaseHelper(this);
        sqLiteDatabase = openHelper.getReadableDatabase();
        recyclerView = findViewById(R.id.recyclerView);

        empty_imageview = findViewById(R.id.empty_imageview);
        no_data = findViewById(R.id.no_data);

        databaseHelper = new DatabaseHelper(Teacher_doubt_stu.this);
        UnameStudent = new ArrayList<>();
        Question = new ArrayList<>();
        String name=getIntent().getStringExtra("message_key1");
        //Intent intent= new Intent(Teacher_Concern.this,Teacher_Concern_adp.class);
        // intent.putExtra("message_key",name);
        storeDataInArrays();

        teacher_doubt_adp = new Teacher_Doubt_adp(Teacher_doubt_stu.this, this, UnameStudent,Question,name);
        recyclerView.setAdapter(teacher_doubt_adp);
        recyclerView.setLayoutManager(new LinearLayoutManager(Teacher_doubt_stu.this));

        floatbtntea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Teacher_doubt_stu.this,Teacher_Concern.class);
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

        Cursor abc=sqLiteDatabase.rawQuery("Select UnameStudent,Question from Doubt ",null);
        if (abc.getCount() == 0) {
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }
        else
        {
            while (abc.moveToNext())
            {
                UnameStudent.add(abc.getString(0));
                Question.add(abc.getString(1));

            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);

        }

    }

}