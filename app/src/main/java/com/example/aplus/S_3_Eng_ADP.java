package com.example.aplus;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

public class S_3_Eng_ADP extends RecyclerView.Adapter<S_3_Eng_ADP.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList Title, Question;
    private String uname,class1,sub;

    S_3_Eng_ADP(Activity activity, Context context, ArrayList Title, ArrayList Question,String uname,String class1,String sub) {
        this.activity = activity;
        this.context = context;
        this.Title = Title;
        this.Question=Question;
        this.uname=uname;
        this.class1=class1;
        this.sub=sub;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.myrow, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.title_txt.setText(String.valueOf(Title.get(position)));
        holder.Que_txt.setText(String.valueOf(Question.get(position)));
        holder.class_txt1.setText(String.valueOf(class1));
        holder.sub_txt1.setText(String.valueOf(sub));
        holder.uname_txt.setText(String.valueOf(uname));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, S_3_Eng_Ques.class);
                intent.putExtra("Question", String.valueOf(Question.get(position)));
                intent.putExtra("Subject", String.valueOf(sub));
                intent.putExtra("class", String.valueOf(class1));
                intent.putExtra("uname", String.valueOf(uname));

                //intent.putExtra("title", String.valueOf(book_title.get(position)));
                //intent.putExtra("author", String.valueOf(book_author.get(position)));
                // intent.putExtra("pages", String.valueOf(book_pages.get(position)));
                activity.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return Title.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title_txt, Que_txt,sub_txt1,class_txt1,uname_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title_txt = itemView.findViewById(R.id.title_txt);
            Que_txt=itemView.findViewById(R.id.Que_txt);
            sub_txt1=itemView.findViewById(R.id.sub_txt1);
            class_txt1=itemView.findViewById(R.id.class_txt1);
            uname_txt=itemView.findViewById(R.id.uname_txt1);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }
}

