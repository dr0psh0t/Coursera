package com.coursera.principlessoftwaredesign.week1.data;

import com.coursera.principlessoftwaredesign.week1.ClosestQuakes;
import com.coursera.principlessoftwaredesign.week1.EarthQuakeClient;
import com.coursera.principlessoftwaredesign.week1.EarthQuakeClient2;
import com.coursera.principlessoftwaredesign.week1.LargestQuakes;

public class QuakeTest {

    public static void main(String[] args) {
        //EarthQuakeClient quakeClient = new EarthQuakeClient();
        //quakeClient.createCSV();
        //quakeClient.bigQuakes();
        //quakeClient.closeToMe();
        //quakeClient.quakesOfDepth();
        //quakeClient.quakesByPhrase();

        //ClosestQuakes closestQuakes = new ClosestQuakes();
        //closestQuakes.findCloseQuakes();

        //LargestQuakes largestQuakes = new LargestQuakes();
        //largestQuakes.findLargestQuakes();


        EarthQuakeClient2 quakeClient2 = new EarthQuakeClient2();
        //quakeClient2.createCSV();
        quakeClient2.quakesWithFilter();
        //quakeClient2.testMatchAllFilter();
        //quakeClient2.testMatchAllFilter2();
    }
}
