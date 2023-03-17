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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class TeacherResponse extends AppCompatActivity {
    RecyclerView recyclerView;
    private SQLiteDatabase sqLiteDatabase;
    private SQLiteOpenHelper openHelper;
    ImageView empty_imageview;
    TextView no_data;
    EditText classteacher, subtecher;
    Button btnvt;
    Context context;
    DatabaseHelper databaseHelper;
    ArrayList<String> Uname,Question;
    Teacher_Response_adp teacher_response_adp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_response);
        openHelper = new DatabaseHelper(this);
        sqLiteDatabase = openHelper.getReadableDatabase();
        recyclerView = findViewById(R.id.recyclerViewt);
        btnvt = findViewById(R.id.buttonvt);
        empty_imageview = findViewById(R.id.empty_imageviewt);
        no_data = findViewById(R.id.no_datat);
        classteacher = findViewById(R.id.classteacher);
        subtecher = findViewById(R.id.subteacher);
        databaseHelper = new DatabaseHelper(TeacherResponse.this);
        Uname = new ArrayList<>();
        Question = new ArrayList<>();

        teacher_response_adp = new Teacher_Response_adp(TeacherResponse.this, this, Uname, Question, classteacher, subtecher);
        recyclerView.setAdapter(teacher_response_adp);
        btnvt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uname.clear();
                Question.clear();

                Cursor abc = sqLiteDatabase.rawQuery("Select Username,Question from Response where Class= '"+classteacher.getText().toString()+"' and Subject= '"+subtecher.getText().toString() +"' ", null);

                if (abc.getCount() == 0) {
                    empty_imageview.setVisibility(View.VISIBLE);
                    no_data.setVisibility(View.VISIBLE);
                } else {
                    while (abc.moveToNext()) {

                        Uname.add(abc.getString(0));
                        Question.add(abc.getString(1));


                        recyclerView.setLayoutManager(new LinearLayoutManager(TeacherResponse.this));


                    }
                    empty_imageview.setVisibility(View.GONE);
                    no_data.setVisibility(View.GONE);
                }


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




}
