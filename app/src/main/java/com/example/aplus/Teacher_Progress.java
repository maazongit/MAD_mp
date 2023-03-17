package com.example.aplus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;

public class Teacher_Progress extends AppCompatActivity {
    private BarChart barChart;
    private EditText uname;
    private Button view;
    private Cursor cursor;
    private SQLiteDatabase sqLiteDatabase;
    private SQLiteOpenHelper openHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_progress);
        barChart = (BarChart) findViewById(R.id.barChart);
        uname = findViewById(R.id.editTextTextPersonName2);
        view = findViewById(R.id.button3);
        openHelper = new DatabaseHelper(this);
        sqLiteDatabase = openHelper.getReadableDatabase();
        ArrayList<Integer> colors=new ArrayList<Integer>();
        colors.add(ContextCompat.getColor(this,R.color.peach));
        colors.add(ContextCompat.getColor(this,R.color.blue));
        colors.add(ContextCompat.getColor(this,R.color.yellow));
        colors.add(ContextCompat.getColor(this,R.color.green));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                queryXData();
                queryYData();
                final ArrayList<BarEntry> yvalue = new ArrayList<>();
                final ArrayList<String> ydata = queryYData();

                for (int i = 0; i < queryYData().size(); i++) {

                    BarEntry newBarEntry = new BarEntry(i, Float.parseFloat(queryYData().get(i)));
                    yvalue.add(newBarEntry);
                }
                final ArrayList<String> xvalue = new ArrayList<>();
                final ArrayList<String> xdata = queryXData();
                for (int i = 0; i < queryXData().size(); i++) {
                    xvalue.add(xdata.get(i));
                }
                BarDataSet dataSet = new BarDataSet(yvalue,"Subjects");

                dataSet.setColors(colors);
                ArrayList<IBarDataSet> dataSets1=new ArrayList<>();
                dataSets1.add(dataSet);
                BarData data=new BarData(dataSets1);
                barChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xvalue));
                barChart.setData(data);
                XAxis xAxis=barChart.getXAxis();
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                xAxis.setDrawLabels(true);
                xAxis.isCenterAxisLabelsEnabled();
                xAxis.setGranularityEnabled(true);
                YAxis rightAxis=barChart.getAxisRight();
                rightAxis.setEnabled(false);
                barChart.setMaxVisibleValueCount(5);
                barChart.setFitBars(true);
            }
        });
    }

    public ArrayList<String> queryXData() {
        sqLiteDatabase = openHelper.getWritableDatabase();
        ArrayList<String> XData = new ArrayList<String>();
        Cursor abc = sqLiteDatabase.rawQuery("Select Subject from Remark where Username='" + uname.getText().toString() + "' GROUP BY Subject", null);
        for (abc.moveToFirst(); !abc.isAfterLast(); abc.moveToNext()) {
            XData.add(abc.getString(0));
        }
        //abc.close();
        return XData;
    }

    public ArrayList<String> queryYData() {
        sqLiteDatabase = openHelper.getWritableDatabase();
        ArrayList<String> YData = new ArrayList<String>();
        Cursor abc = sqLiteDatabase.rawQuery("Select SUM (Score) from Remark where Score IS NOT NULL  and Username='" + uname.getText().toString() + "'GROUP BY Subject", null);

        for (abc.moveToFirst(); !abc.isAfterLast(); abc.moveToNext()) {
            YData.add(abc.getString(0));
        }
        //abc.close();
        return YData;
    }
    ;


}