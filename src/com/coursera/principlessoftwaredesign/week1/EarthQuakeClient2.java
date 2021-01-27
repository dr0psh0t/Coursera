package com.coursera.principlessoftwaredesign.week1;

import com.coursera.principlessoftwaredesign.week1.filteringdata.MagnitudeFilter;
import com.coursera.principlessoftwaredesign.week1.filteringdata.PhraseFilter;
import com.coursera.principlessoftwaredesign.week1.interfaces.*;

import java.lang.reflect.Array;
import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {

    private String url = "D:\\daryll\\coursera\\src\\com\\coursera\\principlessoftwaredesign\\week1\\quiz\\data\\";

    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<>();

        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        
        return answer;
    } 

    public void quakesWithFilter() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = url+"nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);

        //  filtering quake data by magnitude and depth
        /*
        Filter magnitudeFilter = new MagnitudeFilter(3.5, 4.5);
        Filter depthFilter = new DepthFilter(-55000.00, -20000.00);

        ArrayList<QuakeEntry> quakeData1 = filter(list, magnitudeFilter);
        ArrayList<QuakeEntry> quakeData2 = filter(quakeData1, depthFilter);

        for (QuakeEntry qe : quakeData2) {
            System.out.println(qe);
        }*/

        //  filtering quake data by location-distance and phrase

        Filter distanceFilter = new DistanceFilter(new Location(39.7392, -104.9903), 1000000);
        Filter phraseFilter = new PhraseFilter("end", "a");

        ArrayList<QuakeEntry> quakeData1 = filter(list, distanceFilter);
        ArrayList<QuakeEntry> quakeData2 = filter(quakeData1, phraseFilter);

        int size = 0;
        for (QuakeEntry qe : quakeData2) {
            System.out.println(qe);
            size++;
        }

        System.out.println("There are "+quakeData2.size()+" quakes.");
    }

    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        //String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(url+"nov20quakedata.atom");
        //dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
    }

    /*
    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }*/

    /*
    Write a new void method named testMatchAllFilter in the EarthQuakeClient2 class.
    This method reads in earthquake data from a source and stores them into an ArrayList of
    type QuakeEntry. Then it prints all the earthquakes and how many earthquakes that
    were from the source. You should read in earthquakes from the small file
    nov20quakedatasmall.atom, print all the earthquakes and also print how many there
    are. After this works you should comment out the printing of all the earthquakes, but
    continue to print out the total number of earthquakes read in. Then create a
    MatchAllFilter named maf and use the addFilter method to add three Filters to test the
    magnitude between 0.0 and 2.0, to test the depth between ­100000.0 and ­10000.0, and
    if the letter "a" is in the title. Then use filter(list, maf)to use all three filters and print out
    the resulting list of earthquakes. You will see the following two earthquakes meet that
    criteria:

    (33.54, -116.66), mag = 0.30, depth = -10410.00, title = 2km SE of Anza, California
    (63.25, -150.43), mag = 1.70, depth = -99900.00, title = 75km WSW of Cantwell, Alaska
     */
    public void testMatchAllFilter() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = url+"nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);

        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(1.0, 4.0));
        maf.addFilter(new DepthFilter(-180000.0, -30000.0));
        maf.addFilter(new PhraseFilter("any", "o"));

        ArrayList<QuakeEntry> newList = filter(list, maf);

        for (QuakeEntry qe : newList) {
            System.out.println(qe);
        }

        //System.out.println(maf.getName());
        System.out.println("There are "+newList.size()+" quakes.");
    }

    /*
    Write a new void method named testMatchAllFilter2 in the EarthQuakeClient2 class.
    This method should be identical to the method testMatchAllFilter, but will create
    different Filters. You should read in earthquakes from the small file
    nov20quakedatasmall.atom.Then create a MatchAllFilter named maf, and use the
    addFilter method to add three Filters to test the magnitude between 0.0 and 3.0, to test
    for the distance from Tulsa, Oklahoma at location (36.1314, ­95.9372) is less than
    10000000 meters (10000 km), and if the substring “Ca” is in the title. Then use filter(list, maf)
    to use all three filters and print out the resulting list of earthquakes. You will see the
    following seven earthquakes meet that criteria:

    (33.54, -116.66), mag = 0.30, depth = -10410.00, title = 2km SE of Anza, California
    (63.44, -147.62), mag = 1.60, depth = -7400.00, title = 66km E of Cantwell, Alaska
    (36.27, -121.66), mag = 2.00, depth = -7630.00, title = 28km SSE of Carmel Valley Village, California
    (63.25, -150.43), mag = 1.70, depth = -99900.00, title = 75km WSW of Cantwell, Alaska
    (35.00, -118.21), mag = 1.30, depth = 1010.00, title = Quarry Blast - 7km SSW of Mojave, California
    (49.39, -120.44), mag = 2.40, depth = -20.00, title = Explosion - 8km SSE of Princeton, Canada
    (34.05, -117.36), mag = 1.20, depth = 1040.00, title = Quarry Blast - 4km WNW of Grand Terrace, California
     */
    public void testMatchAllFilter2() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = url+"nov20quakedata.atom";
        ArrayList<QuakeEntry> list = parser.read(source);

        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(0.0, 5.0));
        maf.addFilter(new DistanceFilter(new Location(55.7308, 9.1153), 3000000));
        maf.addFilter(new PhraseFilter("any", "e"));

        ArrayList<QuakeEntry> newList = filter(list, maf);

        for (QuakeEntry qe : newList) {
            System.out.println(qe);
        }

        System.out.println("There are "+newList.size()+" quakes.");
    }
}
