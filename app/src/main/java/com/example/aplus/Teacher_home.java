package com.example.aplus;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class Teacher_home extends Fragment {
ImageView imageView ,imageview1;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
View rootview=inflater.inflate(R.layout.fragment_teacher_home, container, false);
        imageView= (ImageView) rootview.findViewById(R.id.imageView11);
        imageview1=(ImageView)rootview.findViewById(R.id.imageView12) ;
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(),TeacherRecycle.class);
                startActivity(intent);
            }
        });
        imageview1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(),TeacherResponse.class);
                startActivity(intent);
            }
        });

        // Inflate the layout for this fragment
        return rootview;

    }

}