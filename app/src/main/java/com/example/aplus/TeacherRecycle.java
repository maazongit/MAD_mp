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
import android.widget.Toast;

import java.util.ArrayList;

public class TeacherRecycle extends AppCompatActivity {
    RecyclerView recyclerView;
    private SQLiteDatabase sqLiteDatabase;
    private SQLiteOpenHelper openHelper;
    ImageView empty_imageview;
    TextView no_data;
    EditText classteacher, subtecher;
Button btnvt;
    Context context;
    DatabaseHelper databaseHelper;
    ArrayList<String> Title,Question;
   teacher_adp teacher_adp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_recycle);
        openHelper = new DatabaseHelper(this);
        sqLiteDatabase = openHelper.getReadableDatabase();
        recyclerView = findViewById(R.id.recyclerViewt);
        btnvt=findViewById(R.id.buttonvt);
        empty_imageview = findViewById(R.id.empty_imageviewt);
        no_data = findViewById(R.id.no_datat);
     classteacher=findViewById(R.id.classteacher);
        subtecher=findViewById(R.id.subteacher);
        databaseHelper = new DatabaseHelper(TeacherRecycle.this);
        Title = new ArrayList<>();
        Question=new ArrayList<>();
       String class1=classteacher.getText().toString();
        String sub=subtecher.getText().toString();
        teacher_adp = new teacher_adp(TeacherRecycle.this, this, Title,Question,classteacher,subtecher);
        recyclerView.setAdapter(teacher_adp);




        btnvt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Title.clear();
                Question.clear();

                if(classteacher.getText().toString().equals("2") && subtecher.getText().toString().equals("English"))
            {
                Cursor abc=sqLiteDatabase.rawQuery("Select Title,Question from Class2Eng ",null);

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

                        recyclerView.setLayoutManager(new LinearLayoutManager(TeacherRecycle.this));


                    }
                    empty_imageview.setVisibility(View.GONE);
                    no_data.setVisibility(View.GONE);
                }

            }
            else if(classteacher.getText().toString().equals("2")&&subtecher.getText().toString().equals("Math")) {
                Cursor abc=sqLiteDatabase.rawQuery("Select Title,Question from Class2Math ",null);

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

                        recyclerView.setLayoutManager(new LinearLayoutManager(TeacherRecycle.this));


                    }
                    empty_imageview.setVisibility(View.GONE);
                    no_data.setVisibility(View.GONE);
                }

            }
                else if(classteacher.getText().toString().equals("2")&&subtecher.getText().toString().equals("Science")) {
                    Cursor abc=sqLiteDatabase.rawQuery("Select Title,Question from Class2Sci ",null);

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

                            recyclerView.setLayoutManager(new LinearLayoutManager(TeacherRecycle.this));


                        }
                        empty_imageview.setVisibility(View.GONE);
                        no_data.setVisibility(View.GONE);
                    }

                }
                else if(classteacher.getText().toString().equals("2")&&subtecher.getText().toString().equals("Social science")) {
                    Cursor abc=sqLiteDatabase.rawQuery("Select Title,Question from Class2Ss ",null);

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

                            recyclerView.setLayoutManager(new LinearLayoutManager(TeacherRecycle.this));


                        }
                        empty_imageview.setVisibility(View.GONE);
                        no_data.setVisibility(View.GONE);
                    }

                }
            else
            {
                Title.clear();
                Question.clear();
                empty_imageview.setVisibility(View.VISIBLE);
                no_data.setVisibility(View.VISIBLE);
                recyclerView.setLayoutManager(new LinearLayoutManager(TeacherRecycle.this));
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