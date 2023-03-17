package com.example.aplus;

import androidx.appcompat.app.AppCompatActivity;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Student_Dashboard extends AppCompatActivity {

    private ImageSlider imageSlider;
    private Button startbtn;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);
        imageSlider=findViewById(R.id.imagesliderstudent);
        startbtn=findViewById(R.id.stubuttonstart);
        textView=findViewById(R.id.textViewstudent);
        Intent intent=getIntent();
        String username=intent.getStringExtra("message_key");
        textView.setText(username);
        ArrayList<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.s, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.ss1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.s2, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.s3, ScaleTypes.FIT));

        // slideModels.add(new SlideModel("", ScaleTypes.FIT));

        imageSlider.setImageList(slideModels,ScaleTypes.FIT);

        startbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=textView.getText().toString().trim();
                Intent intent= new Intent(getApplicationContext(),StudentDash_1.class);
                intent.putExtra("message_key1",username);
                startActivity(intent);
            }
        });


    }
}