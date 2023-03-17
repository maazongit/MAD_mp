package com.example.aplus;

import android.app.Activity;
import android.content.Context;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Parent_Remark_adp extends RecyclerView.Adapter<Parent_Remark_adp.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList Subject, Question,Class;
    private String cuname;

    Parent_Remark_adp(Activity activity, Context context,  ArrayList Subject ,ArrayList Question,ArrayList Class,String cuname) {
        this.activity = activity;
        this.context = context;
        this.Subject=Subject;
        this.Question = Question;
        this.Class=Class;
        this.cuname=cuname;

    }
    @NonNull
    @Override
    public Parent_Remark_adp.MyViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.myrow, parent, false);
        return new Parent_Remark_adp.MyViewHolder(view);
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder (@NonNull final MyViewHolder holder, final int position){
        holder.title_txt1.setText(String.valueOf(Subject.get(position)));
        holder.Que_txt1.setText(String.valueOf(Question.get(position)));
        holder.class_txt1.setText(String.valueOf(Class.get(position)));
        holder.mainLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, Parent_Remark_view.class);
                intent.putExtra("Question", String.valueOf(Question.get(position)));
                intent.putExtra("sub", String.valueOf(Subject.get(position)));
                intent.putExtra("Class", String.valueOf(Class.get(position)));
                intent.putExtra("Uname", cuname);
                //intent.putExtra("author", String.valueOf(book_author.get(position)));
                // intent.putExtra("pages", String.valueOf(book_pages.get(position)));
                activity.startActivity(intent);
                // activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount () {
        return Question.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title_txt1, Que_txt1, sub_txt1, class_txt1, book_author_txt, book_pages_txt;
        ImageView imageViewdel;
        LinearLayout mainLayout1;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title_txt1 = itemView.findViewById(R.id.title_txt);
            Que_txt1 = itemView.findViewById(R.id.Que_txt);
            class_txt1=itemView.findViewById(R.id.class_txt1);
            mainLayout1 = itemView.findViewById(R.id.mainLayout);




            //Animate Recyclerview
            //Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            //mainLayout.setAnimation(translate_anim);
        }

    }
}

