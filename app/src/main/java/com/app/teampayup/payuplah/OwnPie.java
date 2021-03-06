package com.app.teampayup.payuplah;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
        ownPie.setUsePercentValues(false);
        //pieChart.getDescription().setEnabled(false);
        DatabaseHelper PieData = new DatabaseHelper(this);
        Integer counter = 1;


        ownPie.setExtraOffsets(5, 10, 5 ,5);

        ownPie.setDragDecelerationFrictionCoef(0.99f);

        ownPie.setDrawHoleEnabled(true);
        ownPie.setHoleColor(Color.WHITE);
        ownPie.setTransparentCircleRadius(61f);
        ownPie.setCenterText("Owed");
        ownPie.setCenterTextSize(20);

        Cursor res = PieData.getAllOweData();// here can change to own table

        ArrayList<PieEntry> xValues = new ArrayList<>();

        while(res.moveToNext()){
            xValues.add(new PieEntry(res.getInt(5), res.getString(4)));
            //Log.d("onCreatePie", "onCreate: " + res.getInt(0));
            counter += 1;
        }
        /*while(res.moveToNext()){
            xValues.add(new PieEntry(res.getInt(1), res.getString(0)));
            Log.d("onCreatePie", "onCreate: " + res.getInt(1));
            counter += 1;
        }*/

        /*
        while(res.moveToNext()){
            xValues.add(new PieEntry(res.getInt(1), res.getString(0)));
            Log.d("onCreatePie", "onCreate: " + res.getInt(1));
            counter += 1;
        }
        */

        /*
        xValues.add(new PieEntry(34f, "James"));
        xValues.add(new PieEntry(23f, "Ruby"));
        xValues.add(new PieEntry(14f, "Jane"));
        xValues.add(new PieEntry(35, "Jimmy"));
        xValues.add(new PieEntry(40, "Kirin"));
        xValues.add(new PieEntry(50, "Illu"));

        */
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

    }


}
