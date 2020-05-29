package com.dev.smartmonitor.business.chart.chart;

import android.content.Context;

public class ChartFactoryCreator {

    public IChartFactory getFactry(Context context) {
        return new ChartFactory(context);
    }

}
