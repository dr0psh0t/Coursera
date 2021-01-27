package com.coursera.principlessoftwaredesign.week1;

import java.util.ArrayList;

// used in programming exercise. all working so far
public class ClosestQuakes {

    private String url = "D:\\daryll\\coursera\\src\\com\\coursera\\principlessoftwaredesign\\week1\\data\\";

    /*
    Complete the method getClosest that has already been started for you. This method
    has three parameters, an ArrayList of type QuakeEntry named quakeData,a Location
    named current, and an int named howMany. This method should find the closest
    number of howMany earthquakes to the current Location and return them in an
    ArrayList of type QuakeEntry. The earthquakes should be in the ArrayList in order with
    the closest earthquake in index position 0. If there are fewer then howMany
    earthquakes in quakeData,then the ArrayList returned would be the same size as
    quakeData
     */
    public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> quakeData,
                                            Location currentLocation, int howMany) {

        ArrayList<QuakeEntry> quakeDataCopy = new ArrayList<>(quakeData);
        ArrayList<QuakeEntry> ret = new ArrayList<>();
        int minIndex = 0;

        for (int j = 0; j < howMany; j++) {
            for (int k = 1; k < quakeDataCopy.size(); k++) {
                QuakeEntry quake = quakeDataCopy.get(k);

                Location newLocation = quake.getLocation();
                Location minLocation = quakeDataCopy.get(minIndex).getLocation();

                //  if newLocation to jakarta is less than minimum location to jakarta,
                //  or if new quake location is less than closest quake location we've seen so far.
                //  assign k index to minIndex to update
                if (newLocation.distanceTo(currentLocation) < minLocation.distanceTo(currentLocation)) {
                    minIndex = k;
                }
            }

            ret.add(quakeDataCopy.get(minIndex));
            quakeDataCopy.remove(minIndex);
        }

        return ret;
    }

    /*
    The first method you need has already been written for you. The method
    findClosestQuakes reads in data on earthquakes storing them in the ArrayList list and
    prints how many quakes there are. It sets a location variable named jakarta to the
    location of the city Jakarta. It then calls the method getClosest to determine the ten
    closest earthquakes in list and prints information about those quakes and how close they
    are to Jakarta. This method has already been written for you, but doesn’t work yet since
    the method getClosest is not complete.
        */
    public void findCloseQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = url+"nov20quakedata.atom";
        String source = url+"nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        System.out.println("read data for "+list.size());

        Location jakarta = new Location(-6.11, 106.845);
        ArrayList<QuakeEntry> close = getClosest(list, jakarta, 10);

        for (int k = 0; k < close.size(); k++) {
            QuakeEntry entry = close.get(k);
            double distanceInMeters = jakarta.distanceTo(entry.getLocation());
            System.out.printf("%4.2f\t %s\n", (distanceInMeters/1000), entry);
        }

        System.out.println("number found: "+close.size());
    }
}
