package com.coursera.principlessoftwaredesign.week1.interfaces;

import com.coursera.principlessoftwaredesign.week1.Location;
import com.coursera.principlessoftwaredesign.week1.QuakeEntry;

public class DistanceFilter implements Filter {
    public Location location;
    public double maximumDistance;

    public DistanceFilter(Location loc, double dist) {
        location = loc;
        maximumDistance = dist;
    }

    public boolean satisfies(QuakeEntry qe) {
        return qe.getLocation().distanceTo(location) < maximumDistance;
    }

    public String getName() {
        return "Distance";
    }
}
