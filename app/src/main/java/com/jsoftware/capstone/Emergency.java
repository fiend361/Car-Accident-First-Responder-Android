package com.jsoftware.capstone;

public class Emergency {

    String emergencyLocation;
    //String latitude;
    //String longitude;

    public Emergency(String emergencyLocation, String latitude, String longitude) {
        this.emergencyLocation = emergencyLocation;
        //this.latitude = latitude;
        //this.longitude = longitude;
    }

    /*public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }*/

    public Emergency() {
    }

    public Emergency(String location) {
        this.emergencyLocation = location;
    }

    public String getEmergencyLocation() {
        return emergencyLocation;
    }

    public void setEmergencyLocation(String emergencyLocation) {
        this.emergencyLocation = emergencyLocation;
    }
}
