package com.example.aplus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class Teacher_Remark extends AppCompatActivity {
    public static final String TABLE_24="Remark";
    TextView textuname,textque,textclass,textsub,textans;
    ImageView img;
    Button btn;
    private SQLiteDatabase sqLiteDatabase;
    private SQLiteOpenHelper openHelper;
    DatabaseHelper databaseHelper;
    Context context;
    EditText txtremark,txtscore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_remark);
        openHelper = new DatabaseHelper(this);
        sqLiteDatabase = openHelper.getReadableDatabase();
        textuname=findViewById(R.id.textView26);
        textclass=findViewById(R.id.textView27);
        textsub=findViewById(R.id.textView28);
        textque=findViewById(R.id.textView29);
        textans=findViewById(R.id.textView30);
        img=findViewById(R.id.imageView22);
        btn=findViewById(R.id.button2);
        txtremark=findViewById(R.id.editTextTextPersonName);
        txtscore=findViewById(R.id.editTextNumber);
        String question= getIntent().getStringExtra("Question");
        String uname= getIntent().getStringExtra("uname");
        String class1=getIntent().getStringExtra("class");
        String sub=getIntent().getStringExtra("sub");
        textuname.setText("Name : "+uname);
        textque.setText("Question : "+question);
        textsub.setText("Subject : "+sub);
        textclass.setText("Class :"+class1);
        Cursor abc = sqLiteDatabase.rawQuery("Select Answer,Image from Response where Question='"+question+"'and Username='"+uname+"'", null);

            while (abc.moveToNext()) {

                textans.setText(abc.getString(0));
                byte[]image =(abc.getBlob(1));
                Bitmap bmp= BitmapFactory.decodeByteArray(image, 0 , image.length);
                img.setImageBitmap(bmp);
            }
btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String answer=textans.getText().toString();
        String remark=txtremark.getText().toString();
        String score=txtscore.getText().toString();
        Bitmap Image=((BitmapDrawable)img.getDrawable()).getBitmap();
        ByteArrayOutputStream bos =new ByteArrayOutputStream();
        Image.compress(Bitmap.CompressFormat.PNG,100,bos);
        byte[] bArray= bos.toByteArray();
        String str="CREATE TABLE IF NOT EXISTS "+TABLE_24+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,Username TEXT,Class TEXT,Subject TEXT,Question TEXT,Answer TEXT,Image Image,Remark TEXT,Score Text)";
        sqLiteDatabase.execSQL(str);
        insertData(uname,class1,sub,question,answer,bArray,remark,score);
        Toast.makeText(Teacher_Remark.this, "submitted Successful", Toast.LENGTH_SHORT).show();


    }
});
    }
    public void insertData (String uname, String class1, String sub, String question, String answer, byte[] bArray,String remark,String score) {
        ContentValues values24 = new ContentValues();

        values24.put("Username", uname);
        values24.put("Class", class1);
        values24.put("Subject", sub);
        values24.put("Question", question);
        values24.put("Answer", answer);
        values24.put("Image", bArray);
        values24.put("Remark", remark);
        values24.put("Score", score);


        sqLiteDatabase.insert(TABLE_24, null, values24);
    }
}