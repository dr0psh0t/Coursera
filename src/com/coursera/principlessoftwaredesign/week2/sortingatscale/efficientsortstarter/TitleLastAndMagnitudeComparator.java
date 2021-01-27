package com.coursera.principlessoftwaredesign.week2.sortingatscale.efficientsortstarter;

import java.util.Comparator;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry> {

    public int compare(QuakeEntry q1, QuakeEntry q2) {
        String q1LastWord = q1.getInfo().substring(q1.getInfo().lastIndexOf(" "));
        String q2LastWord = q2.getInfo().substring(q2.getInfo().lastIndexOf(" "));

        int res = q1LastWord.compareTo(q2LastWord);

        if (res == 0) {
            return Double.compare(q1.getMagnitude(), q2.getMagnitude());
        } else {
            return res;
        }
    }
}
