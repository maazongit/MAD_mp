package com.example.aplus;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;

public class S_4_Ss_Ques extends AppCompatActivity {

    public static final String TABLE_20="Response";

    private SQLiteDatabase sqLiteDatabase;
    private SQLiteOpenHelper openHelper;
    TextView title, txtque, textcount;
    RadioButton op1, op2, op3;
    RadioGroup radiogroup;
    ImageView s2engimage;
    FloatingActionButton s2engfab;
    Button s2engubmit;
    public int counter=120;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s4_ss_ques);
        openHelper = new DatabaseHelper(this);
        sqLiteDatabase = openHelper.getReadableDatabase();
        title = findViewById(R.id.txttitle);
        op1 = findViewById(R.id.op1);
        op2 = findViewById(R.id.op2);
        op3 = findViewById(R.id.op3);
        s2engubmit=findViewById(R.id.s2engsubmit);
        radiogroup=findViewById(R.id.radioGroup);
        s2engimage=findViewById(R.id.s2engimage);

        s2engubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqLiteDatabase = openHelper.getWritableDatabase();
                String uname=getIntent().getStringExtra("uname");
                String class1=getIntent().getStringExtra("class");
                String sub=getIntent().getStringExtra("Subject");
                String ques=txtque.getText().toString();
                Bitmap Image=((BitmapDrawable)s2engimage.getDrawable()).getBitmap();
                ByteArrayOutputStream bos =new ByteArrayOutputStream();
                Image.compress(Bitmap.CompressFormat.PNG,100,bos);
                byte[] bArray= bos.toByteArray();
                String ans=((RadioButton) findViewById(radiogroup.getCheckedRadioButtonId())).getText().toString();
                String str="CREATE TABLE IF NOT EXISTS "+TABLE_20+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,Username TEXT,Class TEXT,Subject TEXT,Question TEXT,Answer TEXT,Image Image)";
                sqLiteDatabase.execSQL(str);
                insertData(uname,class1,sub,ques,ans,bArray);
                Toast.makeText(S_4_Ss_Ques.this, "Response submitted Successful", Toast.LENGTH_SHORT).show();


            }
        });
        textcount = findViewById(R.id.textcont);
        txtque = findViewById(R.id.txtque);
        s2engimage=findViewById(R.id.s2engimage);
        s2engfab=findViewById(R.id.s2engfab);
        s2engfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(S_4_Ss_Ques.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });

        //Intent intent=getIntent();
        String titles = getIntent().getStringExtra("Question");
        //Getting Data from Intent
        // String titles = getIntent().getStringExtra("title");


        //Setting Intent Data
        txtque.setText(titles);
        Cursor abc = sqLiteDatabase.rawQuery("Select Title,Option1,Option2,Option3 from Class4ss where Question='" + txtque.getText().toString() + "'", null);

        //while(cursor.moveToNext())
        if (abc.moveToFirst()) {

            title.setText(abc.getString(0));
            op1.setText(abc.getString(1));
            op2.setText(abc.getString(2));
            op3.setText(abc.getString(3));


        }

        new CountDownTimer(120000, 1000) {
            public void onTick(long millisUntilFinished) {
                textcount.setText(String.valueOf(counter));
                counter--;
            }

            public  void onFinish(){
                textcount.setText("FINISH!!");
                finish();
            }
        }.start();
    }
    public void insertData (String uname, String class1, String sub, String ques, String ans, byte[] bArray) {
        ContentValues values20 = new ContentValues();

        values20.put("Username", uname);
        values20.put("Class", class1);
        values20.put("Subject", sub);
        values20.put("Question", ques);
        values20.put("Answer", ans);
        values20.put("Image", bArray);


        sqLiteDatabase.insert(TABLE_20, null, values20);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Uri uri= data.getData();
        s2engimage.setImageURI(uri);

    }
}