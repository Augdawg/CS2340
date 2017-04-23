package com.example.kirin.cs2340.Model;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

/**
 * Created by Kirin on 4/22/2017.
 */

public class CurrentLocation {
    private static Context context;

    private LocationManager manager;
    private android.location.LocationListener listener;

    private double currentLat;
    private double currentLng;
    private static CurrentLocation instance;

    private CurrentLocation() {
        manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        listener = new android.location.LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                currentLat = location.getLatitude();
                currentLng = location.getLongitude();
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        try {
            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0l, 0f, listener);
            manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, listener);
        } catch (SecurityException se) {
            se.printStackTrace();
        }
    }

    public static CurrentLocation getInstance(Context newContext) {
        context = newContext;
        if (instance == null) {
            instance = new CurrentLocation();
        }

        return instance;
    }

    public double getCurrentLat() {
        return currentLat;
    }

    public double getCurrentLng() {
        return currentLng;
    }
}
