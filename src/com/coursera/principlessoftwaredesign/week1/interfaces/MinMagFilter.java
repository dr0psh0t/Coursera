package com.coursera.principlessoftwaredesign.week1.interfaces;

import com.coursera.principlessoftwaredesign.week1.QuakeEntry;

public class MinMagFilter implements Filter {
    private double magMin;

    public MinMagFilter(double min) {
        magMin = min;
    }

    public boolean satisfies(QuakeEntry qe) {
        return qe.getMagnitude() > magMin;
    }

    public String getName() {
        return "MinMagFilter";
    }
}