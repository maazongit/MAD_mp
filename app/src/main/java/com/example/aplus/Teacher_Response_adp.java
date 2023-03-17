package com.example.aplus;

import android.app.Activity;
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

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Teacher_Response_adp extends RecyclerView.Adapter<Teacher_Response_adp.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList Uname, Question;
    private EditText classteacher, subtecher;

    Teacher_Response_adp(Activity activity, Context context, ArrayList Uname, ArrayList Question, EditText classteacher, EditText subtecher) {
        this.activity = activity;
        this.context = context;
        this.Uname = Uname;
        this.Question = Question;
        this.classteacher = classteacher;
        this.subtecher = subtecher;
    }
        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType){
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.myrow, parent, false);
            return new MyViewHolder(view);
        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onBindViewHolder ( @NonNull final MyViewHolder holder, final int position){
            holder.title_txt1.setText(String.valueOf(Uname.get(position)));
            holder.Que_txt1.setText(String.valueOf(Question.get(position)));
            holder.class_txt1.setText(String.valueOf(classteacher.getText()));
            holder.sub_txt1.setText(String.valueOf(subtecher.getText()));
            holder.mainLayout1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(context, Teacher_Remark.class);
                    intent.putExtra("Question", String.valueOf(Question.get(position)));
                    intent.putExtra("uname", String.valueOf(Uname.get(position)));
                    intent.putExtra("class", String.valueOf(classteacher.getText()));
                    intent.putExtra("sub", String.valueOf(subtecher.getText()));
                    //intent.putExtra("author", String.valueOf(book_author.get(position)));
                    // intent.putExtra("pages", String.valueOf(book_pages.get(position)));
                    activity.startActivity(intent);
                    // activity.startActivityForResult(intent, 1);
                }
            });


        }

        @Override
        public int getItemCount () {
            return Uname.size();
        }


        class MyViewHolder extends RecyclerView.ViewHolder {

            TextView title_txt1, Que_txt1, sub_txt1, class_txt1, book_author_txt, book_pages_txt;
            ImageView imageViewdel;
            LinearLayout mainLayout1;

            MyViewHolder(@NonNull View itemView) {
                super(itemView);
                title_txt1 = itemView.findViewById(R.id.title_txt);
                Que_txt1 = itemView.findViewById(R.id.Que_txt);
                mainLayout1 = itemView.findViewById(R.id.mainLayout);
                imageViewdel = itemView.findViewById(R.id.imageViewdel);
                sub_txt1 = itemView.findViewById(R.id.sub_txt1);
                class_txt1 = itemView.findViewById(R.id.class_txt1);


                //Animate Recyclerview
                //Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
                //mainLayout.setAnimation(translate_anim);
            }

        }
    }



