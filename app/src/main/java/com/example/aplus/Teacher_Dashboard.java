package com.example.aplus;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;

public class Teacher_Dashboard extends AppCompatActivity {
    private ImageSlider imageSlider;
    private Button startbtn;
    private TextView textView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_dashboard);
        imageSlider=findViewById(R.id.imagesliderteacher);
        startbtn=findViewById(R.id.button);
        textView=findViewById(R.id.textViewteacher);
        Intent intent=getIntent();
        String username=intent.getStringExtra("message_key");
        textView.setText(username);

        ArrayList<SlideModel> slideModels = new ArrayList<>();
        //slideModels.add(slideModels(R.drawable.t1,ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.t, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.t1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.t2, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.t4, ScaleTypes.FIT));
       // slideModels.add(new SlideModel("", ScaleTypes.FIT));

        imageSlider.setImageList(slideModels,ScaleTypes.FIT);

startbtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String name=textView.getText().toString().trim();
        //Intent intent1= new Intent(getApplicationContext(),Teacher_profile.class);

        Intent intent= new Intent(getApplicationContext(),TeacherDash_1.class);
        intent.putExtra("message_key1",name);
        startActivity(intent);

    }
});


    }
}