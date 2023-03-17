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

import java.util.ArrayList;

public class S_3_Math extends AppCompatActivity {
    RecyclerView recyclerView;
    private SQLiteDatabase sqLiteDatabase;
    private SQLiteOpenHelper openHelper;
    ImageView empty_imageview;
    TextView no_data;

    DatabaseHelper databaseHelper;
    ArrayList<String> Title,Question;
    S_3_Math_ADP s_3_math_adp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s3_math);
        openHelper = new DatabaseHelper(this);
        sqLiteDatabase = openHelper.getReadableDatabase();
        recyclerView = findViewById(R.id.recyclerView);
        String uname=getIntent().getStringExtra("message_key");
        String class1=getIntent().getStringExtra("message_key1");
        String sub=getIntent().getStringExtra("message_key2");
        empty_imageview = findViewById(R.id.empty_imageview);
        no_data = findViewById(R.id.no_data);

        databaseHelper = new DatabaseHelper(S_3_Math.this);
        Title = new ArrayList<>();
        Question=new ArrayList<>();
        storeDataInArrays();

        s_3_math_adp = new S_3_Math_ADP(S_3_Math.this, this,  Title,Question,uname,class1,sub);
        recyclerView.setAdapter(s_3_math_adp);
        recyclerView.setLayoutManager(new LinearLayoutManager(S_3_Math.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }

    void storeDataInArrays() {

        Cursor abc=sqLiteDatabase.rawQuery("Select Title,Question from Class3Math ",null);
        if (abc.getCount() == 0) {
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }
        else
        {
            while (abc.moveToNext())
            {
                Title.add(abc.getString(0));
                Question.add(abc.getString(1));

            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }
}