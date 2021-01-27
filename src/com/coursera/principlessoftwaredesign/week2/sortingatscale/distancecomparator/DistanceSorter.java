package com.coursera.principlessoftwaredesign.week2.sortingatscale.distancecomparator;

import java.util.*;

public class DistanceSorter {

    String url = "D:\\daryll\\coursera\\src\\com\\coursera\\principlessoftwaredesign\\week2\\sortingatscale\\distancecomparator\\data\\";
    
    public void sortByDistance() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = url+"nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        //You know what would be amazing?  If the Location class documented
        //whether east or west were positive!
        Location where = new Location(35.9886, -78.9072);
        Collections.sort(list, new DistanceComparator(where));
        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }

    }

    public static void main(String[] args) {
        DistanceSorter distanceSorter = new DistanceSorter();
        distanceSorter.sortByDistance();
    }
}
