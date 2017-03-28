package com.example.kirin.cs2340.Controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.kirin.cs2340.Model.DB.DBHandler;
import com.example.kirin.cs2340.Model.DB.WSRDBHandler;
import com.example.kirin.cs2340.Model.WaterQualityReport;
import com.example.kirin.cs2340.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Kirin on 3/23/2017.
 * Represents the wqr graph activity
 */

public class QualityGraphActivity extends AppCompatActivity {
    private GraphView graph;
    private EditText yearBound;
    private EditText lowerLatBound;
    private EditText upperLatBound;
    private EditText lowerLngBound;
    private EditText upperLngBound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quality_graph);
        graph = (GraphView) findViewById(R.id.graph);
        yearBound = (EditText) findViewById(R.id.year_input);
        lowerLatBound = (EditText) findViewById(R.id.low_lat_bound);
        upperLatBound = (EditText) findViewById(R.id.upper_lat_bound);
        lowerLngBound = (EditText) findViewById(R.id.low_lng_bound);
        upperLngBound = (EditText) findViewById(R.id.upper_lng_bound);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        graph.addSeries(series);
        graph.getGridLabelRenderer().setHorizontalAxisTitle("Month Num");
        graph.getGridLabelRenderer().setVerticalAxisTitle("# Reports");
        graph.setScaleX(1);
    }

    /**
     * Gives relevant data to the graph
     * @param v the current view
     */
    public void updateGraph(View v) {
        graph.removeAllSeries();
        WSRDBHandler db = new WSRDBHandler(getApplicationContext());
        List<WaterQualityReport> wqr = db.getAllWQRReports();
        int[] data = new int[12];
        double lowLat = Double.parseDouble(lowerLatBound.getText().toString());
        double highLat = Double.parseDouble(upperLatBound.getText().toString());
        double lowLng = Double.parseDouble(lowerLngBound.getText().toString());
        double highLng = Double.parseDouble(upperLngBound.getText().toString());
        int year = Integer.parseInt(yearBound.getText().toString());
        for (WaterQualityReport report : wqr) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(report.getDate());
            int yr = cal.get(Calendar.YEAR);
            if (report.getLat() >= lowLat && report.getLat() <= highLat
                    && report.getLng() >= lowLng && report.getLng() <= highLng
                    && year == yr) {
                data[cal.get(Calendar.MONTH) - 1]++;
            }
        }
        DataPoint[] dp = new DataPoint[data.length];
        int dpCounter = 0;
        for (int i = 0; i < data.length; i++) {
            dp[dpCounter++] = new DataPoint(i + 1, data[i]);
            //graph.addSeries(series);
        }

        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(dp);
        graph.addSeries(series);
        graph.setScaleX(1);
        graph.getViewport().setMinX(1);
        graph.getViewport().setMaxX(12);
        graph.getViewport().setXAxisBoundsManual(true);
        int x = 0;
    }
}
