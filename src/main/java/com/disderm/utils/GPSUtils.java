package com.disderm.utils;

import java.lang.*;

public class GPSUtils {


    public static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {

        if ((lat1 == lat2) && (lon1 == lon2)) {
            //System.out.println("ERROR! eran iguales " + lat1 + " " + lat2 + " / " + lon1 + " " + lon2);
            return 0;
        } else {
            double theta = lon1 - lon2;
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;
            switch (unit) {
                case "K":
                    dist = dist * 1.609344;
                    break;
                case "N":
                    dist = dist * 0.8684;
                    break;
                case "M":
                    dist = (dist * 1.609344) * 100;
                    break;
            }
            return (dist);
        }
    }
}

