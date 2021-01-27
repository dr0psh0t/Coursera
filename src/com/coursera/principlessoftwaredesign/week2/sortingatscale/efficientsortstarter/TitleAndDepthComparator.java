package com.coursera.principlessoftwaredesign.week2.sortingatscale.efficientsortstarter;

import java.util.Comparator;

public class TitleAndDepthComparator implements Comparator<QuakeEntry> {

    public int compare(QuakeEntry q1, QuakeEntry q2) {
        int res = q1.getInfo().compareTo(q2.getInfo());

        if (res == 0) {
            return Double.compare(q1.getDepth(), q2.getDepth());
        } else {
            return res;
        }
    }
}
