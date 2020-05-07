package com.example.bourbon.activities.harish_activities.adapters;

import android.content.Context;
import android.view.MotionEvent;

import com.example.bourbon.databinding.ActivityGraphDailyBinding;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Collections;

import print.Print;

public class PlotGraphHelper {

    private ActivityGraphDailyBinding binding;
    private Context context;
    private Print p;
    public PlotGraphHelper(ActivityGraphDailyBinding binding,Context ctx) {
        this.binding=binding;
        context=ctx;
        p=new Print(ctx);
    }

    DataPoint dop[];
    public void plotGraph(ArrayList<Integer> dateList,ArrayList<Integer> activeList){


        Collections.sort(dateList);
        Collections.sort(activeList);


        dop=new DataPoint[dateList.size()];

        ArrayList<Entry> dop2=new ArrayList<>();

        for(int i=0;i<dateList.size();i++){
            dop[i]=new DataPoint(dateList.get(i),activeList.get(i));
            dop2.add(new Entry(dateList.get(i),activeList.get(i)));
        }





        configGraph(dop2);

        for(int i=0;i<dateList.size();i++){
            p.sprintf("x = "+dateList.get(i)+" y = "+activeList.get(i));
        }

        LineGraphSeries < DataPoint > series = new LineGraphSeries< >(dop);
//        binding.graphDaily.addSeries(series);
//
//
//        binding.graphDaily.getViewport().setMinX(dateList.get(0));
//        binding.graphDaily.getViewport().setMaxX(dateList.get(dateList.size()-1));
    }

    private void configGraph(ArrayList<Entry> dop) {

        binding.lineChart.setDragEnabled(true);
        binding.lineChart.setScaleEnabled(false);

        LineDataSet set1=new LineDataSet(dop,"Graph1");

        ArrayList<ILineDataSet> dataSets=new ArrayList<>();
        dataSets.add(set1);

        LineData data=new LineData(dataSets);

        binding.lineChart.setData(data);



    }


}
