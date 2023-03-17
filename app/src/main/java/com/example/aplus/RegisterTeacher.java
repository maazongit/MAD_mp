package com.example.aplus;

import static com.example.aplus.DatabaseHelper.TABLE_3;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterTeacher extends AppCompatActivity {
    private Button submit1;
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase sqLiteDatabase;
    private EditText f1,l1,teach1,teach2,u1,p1,e1,pass1,repass1;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_teacher);
        openHelper = new DatabaseHelper(this);
        submit1 = findViewById(R.id.submit1);
        f1 = findViewById(R.id.f1);
        l1 = findViewById(R.id.l1);
        teach1 = findViewById(R.id.teach1);
        teach2 = findViewById(R.id.teach2);
        p1 = findViewById(R.id.p1);
        e1 = findViewById(R.id.e1);
        u1 = findViewById(R.id.u1);
        pass1 = findViewById(R.id.pass1);
        repass1 = findViewById(R.id.repass1);
        submit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqLiteDatabase = openHelper.getWritableDatabase();
                String uname = u1.getText().toString();
                String fname = f1.getText().toString();
                String lname = l1.getText().toString().trim();
                String std = teach1.getText().toString().trim();
                String sub = teach2.getText().toString().trim();
                String phone= p1.getText().toString();
                String femail = e1.getText().toString();
                String val = pass1.getText().toString();
                String cpass = repass1.getText().toString();
                if(!validateName() | !validatePassword() | !validatePhoneNo() | !validateEmail() | !validateUsername() | !validateConfirmpassword())
                {
                    return;
                }
                else
                {
                    insertData(fname,lname,std,sub,phone,femail,uname,val);
                    Toast.makeText(RegisterTeacher.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(RegisterTeacher.this, Login.class);
                    startActivity(intent);
                }
            }
        });

    }
    private Boolean validateUsername()
    {
        String uname = u1.getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";
        if (uname.isEmpty())
        {
            u1.setError("Field cannot be empty");
            return false;
        }
        else if (uname.length() >= 15)
        {
            u1.setError("Username too long");
            return false;
        }
        else if (!uname.matches(noWhiteSpace))
        {
            u1.setError("White Spaces are not allowed");
            return false;
        }
        else
        {
            u1.setError(null);
            //username.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateName() {
        String fname = f1.getText().toString();
        String lname = l1.getText().toString().trim();

        String val = pass1.getText().toString();
        if (fname.isEmpty() || lname.isEmpty()) {
            Toast.makeText(RegisterTeacher.this, "Please fill all the details", Toast.LENGTH_SHORT).show();

            return false;
        }
        else {
            f1.setError(null);
            //firstname.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validateEmail()
    {
        String femail = e1.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (femail.isEmpty()) {
            e1.setError("Field cannot be empty");
            return false;
        } else if (!femail.matches(emailPattern)) {
            e1.setError("Invalid email address");
            return false;
        } else {
            e1.setError(null);
            //email.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePhoneNo() {
        String phone = p1.getText().toString();
        if (phone.isEmpty())
        {
            p1.setError("Field cannot be empty");
            return false;
        }
        if(phone.length()<10)
        {
            p1.setError("Enter Correct Contact Number");
            return false;
        }
        else
        {
            p1.setError(null);
            //regPhoneNo.setErrorEnabled(false);
            return true;
        }
    }


    private Boolean validatePassword() {
        String val = pass1.getText().toString();
        String passwordVal = "^" +
                "(?=.*[0-9])" +         //at least 1 digit
                "(?=.*[a-z])" +         //at least 1 lower case letter
                "(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";
        if (val.isEmpty()) {
            pass1.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            pass1.setError("Password is too weak");
            return false;
        } else {
            pass1.setError(null);
            //regPassword.setErrorEnabled(false);
            return true;
        }
    }
    private  Boolean validateConfirmpassword()
    {
        String cpass= repass1.getText().toString();
        String val = pass1.getText().toString();
        if (!cpass.equals(val))
        {
            repass1.setError("Password did not match");
            return false;
        }
        else
        {
            repass1.setError(null);
            //regPassword.setErrorEnabled(false);
            return true;
        }
    }


    public void insertData(String fname,String lname,String std,String sub,String phone, String femail, String uname,String val){
        ContentValues values3 = new ContentValues();

        values3.put("FirstName",fname);
        values3.put("LastName",lname);
        values3.put("Class",std);
        values3.put("Subject",sub);
        values3.put("Phone",phone);
        values3.put("Email",femail);
        values3.put("UserName",uname);
        values3.put("Password",val);

        sqLiteDatabase.insert(TABLE_3,null,values3);
    }
}



