package com.ivan.newtechnologies.distance;

public class Main {

    public static void main(String[] args) {
        System.out.println(distance(32.9, -96.8, 29.4, -98.5));
    }

    public static double distance(final double lat1, final double lon1, final double lat2, final double lon2) {
        final double theta = lon1 - lon2;
        final double dist = Math.sin(degToRad(lat1)) * Math.sin(degToRad(lat2)) + Math.cos(degToRad(lat1)) * Math.cos(degToRad(lat2)) * Math.cos(degToRad(theta));
        return radToDeg(Math.acos(dist)) * 60 * 1.1515* 1.609344;
    }

    private static double degToRad(final double deg) {
        return (deg * Math.PI / 180.0);
    }

    private static double radToDeg(final double rad) {
        return (rad * 180.0 / Math.PI);
    }

}
