package com.example.kirin.cs2340.Controller;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kirin.cs2340.Model.ActivityLog;
import com.example.kirin.cs2340.Model.CurrentUser;
import com.example.kirin.cs2340.Model.LogType;
import com.example.kirin.cs2340.Model.Manager;
import com.example.kirin.cs2340.Model.WaterSourceReport;
import com.example.kirin.cs2340.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

/**
 * Created by Kirin on 3/8/2017.
 * List adapter for report view
 */

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ViewHolder> {
    private final List<WaterSourceReport> reports;
    private ViewSourceActivity context;
    private DatabaseReference database = FirebaseDatabase.getInstance().getReference();
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
        final WaterSourceReport wsr = reports.get(i);
        viewHolder.v.setLongClickable(true);
        final ViewSourceActivity context = this.context;
        if (CurrentUser.getInstance().getCurrentUser() instanceof Manager) {
            viewHolder.v.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Manager Action");
                    builder.setMessage("Delete this report?");
                    builder.setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    builder.setPositiveButton("Delete",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    ActivityLog log = new ActivityLog();
                                    log.setId1(CurrentUser.getInstance().getCurrentUser().getId());
                                    log.setType(LogType.REPORT_DELETE);
                                    log.setId2(wsr.getReportNumber());
                                    database.getRoot().child("Security Log").push().setValue(log);
                                    dialog.cancel();
                                }
                            });
                    builder.create().show();
                    return true;
                }
            });
        }
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
    public void setContext(ViewSourceActivity context) {
        this.context = context;
    }
}
