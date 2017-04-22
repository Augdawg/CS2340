package com.example.kirin.cs2340.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kirin.cs2340.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by August on 4/21/2017.
 */

public class UserArrayAdapter extends ArrayAdapter<GeneralUser> {

    private List<GeneralUser> users = new ArrayList<>();

    public UserArrayAdapter(Context context, int textViewResourceID) {
        super(context, textViewResourceID);
    }

    public void add(GeneralUser user) {
        users.add(user);
        super.add(user);
    }

    public GeneralUser getItem(int index) {
        return this.users.get(index);
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        GeneralUser user = getItem(position);
        if (user == null) {
            Toast.makeText(getContext(), "Here!", Toast.LENGTH_SHORT).show();
            return convertView;
        }
        View row = convertView;
        LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        row = inflater.inflate(R.layout.user_list_item, parent, false);
        TextView title = (TextView) row.findViewById(R.id.userEmail);
        title.setText("Email: " + user.getEmail());
        TextView banned = (TextView) row.findViewById(R.id.userBanned);
        banned.setText("Banned: " + user.getBanned());
        TextView blocked = (TextView) row.findViewById(R.id.userBlocked);
        blocked.setText("Blocked: " + user.getBlocked());
        TextView type = (TextView) row.findViewById(R.id.userType);
        if (user instanceof Admin)
            type.setText("Account type: Admin");
        else if (user instanceof Manager)
            type.setText("Account type: Manager");
        else if (user instanceof Worker)
            type.setText("Account type: Worker");
        else
            type.setText("Account type: User");

        return row;
    }
}
