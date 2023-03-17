package com.example.aplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Student_Quiz_sub extends AppCompatActivity {
    private Cursor cursor;
    private SQLiteDatabase sqLiteDatabase;
    private SQLiteOpenHelper openHelper;
    private ImageButton stueng,stumath,stuss,stusci;
    private TextView textView,textView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_quiz_sub);

        stusci=findViewById(R.id.stusci);
        stumath=findViewById(R.id.stumath);
        stueng=findViewById(R.id.stueng);
        stuss=findViewById(R.id.stuss);
        textView=findViewById(R.id.textView_stu);
        textView1=findViewById(R.id.textclass_stu);
        openHelper = new DatabaseHelper(this);
        sqLiteDatabase = openHelper.getReadableDatabase();
        Intent intent=getIntent();
        String username=intent.getStringExtra("message_key1");
        textView.setText(username);
        Cursor abc=sqLiteDatabase.rawQuery("Select Class from student where UserName='"+textView.getText().toString()+"'",null);


        if(abc!=null && abc.getCount()>0) {
            abc.moveToFirst();
            textView1.setText(abc.getString(0));
            //String class=abc.getString(abc.getColumnIndex("Class"));

        }
        String Class=textView1.getText().toString();





        stusci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Subject="Science";
                if(textView1.getText().toString().equals("2"))
                    {
                        Intent intent= new Intent(Student_Quiz_sub.this,S_2_Sci.class);
                        intent.putExtra("message_key",username);
                        intent.putExtra("message_key1",Class);
                        intent.putExtra("message_key2",Subject);
                        startActivity(intent);
                    }
                else if(textView1.getText().toString().equals("3"))
                {
                    Intent intent= new Intent(Student_Quiz_sub.this,S_3_Sci.class);
                    intent.putExtra("message_key",username);
                    intent.putExtra("message_key1",Class);
                    intent.putExtra("message_key2",Subject);
                    startActivity(intent);
                }
                else if(textView1.getText().toString().equals("4"))
                {
                    Intent intent= new Intent(Student_Quiz_sub.this,S_4_Sci.class);
                    intent.putExtra("message_key",username);
                    intent.putExtra("message_key1",Class);
                    intent.putExtra("message_key2",Subject);
                    startActivity(intent);
                }
                else if(textView1.getText().toString().equals("5"))
                {
                    Intent intent= new Intent(Student_Quiz_sub.this,S_5_Sci.class);
                    intent.putExtra("message_key",username);
                    intent.putExtra("message_key1",Class);
                    intent.putExtra("message_key2",Subject);
                    startActivity(intent);
                }


            }
        });

        stumath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Subject="Math";
                if(textView1.getText().toString().equals("2"))
                {
                    Intent intent= new Intent(Student_Quiz_sub.this,S_2_Math.class);
                    intent.putExtra("message_key",username);
                    intent.putExtra("message_key1",Class);
                    intent.putExtra("message_key2",Subject);

                    startActivity(intent);
                }
                else if(textView1.getText().toString().equals("3"))
                {
                    Intent intent= new Intent(Student_Quiz_sub.this,S_3_Math.class);
                    intent.putExtra("message_key",username);
                    intent.putExtra("message_key1",Class);
                    intent.putExtra("message_key2",Subject);
                    startActivity(intent);
                }
                else if(textView1.getText().toString().equals("4"))
                {
                    Intent intent= new Intent(Student_Quiz_sub.this,S_4_Math.class);
                    intent.putExtra("message_key",username);
                    intent.putExtra("message_key1",Class);
                    intent.putExtra("message_key2",Subject);
                    startActivity(intent);
                }
                else if(textView1.getText().toString().equals("5"))
                {
                    Intent intent= new Intent(Student_Quiz_sub.this,S_5_Math.class);
                    intent.putExtra("message_key",username);
                    intent.putExtra("message_key1",Class);
                    intent.putExtra("message_key2",Subject);
                    startActivity(intent);
                }

            }
        });
        stueng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Subject="English";
                if(textView1.getText().toString().equals("2"))
                {
                    Intent intent= new Intent(Student_Quiz_sub.this,S_2_Eng.class);
                    intent.putExtra("message_key",username);
                    intent.putExtra("message_key1",Class);
                    intent.putExtra("message_key2",Subject);
                    startActivity(intent);
                }
else if(textView1.getText().toString().equals("3"))
                {
                    Intent intent= new Intent(Student_Quiz_sub.this,S_3_Math.class);
                    intent.putExtra("message_key",username);
                    intent.putExtra("message_key1",Class);
                    intent.putExtra("message_key2",Subject);
                    startActivity(intent);
                }
                else if(textView1.getText().toString().equals("4"))
                {
                    Intent intent= new Intent(Student_Quiz_sub.this,S_4_Math.class);
                    intent.putExtra("message_key",username);
                    intent.putExtra("message_key1",Class);
                    intent.putExtra("message_key2",Subject);
                    startActivity(intent);
                }
                else if(textView1.getText().toString().equals("5"))
                {
                    Intent intent= new Intent(Student_Quiz_sub.this,S_5_Math.class);
                    intent.putExtra("message_key",username);
                    intent.putExtra("message_key1",Class);
                    intent.putExtra("message_key2",Subject);
                    startActivity(intent);
                }

            }
        });
        stuss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Subject="SocialScience";
                if(textView1.getText().toString().equals("2"))
                {
                    Intent intent= new Intent(Student_Quiz_sub.this,S_2_Ss.class);
                    intent.putExtra("message_key",username);
                    intent.putExtra("message_key1",Class);
                    intent.putExtra("message_key2",Subject);
                    startActivity(intent);
                }
                else if(textView1.getText().toString().equals("3"))
                {
                    Intent intent= new Intent(Student_Quiz_sub.this,S_3_Ss.class);
                    intent.putExtra("message_key",username);
                    intent.putExtra("message_key1",Class);
                    intent.putExtra("message_key2",Subject);
                    startActivity(intent);
                }
                else if(textView1.getText().toString().equals("4"))
                {
                    Intent intent= new Intent(Student_Quiz_sub.this,S_4_Ss.class);
                    intent.putExtra("message_key",username);
                    intent.putExtra("message_key1",Class);
                    intent.putExtra("message_key2",Subject);
                    startActivity(intent);
                }
                else if(textView1.getText().toString().equals("5"))
                {
                    Intent intent= new Intent(Student_Quiz_sub.this,S_5_Ss.class);
                    intent.putExtra("message_key",username);
                    intent.putExtra("message_key1",Class);
                    intent.putExtra("message_key2",Subject);
                    startActivity(intent);
                }
            }
        });

    }
}