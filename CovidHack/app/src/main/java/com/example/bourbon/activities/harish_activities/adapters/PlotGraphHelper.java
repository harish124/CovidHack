package com.example.bourbon.activities.harish_activities.adapters;

import android.app.Activity;

import com.example.bourbon.databinding.ActivityGraphDailyBinding;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class PlotGraphHelper {

    private ActivityGraphDailyBinding binding;
    public PlotGraphHelper(ActivityGraphDailyBinding binding) {
        this.binding=binding;
    }

    DataPoint dop[];
    public void plotGraph(ArrayList<Integer> dateList,ArrayList<Integer> activeList){


        Collections.sort(dateList);
        Collections.sort(activeList);


        dop=new DataPoint[dateList.size()];


        for(int i=0;i<dateList.size();i++){
            dop[i]=new DataPoint(dateList.get(i),activeList.get(i));
        }


        LineGraphSeries < DataPoint > series = new LineGraphSeries< >(dop);
        binding.graphDaily.addSeries(series);


        binding.graphDaily.getViewport().setMinX(dateList.get(0));
        binding.graphDaily.getViewport().setMaxX(dateList.get(dateList.size()-1));
    }
}
