package com.example.weatherpredictor;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GeocodingUtils {
    private static final String TAG = "GeocodingUtil";
    public static String getCityFromCoordinates(Context context, double latitude, double longitude) {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        String cityName = "";

        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses != null && !addresses.isEmpty()) {
                Address address = addresses.get(0);
                cityName = address.getSubAdminArea();
            }
        } catch (IOException e) {
            Log.e(TAG, "Error geocoding coordinates: " + e.getMessage());
        }

        return cityName;
    }
}