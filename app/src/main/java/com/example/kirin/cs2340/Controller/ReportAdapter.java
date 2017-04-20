package com.example.kirin.cs2340.Controller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kirin.cs2340.Model.WaterSourceReport;
import com.example.kirin.cs2340.R;

import java.util.List;

/**
 * Created by Kirin on 3/8/2017.
 * List adapter for report view
 */

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ViewHolder> {
    private final List<WaterSourceReport> reports;

    /**
     * List item holder that holds report view fields
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View v;

        /**
         * Constructor for ViewHolder
         * @param v the view to be displayed
         */
        public ViewHolder(View v) {
            super(v);
            this.v = v;
        }
    }

    /**
     * Constructor for report adapter
     * @param dataSet the content for the list view
     */
    public ReportAdapter(List<WaterSourceReport> dataSet) {
        this.reports = dataSet;
    }

    /**
     * Gets number of items in list
     * @return number of items in list
     */
    public int getItemCount() {
        return reports.size();
    }

    /**
     * Creates list item view
     * @param parent the parent of the list item
     * @param viewType the type of view
     * @return new list item view
     */
    public ReportAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.report_card, parent, false);
        return new ReportAdapter.ViewHolder(itemView);
    }

    /**
     * populates the fields in the list item view
     * @param viewHolder the list item view
     * @param i index of item in overall list that needs populated fields
     */
    public void onBindViewHolder(ReportAdapter.ViewHolder viewHolder, int i) {
        WaterSourceReport wsr = reports.get(i);
        TextView tv = (TextView) viewHolder.v.findViewById(R.id.report_id);
        tv.setText(String.format(Integer.toString(wsr.getReportNumber())));
        tv = (TextView) viewHolder.v.findViewById(R.id.submitter);
        tv.setText(wsr.getName());
        tv = (TextView) viewHolder.v.findViewById(R.id.location);
        tv.setText("(" + wsr.getLat() + ", " + wsr.getLng() + ")");
        tv = (TextView) viewHolder.v.findViewById(R.id.condition);
        tv.setText(wsr.getCondition().toString());
        tv = (TextView) viewHolder.v.findViewById(R.id.type);
        tv.setText(wsr.getType().toString());
        tv = (TextView) viewHolder.v.findViewById(R.id.date);
        tv.setText(wsr.getDate().toString());
    }
}
