package com.example.aplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Student_Remark_view extends AppCompatActivity {
    TextView textans, textque, textcans, textscore, textremark;
    ImageView img;
    private SQLiteDatabase sqLiteDatabase;
    private SQLiteOpenHelper openHelper;
    DatabaseHelper databaseHelper;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_remark_view);

        openHelper = new DatabaseHelper(this);
        sqLiteDatabase = openHelper.getReadableDatabase();
        textscore = findViewById(R.id.textView39);
        textcans = findViewById(R.id.textView40);
        textremark = findViewById(R.id.textView38);
        textque = findViewById(R.id.textView36);
        textans = findViewById(R.id.textView37);
        img = findViewById(R.id.imageView25);

        String question = getIntent().getStringExtra("Question");
        String sub = getIntent().getStringExtra("sub");
        String Class = getIntent().getStringExtra("Class");
        String uname = getIntent().getStringExtra("Uname");
        textque.setText("Question : " + question);
        Cursor abc = sqLiteDatabase.rawQuery("Select Answer,Image,Remark,Score from Remark where Username='" + uname + "' and Question='" + question + "'and Class='" + Class + "' and Subject='" + sub + "'", null);

        while (abc.moveToNext()) {

            textans.append(abc.getString(0));
            byte[] image = (abc.getBlob(1));
            Bitmap bmp = BitmapFactory.decodeByteArray(image, 0, image.length);
            img.setImageBitmap(bmp);
            textremark.append(abc.getString(2));
            textscore.append(abc.getString(3));
        }

        if (sub.equals("English") && Class.equals("2")) {
            Cursor abc1 = sqLiteDatabase.rawQuery("Select Correctans from Class2eng where Question='" + question + "'", null);

            while (abc1.moveToNext()) {

                textcans.setText(abc1.getString(0));
            }
        } else if (sub.equals("English") && Class.equals("3")) {
            Cursor abc1 = sqLiteDatabase.rawQuery("Select Correctans from Class3eng where Question='" + question + "'", null);

            while (abc1.moveToNext()) {

                textcans.append(abc1.getString(0));
            }
        }
        else if(sub.equals("English") && Class.equals("4"))
        {
            Cursor abc1 = sqLiteDatabase.rawQuery("Select Correctans from Class4eng where Question='"+question+"'", null);

            while (abc1.moveToNext()) {

                textcans.append(abc1.getString(0));
            }
        }
        else if(sub.equals("English") && Class.equals("5"))
        {
            Cursor abc1 = sqLiteDatabase.rawQuery("Select Correctans from Class5eng where Question='"+question+"'", null);

            while (abc1.moveToNext()) {

                textcans.append(abc1.getString(0));
            }
        }


        else if (sub.equals("Math") && Class.equals("2")) {
            Cursor abc1 = sqLiteDatabase.rawQuery("Select Correctans from Class2math where Question='" + question + "'", null);

            while (abc1.moveToNext()) {

                textcans.append(abc1.getString(0));
            }
        } else if (sub.equals("Math") && Class.equals("3")) {
            Cursor abc1 = sqLiteDatabase.rawQuery("Select Correctans from Class3math where Question='" + question + "'", null);

            while (abc1.moveToNext()) {

                textcans.append(abc1.getString(0));
            }
        }
        else if(sub.equals("Math") && Class.equals("4"))
        {
            Cursor abc1 = sqLiteDatabase.rawQuery("Select Correctans from Class4math where Question='"+question+"'", null);

            while (abc1.moveToNext()) {

                textcans.append(abc1.getString(0));
            }
        }
        else if(sub.equals("Math") && Class.equals("5"))
        {
            Cursor abc1 = sqLiteDatabase.rawQuery("Select Correctans from Class5math where Question='"+question+"'", null);

            while (abc1.moveToNext()) {

                textcans.append(abc1.getString(0));
            }
        }

        else if (sub.equals("Science") && Class.equals("2")) {
            Cursor abc1 = sqLiteDatabase.rawQuery("Select Correctans from Class2Sci where Question='" + question + "'", null);

            while (abc1.moveToNext()) {

                textcans.append(abc1.getString(0));
            }
        } else if (sub.equals("Science") && Class.equals("3")) {
            Cursor abc1 = sqLiteDatabase.rawQuery("Select Correctans from Class3sci where Question='" + question + "'", null);

            while (abc1.moveToNext()) {

                textcans.append(abc1.getString(0));
            }
        }
        else if(sub.equals("Science") && Class.equals("4"))
        {
            Cursor abc1 = sqLiteDatabase.rawQuery("Select Correctans from Class4sci where Question='"+question+"'", null);

            while (abc1.moveToNext()) {

                textcans.append(abc1.getString(0));
            }
        }
        else if(sub.equals("Science") && Class.equals("5")){
            Cursor abc1 = sqLiteDatabase.rawQuery("Select Correctans from Class5sci where Question='" + question + "'", null);

            while (abc1.moveToNext()) {

                textcans.append(abc1.getString(0));
            }
        }

        else if (sub.equals("SocialScience") && Class.equals("2")) {
            Cursor abc1 = sqLiteDatabase.rawQuery("Select Correctans from Class2ss where Question='" + question + "'", null);

            while (abc1.moveToNext()) {

                textcans.append(abc1.getString(0));
            }
        } else if (sub.equals("SocialScience") && Class.equals("3")) {
            Cursor abc1 = sqLiteDatabase.rawQuery("Select Correctans from Class3ss where Question='" + question + "'", null);

            while (abc1.moveToNext()) {

                textcans.append(abc1.getString(0));
            }
        }
        else if(sub.equals("SocialScience") && Class.equals("4"))
        {
            Cursor abc1 = sqLiteDatabase.rawQuery("Select Correctans from Class4ss where Question='"+question+"'", null);

            while (abc1.moveToNext()) {

                textcans.append(abc1.getString(0));
            }
        }
        else
        {
            Cursor abc1 = sqLiteDatabase.rawQuery("Select Correctans from Class5ss where Question='"+question+"'", null);

            while (abc1.moveToNext()) {

                textcans.append(abc1.getString(0));
            }
        }

    }
}
