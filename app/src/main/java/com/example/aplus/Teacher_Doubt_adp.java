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

public class Teacher_Doubt_adp extends RecyclerView.Adapter<Teacher_Doubt_adp.MyViewHolder>{
    private Context context;
    private Activity activity;
    private ArrayList Question,UnameStudent;
    private String name;

    Teacher_Doubt_adp(Activity activity, Context context, ArrayList UnameStudent, ArrayList Question,String name) {
        this.activity = activity;
        this.context = context;
        this.UnameStudent=UnameStudent;
        this.Question=Question;
        this.name=name;
}

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.myrow4, parent, false);
        return new MyViewHolder(view);
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final Teacher_Doubt_adp.MyViewHolder holder, final int position) {
        holder.title_txt.setText(String.valueOf(UnameStudent.get(position)));
        holder.Que_txt.setText(String.valueOf(Question.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TtoSreply.class);
                intent.putExtra("Question", String.valueOf(Question.get(position)));
                intent.putExtra("UnameParent", String.valueOf(UnameStudent.get(position)));
                intent.putExtra("name", String.valueOf(name));
                //intent.putExtra("author", String.valueOf(book_author.get(position)));
                // intent.putExtra("pages", String.valueOf(book_pages.get(position)));
                activity.startActivity(intent);



            }
        });


    }

    @Override
    public int getItemCount() {
        return Question.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title_txt, Que_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title_txt = itemView.findViewById(R.id.title_txt);
            Que_txt=itemView.findViewById(R.id.Que_txt);
            // sub_txt1=itemView.findViewById(R.id.sub_txt1);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}

