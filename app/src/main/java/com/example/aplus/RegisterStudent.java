package com.example.aplus;

import static com.example.aplus.DatabaseHelper.TABLE_2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterStudent extends AppCompatActivity {
    private Button submit2;
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase sqLiteDatabase;
    private EditText f2,l2,class2,u2,p2,e2,pass2,repass2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_student);
        openHelper = new DatabaseHelper(this);
        submit2 = findViewById(R.id.submit2);
        f2 = findViewById(R.id.f2);
        l2 = findViewById(R.id.l2);
        class2 = findViewById(R.id.class2);
        p2 = findViewById(R.id.p2);
        e2 = findViewById(R.id.e2);
        u2 = findViewById(R.id.u2);
        pass2 = findViewById(R.id.pass2);
        repass2 = findViewById(R.id.repass2);

        submit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqLiteDatabase = openHelper.getWritableDatabase();
                String uname = u2.getText().toString();
                String fname = f2.getText().toString();
                String lname = l2.getText().toString().trim();
                String std = class2.getText().toString().trim();
                String phone= p2.getText().toString();
                String femail = e2.getText().toString();
                String val = pass2.getText().toString();
                String cpass = repass2.getText().toString();
                if(!validateName() | !validatePassword() | !validatePhoneNo() | !validateEmail() | !validateUsername() | !validateConfirmpassword())
                {
                    return;
                }
                else
                {
                    insertData(fname,lname,std,phone,femail,uname,val);
                    Toast.makeText(RegisterStudent.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(RegisterStudent.this, Login.class);
                    startActivity(intent);
                }
            }
        });

    }
    private Boolean validateUsername()
    {
        String uname = u2.getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";
        if (uname.isEmpty())
        {
            u2.setError("Field cannot be empty");
            return false;
        }
        else if (uname.length() >= 15)
        {
            u2.setError("Username too long");
            return false;
        }
        else if (!uname.matches(noWhiteSpace))
        {
            u2.setError("White Spaces are not allowed");
            return false;
        }
        else
        {
            u2.setError(null);
            //username.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateName() {
        String fname = f2.getText().toString();
        String lname = l2.getText().toString().trim();

        String val = pass2.getText().toString();
        if (fname.isEmpty() || lname.isEmpty()) {
            Toast.makeText(RegisterStudent.this, "Please fill all the details", Toast.LENGTH_SHORT).show();

            return false;
        }
        else {
            f2.setError(null);
            //firstname.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validateEmail()
    {
        String femail = e2.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (femail.isEmpty()) {
            e2.setError("Field cannot be empty");
            return false;
        } else if (!femail.matches(emailPattern)) {
            e2.setError("Invalid email address");
            return false;
        } else {
            e2.setError(null);
            //email.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePhoneNo() {
        String phone = p2.getText().toString();
        if (phone.isEmpty())
        {
            p2.setError("Field cannot be empty");
            return false;
        }
        if(phone.length()<10)
        {
            p2.setError("Enter Correct Contact Number");
            return false;
        }
        else
        {
            p2.setError(null);
            //regPhoneNo.setErrorEnabled(false);
            return true;
        }
    }


    private Boolean validatePassword() {
        String val = pass2.getText().toString();
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
            pass2.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            pass2.setError("Password is too weak");
            return false;
        } else {
            pass2.setError(null);
            //regPassword.setErrorEnabled(false);
            return true;
        }
    }
    private  Boolean validateConfirmpassword()
    {
        String cpass= repass2.getText().toString();
        String val = pass2.getText().toString();
        if (!cpass.equals(val))
        {
            repass2.setError("Password did not match");
            return false;
        }
        else
        {
            repass2.setError(null);
            //regPassword.setErrorEnabled(false);
            return true;
        }
    }


    public void insertData(String fname,String lname,String std, String phone,String femail,String uname ,String val){
        //SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        //sqLiteDatabase=SQLiteOpenHelper.g
        //ContentValues values1 = new ContentValues();
        ContentValues values2 = new ContentValues();

        values2.put("Firstname",fname);
        values2.put("LastName",lname);
        values2.put("Class",std);
        values2.put("Phone",phone);
        values2.put("Email",femail);
        values2.put("Username",uname);
        values2.put("Password",val);
        sqLiteDatabase.insert(TABLE_2,null,values2);

    }
}