package com.example.aplus;

import static com.example.aplus.DatabaseHelper.TABLE_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.Bundle;
import android.widget.Toast;

public class RegisterParent extends AppCompatActivity {
    private Button btnsubmit;
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase sqLiteDatabase;
    private EditText firstname,lastname,childsname,contact,email,username,password,confirmpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_parent);
        openHelper = new DatabaseHelper(this);
        btnsubmit = findViewById(R.id.btnsubmit);
        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        childsname = findViewById(R.id.childsname);
        contact = findViewById(R.id.contact);
        email = findViewById(R.id.email);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        confirmpassword = findViewById(R.id.retypepassword);

DatabaseHelper databaseHelper;



        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqLiteDatabase = openHelper.getWritableDatabase();
                String uname = username.getText().toString();
                String fname = firstname.getText().toString();
                String lname = lastname.getText().toString().trim();
                String cname = childsname.getText().toString().trim();
                String cpass= confirmpassword.getText().toString();
                String femail = email.getText().toString();
                String val = password.getText().toString();
                String phone = contact.getText().toString();
                if(!validateName() | !validatePassword() | !validatePhoneNo() | !validateEmail() | !validateUsername() | !validateConfirmpassword())
                {
                    return;
                }
                else
                {
                    insertData(fname,lname,cname,phone,femail,uname,val);
                    Toast.makeText(RegisterParent.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(RegisterParent.this,Login.class);
                    startActivity(intent);
                }

            }
        });

    }




        private Boolean validateUsername()
    {
            String uname = username.getText().toString();
            String noWhiteSpace = "\\A\\w{4,20}\\z";
            if (uname.isEmpty())
            {
                username.setError("Field cannot be empty");
                return false;
            }
            else if (uname.length() >= 15)
            {
                username.setError("Username too long");
                return false;
            }
            else if (!uname.matches(noWhiteSpace))
            {
                username.setError("White Spaces are not allowed");
                return false;
            }
            else
            {
                username.setError(null);
                //username.setErrorEnabled(false);
                return true;
            }
        }

    private Boolean validateName() {
        String fname = firstname.getText().toString();
        String lname = lastname.getText().toString().trim();
        String cname = childsname.getText().toString().trim();
        String val = password.getText().toString();
        if (fname.isEmpty() || lname.isEmpty()|| cname.isEmpty()) {
            Toast.makeText(RegisterParent.this, "Please fill all the details", Toast.LENGTH_SHORT).show();

            return false;
        }
        else {
            firstname.setError(null);
            //firstname.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validateEmail()
    {
        String femail = email.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (femail.isEmpty()) {
            email.setError("Field cannot be empty");
            return false;
        } else if (!femail.matches(emailPattern)) {
            email.setError("Invalid email address");
            return false;
        } else {
            email.setError(null);
            //email.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePhoneNo() {
        String phone = contact.getText().toString();
        if (phone.isEmpty())
        {
            contact.setError("Field cannot be empty");
            return false;
        }
        if(phone.length()<10)
        {
            contact.setError("Enter Correct Contact Number");
            return false;
        }
        else
        {
            contact.setError(null);
            //regPhoneNo.setErrorEnabled(false);
            return true;
        }
    }


    private Boolean validatePassword() {
        String val = password.getText().toString();
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
            password.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            password.setError("Password is too weak");
            return false;
        } else {
            password.setError(null);
            //regPassword.setErrorEnabled(false);
            return true;
        }
    }
private  Boolean validateConfirmpassword()
{
    String cpass= confirmpassword.getText().toString();
    String val = password.getText().toString();
    if (!cpass.equals(val))
    {
        confirmpassword.setError("Password did not match");
        return false;
    }
      else
    {
        confirmpassword.setError(null);
        //regPassword.setErrorEnabled(false);
        return true;
    }
}

    public void insertData(String fname,String lname,String cname,String phone, String femail, String uname,String val) {
        //ContentValues contentValues = new ContentValues();
        ContentValues values1 = new ContentValues();

        values1.put("FirstName",fname);
        values1.put("LastName",lname);
        values1.put("Childsname",cname);
        values1.put("Phone",phone);
        values1.put("Gmail",femail);
        values1.put("UserName",uname);
        values1.put("PassWord",val);

        sqLiteDatabase.insert(TABLE_1,null,values1);

    }

}