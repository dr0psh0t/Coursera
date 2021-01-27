package com.coursera.principlessoftwaredesign.week1.interfaces;

import com.coursera.principlessoftwaredesign.week1.QuakeEntry;

public class DepthFilter implements Filter {
    private double min;
    private double max;

    public DepthFilter(double minDepth, double maxDepth) {
        min = minDepth;
        max = maxDepth;
    }

    public boolean satisfies(QuakeEntry qe) {
        return qe.getDepth() >= min && qe.getDepth() <= max;
    }

    /*
    public boolean satisfies(QuakeEntry qe) {
        return qe.getDepth() > min && qe.getDepth() < max;
    }*/

    public String getName() {
        return "DepthFilter";
    }
}
