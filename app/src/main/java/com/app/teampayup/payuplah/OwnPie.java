package com.app.teampayup.payuplah;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class OwnPie extends AppCompatActivity {

    PieChart ownPie;
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_own_pie);

        ownPie = (PieChart) findViewById(R.id.ownPie);
        btnNext = (Button) findViewById(R.id.btnNext);
        ownPie.setUsePercentValues(true);
        //pieChart.getDescription().setEnabled(false);
        ownPie.setExtraOffsets(5, 10, 5 ,5);

        ownPie.setDragDecelerationFrictionCoef(0.99f);

        ownPie.setDrawHoleEnabled(false);
        ownPie.setHoleColor(Color.WHITE);
        ownPie.setTransparentCircleRadius(61f);

        ArrayList<PieEntry> xValues = new ArrayList<>();

        xValues.add(new PieEntry(34f, "James"));
        xValues.add(new PieEntry(23f, "Ruby"));
        xValues.add(new PieEntry(14f, "Jane"));
        xValues.add(new PieEntry(35, "Jimmy"));
        xValues.add(new PieEntry(40, "Kirin"));
        xValues.add(new PieEntry(50, "Illu"));

       /* Description description = new Description();
        description.setText("This is the fist apple pie");
        description.setTextSize(11);
        pieChart.setDescription(description);
    */
        ownPie.animateY(1000, Easing.EasingOption.EaseInOutCubic);

        PieDataSet dataSet = new PieDataSet(xValues,"People");
        //dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.PASTEL_COLORS);


        PieData data = new PieData((dataSet));
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.YELLOW);

        ownPie.setData(data);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(OwnPie.this, PieActivity2.class);//Still havent create pie 3 yet so PieActibity 2 will be there for the time being

                startActivity(in);
            }
        });
    }


}
