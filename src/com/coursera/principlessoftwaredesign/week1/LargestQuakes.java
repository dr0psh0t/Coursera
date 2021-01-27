package com.coursera.principlessoftwaredesign.week1;

import java.lang.reflect.Array;
import java.util.*;

// used in programming exercise. all working so far
public class LargestQuakes {

    private String url = "D:\\daryll\\coursera\\src\\com\\coursera\\principlessoftwaredesign\\week1\\data\\";

    /*
    Write a void method named findLargestQuakes that reads in earthquake data from a
    source and storing them into an ArrayList of type QuakeData. Then it prints all the
    earthquakes and how many earthquakes that were from the source. You should read in
    earthquakes from the small file nov20quakedatasmall.atom, print all the earthquakes
    and also print how many there are. After this works you should comment out the printing
    of all the earthquakes, but continue to print out the total number of earthquakes read in.
     */
    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = url+"nov20quakedata.atom";

        ArrayList<QuakeEntry> largestQuakes = parser.read(source);
        ArrayList<QuakeEntry> newLargestQuakes = getLargest(largestQuakes, 50);

        for (QuakeEntry quakeEntry : newLargestQuakes) {
            System.out.println(quakeEntry);
        }

        //System.out.println("Index location of largest magnitude: "+indexOfLargest(largestQuakes));
        System.out.println("There are "+largestQuakes.size()+" quakes.");
    }

    /*
    Write a method named indexOfLargest that has one parameter, an ArrayList of type
    QuakeEntry named data. This method returns an integer representing the index location
    in data of the earthquake with the largest magnitude. You should test out this method by
    adding code to the method findLargestQuakes to print the index location of the largest
    magnitude earthquake in the file nov20quakedatasmall.atom and the earthquake at
    that location. You will see that the largest such earthquake is at location 3 and has
    magnitude 5.50. Once this works you may want to comment this out.
     */
    public int indexOfLargest(ArrayList<QuakeEntry> data) {
        int indexOfLargest = 0;
        int index = 0;
        double magnitude = 0;

        for (QuakeEntry quakeEntry : data) {
            if (magnitude < quakeEntry.getMagnitude()) {
                magnitude = quakeEntry.getMagnitude();
                indexOfLargest = index;
            }

            index++;
        }

        return indexOfLargest;
    }

    /*
    Write a method named getLargest that has two parameters, an ArrayList of type
    QuakeEntry named quakeData and an integer named howMany. This method returns
    an ArrayList of type QuakeEntry of the top howMany largest magnitude earthquakes
    from quakeData. The quakes returned should be in the ArrayList in order by their
    magnitude, with the largest magnitude earthquake in index position 0. If quakeData has
    fewer than howMany earthquakes, then the number of earthquakes returned in the
    ArrayList is equal to the number of earthquakes in quakeData.This method should call
    the method indexOfLargest
     */
    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany) {
        ArrayList<QuakeEntry> quakeDataCopy = new ArrayList<>(quakeData);
        ArrayList<QuakeEntry> ret = new ArrayList<>();
        int loopLength = 0;

        if (quakeData.size() >= howMany) {
            loopLength = howMany;
        } else {
            loopLength = quakeData.size();
        }

        for (int j = 0; j < loopLength; j++) {
            int largestIndex = indexOfLargest(quakeDataCopy);

            ret.add(quakeDataCopy.get(largestIndex));
            quakeDataCopy.remove(largestIndex);
        }

        return ret;
    }
}
