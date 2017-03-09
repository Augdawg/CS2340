package com.example.kirin.cs2340.Controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kirin.cs2340.Model.WaterSourceReport;
import com.example.kirin.cs2340.R;

import java.util.List;

/**
 * Created by Kirin on 3/8/2017.
 */

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ViewHolder> {
    Context context;
    private List<WaterSourceReport> reports;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View v;
        public ViewHolder(View v) {
            super(v);
            this.v = v;
        }
    }

    public ReportAdapter(List<WaterSourceReport> dataSet) {
        this.reports = dataSet;
    }

    public int getItemCount() {
        return reports.size();
    }

    public ReportAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.report_card, parent, false);
        return new ReportAdapter.ViewHolder(itemView);
    }

    public void onBindViewHolder(ReportAdapter.ViewHolder viewHolder, int i) {
        WaterSourceReport wsr = reports.get(i);
        TextView tv = (TextView) viewHolder.v.findViewById(R.id.reportid);
        tv.setText(Integer.toString(wsr.getReportNumber()));
        tv = (TextView) viewHolder.v.findViewById(R.id.submitter);
        tv.setText(wsr.getName());
        tv = (TextView) viewHolder.v.findViewById(R.id.location);
        tv.setText("(" + wsr.getLat() + ", " + wsr.getLng() + ")");
        tv = (TextView) viewHolder.v.findViewById(R.id.condition);
        tv.setText(wsr.getWaterCondition());
        tv = (TextView) viewHolder.v.findViewById(R.id.type);
        tv.setText(wsr.getWaterType());
    }
}
