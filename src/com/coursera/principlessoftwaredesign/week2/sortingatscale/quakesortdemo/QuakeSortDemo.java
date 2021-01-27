
/**
 * Illustrate sorting
 * @author Duke Software 
 * @version 1.0
 */

package com.coursera.principlessoftwaredesign.week2.sortingatscale.quakesortdemo;

import edu.duke.*;
import java.util.*;

public class QuakeSortDemo {

    String url = "D:\\daryll\\coursera\\src\\com\\coursera\\principlessoftwaredesign\\week2\\sortingatscale\\quakesortdemo\\data\\";
    
    public void testSort(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = url+"nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        
        ArrayList<QuakeEntry> list  = parser.read(source);
        Collections.sort(list);
        
        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }
    }

    public static void main(String[] args) {
        QuakeSortDemo quakeSortDemo = new QuakeSortDemo();
        quakeSortDemo.testSort();
    }
}
