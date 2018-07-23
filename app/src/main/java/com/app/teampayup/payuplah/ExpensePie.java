package com.app.teampayup.payuplah;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import static android.graphics.Color.BLACK;

public class ExpensePie extends AppCompatActivity {


    PieChart pieChart;
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_pie);


        pieChart =(PieChart) findViewById(R.id.pieChart);
        btnNext = (Button) findViewById(R.id.btnNext);

        pieChart.setUsePercentValues(true);
        //pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 10, 5 ,5);

        pieChart.setDragDecelerationFrictionCoef(0.99f);

        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);

        ArrayList<PieEntry> yValues = new ArrayList<>();

        yValues.add(new PieEntry(34f, "PS4"));
        yValues.add(new PieEntry(23f, "IPhone X"));
        yValues.add(new PieEntry(14f, "Snacks"));
        yValues.add(new PieEntry(35, "Water"));
        yValues.add(new PieEntry(40, "Chicken Rice"));
        yValues.add(new PieEntry(23, "StarBucks"));


        Description description = new Description();
        description.setText("Expense");
        description.setTextSize(11);
        pieChart.setDescription(description);

        //hii
        pieChart.animateY(1000, Easing.EasingOption.EaseInOutCubic);

        PieDataSet dataSet = new PieDataSet(yValues,"Item");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);


        PieData data = new PieData((dataSet));
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.YELLOW);

        pieChart.setData(data);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(ExpensePie.this, OwnPie.class);
                startActivity(in);
            }
        });


    }
}