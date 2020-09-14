package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
    public EarthquakeAdapter(Context context, List<Earthquake> earthquakes) {
        super(context,0,earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Earthquake currentEarthquake = getItem(position);
        View listItemView = convertView;
        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.earthquake_list_item, parent, false);
        }

        TextView magnitude = (TextView) listItemView.findViewById(R.id.magnitude);
        magnitude.setText(currentEarthquake.getMagnitude());

        String location = currentEarthquake.getCity();
        int splitIndex = location.indexOf(" OF ");
        String offset;
        String primaryLocation;
        if (splitIndex != -1)
        {
            splitIndex = splitIndex + 4;
            offset = location.substring(0,splitIndex);
            primaryLocation = location.substring(splitIndex);
        }
        else
        {
            offset = "Near the";
            primaryLocation = location;
        }

        TextView offsetView = (TextView) listItemView.findViewById((R.id.offset));
        offsetView.setText(offset);

        TextView primaryLocationView = (TextView) listItemView.findViewById((R.id.primaryLocation));
        primaryLocationView.setText(primaryLocation);

        Date dateObject = new Date(currentEarthquake.getDate());
        String formattedDate = formatDate(dateObject);
        TextView date = (TextView) listItemView.findViewById((R.id.date));
        date.setText(formattedDate);

        String formattedTime = formatTime(dateObject);
        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        timeView.setText(formattedTime);


        return listItemView;
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
        return dateFormat.format(dateObject);
    }

    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }
}
