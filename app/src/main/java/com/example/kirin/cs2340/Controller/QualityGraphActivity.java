package com.example.kirin.cs2340.Controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.kirin.cs2340.Model.DB.DBHandler;
import com.example.kirin.cs2340.Model.DB.WSRDBHandler;
import com.example.kirin.cs2340.Model.WaterQualityReport;
import com.example.kirin.cs2340.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Kirin on 3/23/2017.
 * Represents the wqr graph activity
 */

public class QualityGraphActivity extends AppCompatActivity {
    private GraphView graph;
    private EditText yearBound;
    private RadioGroup dataType;
    private EditText latitude;
    private EditText longitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quality_graph);
        graph = (GraphView) findViewById(R.id.graph);
        yearBound = (EditText) findViewById(R.id.year_input);
        dataType = (RadioGroup) findViewById(R.id.graph_input);
        latitude = (EditText) findViewById(R.id.lat);
        longitude = (EditText) findViewById(R.id.lng);
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
        double halfMileCoordinate = 0.00725;
        WSRDBHandler db = new WSRDBHandler(getApplicationContext());
        List<WaterQualityReport> wqr = db.getAllWQRReports();
        int[] data = new int[12];
        double lowLat = Double.parseDouble(latitude.getText().toString()) - halfMileCoordinate;
        double highLat = Double.parseDouble(latitude.getText().toString()) + halfMileCoordinate;
        double lowLng = Double.parseDouble(longitude.getText().toString()) - halfMileCoordinate;
        double highLng = Double.parseDouble(longitude.getText().toString()) + halfMileCoordinate;
        int year = Integer.parseInt(yearBound.getText().toString());
        String selection = ((RadioButton)findViewById(dataType.getCheckedRadioButtonId())).getText().toString();

        Map<Integer, Integer> dp = new HashMap<>();
        for (int i = 1; i < 13; i++) {
            dp.put(i, 0);
        }
        int[] amounts = new int[12];
        if (selection.equals("VIRUS")) {
            graph.getGridLabelRenderer().setVerticalAxisTitle("VirusPPM");
            for (WaterQualityReport r : wqr) {
                Calendar c = Calendar.getInstance();
                c.setTime(r.getDate());
                int yr = c.get(Calendar.YEAR);
                if (r.getLat() >= lowLat && r.getLat() <= highLat
                        && r.getLng() >= lowLng && r.getLng() <= highLng
                        &&  year == yr) {
                    dp.put(c.get(Calendar.MONTH) + 1, dp.get(c.get(Calendar.MONTH) + 1) + r.getVirusPPM());
                    amounts[c.get(Calendar.MONTH)]++;
                }
            }
        } else if (selection.equals("CONTAMINANT")) {
            graph.getGridLabelRenderer().setVerticalAxisTitle("ContaminantPPM");
            for (WaterQualityReport r : wqr) {
                Calendar c = Calendar.getInstance();
                c.setTime(r.getDate());
                int yr = c.get(Calendar.YEAR);
                if (r.getLat() >= lowLat && r.getLat() <= highLat
                        && r.getLng() >= lowLng && r.getLng() <= highLng
                        &&  year == yr) {
                    dp.put(c.get(Calendar.MONTH) + 1, dp.get(c.get(Calendar.MONTH) + 1) + r.getContaminantPPM());
                    amounts[c.get(Calendar.MONTH)]++;
                }
            }
        }
        for (int i = 1; i < 13; i++) {
            if (amounts [i - 1] != 0) {
                int amt = dp.get(i) / amounts[i - 1];
                dp.put(i, amt);
            } else {
                dp.put(i, 0);
            }
        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]
                {
                new DataPoint(1, dp.get(1)),
                new DataPoint(2, dp.get(2)),
                new DataPoint(3, dp.get(3)),
                new DataPoint(4, dp.get(4)),
                new DataPoint(5, dp.get(5)),
                new DataPoint(6, dp.get(6)),
                new DataPoint(7, dp.get(7)),
                new DataPoint(8, dp.get(8)),
                new DataPoint(9, dp.get(9)),
                new DataPoint(10, dp.get(10)),
                new DataPoint(11, dp.get(11)),
                new DataPoint(12, dp.get(12))
                }
        );
        graph.addSeries(series);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMaxX(12);
    }
}
