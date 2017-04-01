package com.example.kirin.cs2340.Controller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kirin.cs2340.Model.WaterQualityReport;
import com.example.kirin.cs2340.R;

import java.util.List;

/**
 * Created by Kirin on 3/11/2017.
 * Serves as adapter to give each report a view
 */

public class WQRReportAdapter extends RecyclerView.Adapter<WQRReportAdapter.WQRViewHolder> {
    private final List<WaterQualityReport> reports;

    /**
     * List item holder that holds report view fields
     */
    public static class WQRViewHolder extends RecyclerView.ViewHolder {
        public final View v;

        /**
         * Constructor for ViewHolder
         * @param v the view to be displayed
         */
        public WQRViewHolder(View v) {
            super(v);
            this.v = v;
        }
    }

    /**
     * Constructor for report adapter
     * @param dataSet the content for the list view
     */
    public WQRReportAdapter(List<WaterQualityReport> dataSet) {
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
    public WQRReportAdapter.WQRViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.wqr_report_card, parent, false);
        return new WQRReportAdapter.WQRViewHolder(itemView);
    }

    /**
     * populates the fields in the list item view
     * @param viewHolder the list item view
     * @param i index of item in overall list that needs populated fields
     */
    public void onBindViewHolder(WQRReportAdapter.WQRViewHolder viewHolder, int i) {
        WaterQualityReport wqr = reports.get(i);
        TextView tv = (TextView) viewHolder.v.findViewById(R.id.report_id);
        tv.setText(String.format(Integer.toString(wqr.getReportId())));
        tv = (TextView) viewHolder.v.findViewById(R.id.submitter);
        tv.setText(wqr.getName());
        tv = (TextView) viewHolder.v.findViewById(R.id.location);
        tv.setText("(" + wqr.getLat() + ", " + wqr.getLng() + ")");
        tv = (TextView) viewHolder.v.findViewById(R.id.condition);
        tv.setText(wqr.getCondition().toString());
        tv = (TextView) viewHolder.v.findViewById(R.id.virus);
        tv.setText(String.format(Integer.toString(wqr.getVirusPPM())));
        tv = (TextView) viewHolder.v.findViewById(R.id.contaminant);
        tv.setText(String.format(Integer.toString(wqr.getContaminantPPM())));
        tv = (TextView) viewHolder.v.findViewById(R.id.date);
        tv.setText(wqr.getDate().toString());
    }
}
