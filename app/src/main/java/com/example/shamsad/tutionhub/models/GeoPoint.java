package com.example.shamsad.tutionhub.models;

/**
 * Created by shamsad on 11/19/17.
 */

public class GeoPoint {
    private Double latitude;
    private Double longitude;

    public GeoPoint(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}
