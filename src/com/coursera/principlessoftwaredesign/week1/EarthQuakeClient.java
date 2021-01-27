package com.coursera.principlessoftwaredesign.week1;

import java.util.*;
import edu.duke.*;

// used in programming exercise. all working so far
public class EarthQuakeClient {

    private String url = "D:\\daryll\\coursera\\src\\com\\coursera\\principlessoftwaredesign\\week1\\data\\";

    /*
    Write the method filterByMagnitudethat has already been started for you. This method
    has two parameters, an ArrayList of type QuakeEntry named quakeData,and a double
    named magMin. This method should return an ArrayList of type QuakeEntry of all the
    earthquakes from quakeDatathat have a magnitude larger than magMin. Notice that we
    have already created an ArrayList named answer for you to store those earthquakes
    that satisfy this requirement.
    */
    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData, double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<>();

        for (QuakeEntry qe : quakeData) {
            if (qe.getMagnitude() > magMin) {
                answer.add(qe);
            }
        }
        return answer;
    }

    /*
    Write the method filterByDistanceFrom that has already been started for you. This
    method has three parameters, an ArrayList of type QuakeEntry named quakeData,a
    double named distMax, and a Location named from. This method should return an
    ArrayList of type QuakeEntry of all the earthquakes from quakeData that are less than
    distMax from the location from. Notice that we have already created an ArrayList
    named answer for you to store those earthquakes that satisfy this requirement.
    */
    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData, double distMax, Location from) {      
        ArrayList<QuakeEntry> answer = new ArrayList<>();

        for (QuakeEntry qe : quakeData) {
            if (qe.getLocation().distanceTo(from) < distMax) {
                answer.add(qe);
            }
        }
        return answer;
    }
            
    public void dumpCSV(ArrayList<QuakeEntry> list){
		System.out.println("Latitude,Longitude,Magnitude,Info");
		for(QuakeEntry qe : list){
			System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
			                  qe.getLocation().getLatitude(),
			                  qe.getLocation().getLongitude(),
			                  qe.getMagnitude(),
			                  qe.getInfo());
	    }
		
	}

	/*
    Modify the method bigQuakes that has no parameters to use filterByMagnitude and
    print earthquakes above a certain magnitude, and also print the number of such
    earthquakes. Currently this method reads data on earthquakes from a file, stores a
    QuakeEntry for each earthquake read in the ArrayList named list, and prints out the
    number of earthquakes read in. After making modifications, when you run your program
    on the file nov20quakedatasmall.atom for quakes larger than 5.0, you should get the
    output:
    read data for 25 quakes
            (26.38, 142.71), mag = 5.50, depth = -12890.00, title = 91km SSE of Chichi-shima, Japan
            (-11.63, 165.52), mag = 5.10, depth = -20700.00, title = 106km SSW of Lata, Solomon Islands
            (-24.67, -175.93), mag = 5.10, depth = -10000.00, title = South of Tonga
    Found 3 quakes that match that criteria
    */
	public void bigQuakes() {
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedata.atom";

        EarthQuakeParser parser = new EarthQuakeParser();
        String source = url+"nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for " + list.size() + " quakes");

        ArrayList<QuakeEntry> listBig = filterByMagnitude(list, 5.0);
        for (QuakeEntry qe : listBig) {
           System.out.println(qe); 
        }
	}


    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = url+"nov20quakedata.atom";
        String source = url+"nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }

    /*
    Modify the method closeToMe that has no parameters to call filterByDistance to print
    out the earthquakes within 1000 Kilometers to a specified city (such as Durham, NC).
    For each earthquake found, print the distance from the earthquake to the specified city,
    followed by the information about the city (use getInfo). Currently this method reads
    data on earthquakes from a URL, stores a QuakeEntry for each earthquake read in the
    ArrayList named list, and prints out the number of earthquakes read in. It also gives the
    location for two cities, Durham, NC (35.988, ­78.907) and Bridgeport, CA (38.17,
    ­118.82). After making modifications, when you run your program on the file
    nov20quakedatasmall.atom for the city location Durham, NC, no earthquakes are
    found. But if you then run the program for the city location Bridgeport, CA, seven
    earthquakes are found, and you should get the output:

    read data for 25 quakes
    549.2146875 2km SE of Anza, California
    329.17615625 28km SSE of Carmel Valley Village, California
    225.440046875 44km SW of Beatty, Nevada
    356.428375 Quarry Blast - 7km SSW of Mojave, California
    176.09775 16km WNW of Fernley, Nevada
    210.82540625 18km WNW of Beatty, Nevada
    475.94878125 Quarry Blast - 4km WNW of Grand Terrace, California
    Found 7 quakes that match that criteria
     */
    public void closeToMe() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = url+"nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("# quakes read: " + list.size());
        
        //Durham, NC
        //Location city = new Location(35.988, -78.907);
        //Bridgeport, CA
        Location city = new Location(38.17, -118.82);
        ArrayList<QuakeEntry> close = filterByDistanceFrom(list, 1000*1000, city);
        for (int k=0; k< close.size(); k++) {
            QuakeEntry entry = close.get(k);
            double distanceInMeters = city.distanceTo(entry.getLocation());
            System.out.println(distanceInMeters/1000 + " " + entry.getInfo());
        }
    }

    /*
    Write the method filterByDepth that has three parameters, an ArrayList of type
    QuakeEntry named quakeData, a double named minDepth and a double named
    maxDepth. This method should return an ArrayList of type QuakeEntry of all the
    earthquakes from quakeData whose depth is between minDepth and maxDepth,
    exclusive. (Do not include quakes with depth exactly minDepth or maxDepth.
     */
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeEntries, double minDepth, double maxDepth) {
        ArrayList<QuakeEntry> newQuakeEntries = new ArrayList<>();

        for (QuakeEntry quakeEntry : quakeEntries) {
            if (quakeEntry.getDepth() > minDepth && quakeEntry.getDepth() < maxDepth) {
                newQuakeEntries.add(quakeEntry);
            }
        }
        return newQuakeEntries;
    }

    /*
    Write the void method quakesOfDepth that has no parameters to use filterByDepth
    and print all the earthquakes from a data source whose depth is between a given
    minimum and maximum value. You should also print out the number of earthquakes
    found. After writing this method, when you run your program on the file
    nov20quakedatasmall.atom for quakes with depth between ­10000.0 and ­5000.0 you
    should find five such quakes and get the output:

    read data for 25 quakes
    Find quakes with depth between -10000.0 and -5000.0
    (63.44, -147.62), mag = 1.60, depth = -7400.00, title = 66km E of Cantwell, Alaska
    (36.27, -121.66), mag = 2.00, depth = -7630.00, title = 28km SSE of Carmel Valley Village, California
    (36.65, -117.13), mag = -0.20, depth = -9300.00, title = 44km SW of Beatty, Nevada
    (39.69, -119.41), mag = 0.00, depth = -7600.00, title = 16km WNW of Fernley, Nevada
    (37.00, -116.94), mag = -0.10, depth = -6700.00, title = 18km WNW of Beatty, Nevada
    Found 5 quakes that match that criteria
     */
    public void quakesOfDepth() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = url+"nov20quakedata.atom";

        ArrayList<QuakeEntry> quakeEntries = parser.read(source);
        ArrayList<QuakeEntry> quakesOfDepthEntries = filterByDepth(quakeEntries, -4000.0, -2000.0);

        for (QuakeEntry quakeEntry : quakesOfDepthEntries) {
            System.out.println(quakeEntry);
        }

        System.out.println("There are "+quakesOfDepthEntries.size()+" quakes.");
    }

    /*
    Write the method filterByPhrase that has three parameters, an ArrayList of type
    QuakeEntry named quakeData, a String named where that indicates where to search in
    the title and has one of three values: ("start", "end", or "any"), and a String named
    phrase, indicating the phrase to search for in the title of the earthquake. The title of the
    earthquake can be obtained through the getInfo method. The filterByPhrase method
    should return an ArrayList of type QuakeEntry of all the earthquakes from quakeData
    whose titles have the given phrase found at location where("start" means the phrase
    must start the title, "end" means the phrase must end the title and "any" means the
    phrase is a substring anywhere in the title.)
     */
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase) {
        ArrayList<QuakeEntry> newQuakeEntries = new ArrayList<>();

        if (where.equals("end")) {
            for (QuakeEntry quakeEntry : quakeData) {
                if (quakeEntry.getInfo().lastIndexOf(phrase) != -1) {
                    newQuakeEntries.add(quakeEntry);
                }
            }
        }

        if (where.equals("start")) {
            for (QuakeEntry quakeEntry : quakeData) {
                if (quakeEntry.getInfo().startsWith(phrase)) {
                    newQuakeEntries.add(quakeEntry);
                }
            }
        }

        if (where.equals("any")) {
            for (QuakeEntry quakeEntry : quakeData) {
                if (quakeEntry.getInfo().contains(phrase)) {
                    newQuakeEntries.add(quakeEntry);
                }
            }
        }

        return newQuakeEntries;
    }

    /*
    Write the void method quakesByPhrase to use filterByPhrase and print all the
    earthquakes from a data source that have phrase in their title in a given position in the
    title. You should also print out the number of earthquakes found. After writing this
    method, when you run your program on the file nov20quakedatasmall.atom for quakes
    with phrase "California" and where set to "end" you should find four such quakes and
    get the output:
    read data for 25 quakes

            (33.54, -116.66), mag = 0.30, depth = -10410.00, title = 2km SE of Anza, California
            (36.27, -121.66), mag = 2.00, depth = -7630.00, title = 28km SSE of Carmel Valley Village, California
            (35.00, -118.21), mag = 1.30, depth = 1010.00, title = Quarry Blast - 7km SSW of Mojave, California
            (34.05, -117.36), mag = 1.20, depth = 1040.00, title = Quarry Blast - 4km WNW of Grand Terrace, California

    Found 4 quakes that match California at end
    */
    public void quakesByPhrase() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = url+"nov20quakedata.atom";

        ArrayList<QuakeEntry> quakeEntries = parser.read(source);
        //ArrayList<QuakeEntry> quakesByPhraseEntries = filterByPhrase(quakeEntries, "end", "California");
        //ArrayList<QuakeEntry> quakesByPhraseEntries = filterByPhrase(quakeEntries, "start", "Explosion");
        ArrayList<QuakeEntry> quakesByPhraseEntries = filterByPhrase(quakeEntries, "any", "Can");

        for (QuakeEntry quakeEntry : quakesByPhraseEntries) {
            System.out.println(quakeEntry);
        }

        System.out.println("There are "+quakesByPhraseEntries.size()+" quakes.");
    }
}
