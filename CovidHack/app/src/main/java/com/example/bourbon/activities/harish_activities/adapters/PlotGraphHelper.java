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

    DataPoint dop[]=new DataPoint[500];
    public void plotGraph(ArrayList<Integer> dateList,ArrayList<Integer> activeList){
        int i=0;

        Collections.sort(dateList);
        Collections.sort(activeList);

        for(int x: dateList){
            for(int y:activeList){
                dop[i]=(new DataPoint(x,y));
                i+=1;
            }
        }


        LineGraphSeries < DataPoint > series = new LineGraphSeries< >(dop);
        binding.graphDaily.addSeries(series);
    }
}
