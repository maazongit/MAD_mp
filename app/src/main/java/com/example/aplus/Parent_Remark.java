package com.example.aplus;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Parent_Remark extends AppCompatActivity {
    private TextView uname;
    RecyclerView recyclerView;
    private SQLiteDatabase sqLiteDatabase;
    private SQLiteOpenHelper openHelper;
    ImageView empty_imageview;
    TextView no_data;
    Context context;
    DatabaseHelper databaseHelper;
    ArrayList<String> Subject,Question,Class;
    Parent_Remark_adp parent_remark_adp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent_remark);
        uname=findViewById(R.id.textView35);
        Intent intent=getIntent();
        String cuname=intent.getStringExtra("messagekey");
        String cclass=intent.getStringExtra("messagekey1");
        uname.setText(cuname);
        openHelper = new DatabaseHelper(this);
        sqLiteDatabase = openHelper.getReadableDatabase();
        recyclerView = findViewById(R.id.recyclerViewt);
        empty_imageview = findViewById(R.id.empty_imageviewt);
        no_data = findViewById(R.id.no_datat);
        databaseHelper = new DatabaseHelper(Parent_Remark.this);
        Subject = new ArrayList<>();
        Question = new ArrayList<>();
        Class =new ArrayList<>();
        parent_remark_adp = new Parent_Remark_adp(Parent_Remark.this, this, Subject, Question,Class,cuname);
        recyclerView.setAdapter(parent_remark_adp);

        Cursor abc = sqLiteDatabase.rawQuery("Select Subject,Question,Class from Remark where Username= '"+uname.getText().toString()+"'", null);

        if (abc.getCount() == 0) {
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        } else {
            while (abc.moveToNext()) {

                Subject.add(abc.getString(0));
                Question.add(abc.getString(1));
                Class.add(abc.getString(2));


                recyclerView.setLayoutManager(new LinearLayoutManager(Parent_Remark.this));


            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }

}