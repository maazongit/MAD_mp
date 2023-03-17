package com.example.aplus;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
private EditText usernamelogin, emaillogin,passwrod;
private  Button login;
    private SQLiteDatabase sqLiteDatabase;
    private SQLiteOpenHelper openHelper;
    private Cursor cursor1,cursor2,cursor3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        openHelper = new DatabaseHelper(this);
        sqLiteDatabase = openHelper.getReadableDatabase();

        usernamelogin=findViewById(R.id.usernamelogin);
        emaillogin=findViewById(R.id.emaillogin);
        passwrod=findViewById(R.id.passwordlogin);
        login=findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            String email=emaillogin.getText().toString().trim();
            String password=passwrod.getText().toString().trim();
            String username=usernamelogin.getText().toString().trim();


                if (email.isEmpty() || password.isEmpty() || username.isEmpty())
                {
                    Toast.makeText(Login.this, "Enter your Email , UserName and Password to login", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    cursor1 = sqLiteDatabase.rawQuery("SELECT *FROM " + DatabaseHelper.TABLE_2 + " WHERE " + "UserName" + "=? AND " + "Email" + "=? AND " + "Password" + "=?", new String[]{username, email, password});
                    cursor2 = sqLiteDatabase.rawQuery("SELECT *FROM " + DatabaseHelper.TABLE_1 + " WHERE " + "UserName" + "=? AND " + "Gmail" + "=? AND " + "Password" + "=?", new String[]{username, email, password});
                    cursor3 = sqLiteDatabase.rawQuery("SELECT *FROM " + DatabaseHelper.TABLE_3 + " WHERE " + "UserName" + "=? AND " + "Email" + "=? AND " + "Password" + "=?", new String[]{username, email, password});
                    //cursor=db.rawQuery("SELECT *FROM " + DBParent.TABLE_NAME + " WHERE " +DBParent.COL_7+"=? AND "+ DBParent.COL_6 + "=? AND " + DBParent.COL_8 + "=?", new String[]{username,email, password});
                    if (cursor1 != null || cursor2 != null || cursor3 != null) {
                        if (cursor1.getCount() > 0) {
                            Intent intent= new Intent(getApplicationContext(),Student_Dashboard.class);
                            intent.putExtra("message_key",username);
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(), "Login sucess", Toast.LENGTH_SHORT).show();

                        }
                            else if(cursor2.getCount() > 0)
                            {
                                Intent intent= new Intent(getApplicationContext(),Parent_Dashboard.class);
                                intent.putExtra("message_key",username);
                                startActivity(intent);
                                Toast.makeText(getApplicationContext(), "Login sucess", Toast.LENGTH_SHORT).show();

                            }
                            else if(cursor3.getCount() > 0)
                        {
                            Intent intent= new Intent(getApplicationContext(),Teacher_Dashboard.class);

                           intent.putExtra("message_key",username);
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(), "Login sucess", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "Login error", Toast.LENGTH_SHORT).show();
                        }
                        }

                    }
                }



        });
    }
}