package com.example.aplus;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Student_Concern_adp extends RecyclerView.Adapter<Student_Concern_adp.MyViewHolder> {
    private Context context;
    private Activity activity;
    private ArrayList Question,UnameStudent, UnameTeacher,Reply;

    Student_Concern_adp(Activity activity, Context context, ArrayList UnameStudent, ArrayList Question,ArrayList UnameTeacher,ArrayList Reply) {
        this.activity = activity;
        this.context = context;
        this.UnameStudent = UnameStudent;
        this.Question = Question;
        this.UnameTeacher = UnameTeacher;
        this.Reply = Reply;
    }
        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.myrow2, parent, false);
            return new MyViewHolder(view);
        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onBindViewHolder(@NonNull final Student_Concern_adp.MyViewHolder holder, final int position) {
            holder.uname_parent_txt.setText(String.valueOf(UnameStudent.get(position)));
            holder.Que_txt.setText(String.valueOf(Question.get(position)));
            holder.uname_teacher.setText(String.valueOf(UnameTeacher.get(position)));
            holder.response_teacher.setText(String.valueOf(Reply.get(position)));
            holder.parentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //intent.putExtra("title", String.valueOf(book_title.get(position)));
                    //intent.putExtra("author", String.valueOf(book_author.get(position)));
                    // intent.putExtra("pages", String.valueOf(book_pages.get(position)));

                }
            });


        }

        @Override
        public int getItemCount() {
            return UnameStudent.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            TextView uname_parent_txt, Que_txt,uname_teacher,response_teacher,uname_txt;
            LinearLayout parentLayout;

            MyViewHolder(@NonNull View itemView) {
                super(itemView);
                uname_parent_txt = itemView.findViewById(R.id.uname_parent_txt);
                Que_txt=itemView.findViewById(R.id.Que_txt);
                // sub_txt1=itemView.findViewById(R.id.sub_txt1);
                response_teacher=itemView.findViewById(R.id.response_teacher);
                uname_teacher=itemView.findViewById(R.id.uname_teacher);
                parentLayout = itemView.findViewById(R.id.parentLayout);
                //Animate Recyclerview
                Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
                parentLayout.setAnimation(translate_anim);
            }

        }

    }