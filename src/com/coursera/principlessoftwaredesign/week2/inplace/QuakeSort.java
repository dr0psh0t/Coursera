package com.coursera.principlessoftwaredesign.week2.inplace;

import java.util.*;

public class QuakeSort {
    private String url = "D:\\daryll\\coursera\\src\\com\\coursera\\principlessoftwaredesign\\week2\\inplace\\data\\";



    /*
    Write the method getLargestDepth that has two parameters, an ArrayList of type
    QuakeEntry named quakeData and an int named from representing an index position in
    the ArrayList. This method returns an integer representing the index position of the
    QuakeEntry with the largest depth considering only those QuakeEntry’s from position
    from to the end of the ArrayList
     */
    public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from) {
        int minIdx = from;

        //  this for loop will sort list from smallest to highest depth
        //for (int i = from +1; i < quakeData.size(); i++) {

        //  this for loop will sort list from highest to smallest depth
        for (int i = from; i >= 0; i--) {
            //System.out.println(quakeData.get(minIdx).getDepth()+" > "+quakeData.get(i).getDepth());
            if (quakeData.get(minIdx).getDepth() > quakeData.get(i).getDepth()) {
                minIdx = i;
            }
        }

        //System.out.println("largestdepth= "+quakeData.get(minIdx).getDepth());
        return minIdx;
    }

    /*
    Write the void method sortByLargestDepth that has one parameter, an ArrayList of
    type QuakeEntry named in. This method sorts the QuakeEntry’s in the ArrayList by
    depth using the selection sort algorithm, but in reverse order from largest depth to
    smallest depth (the QuakeEntry with the largest depth should be in the 0th position in the
    ArrayList). This method should call the method getLargestDepth repeatedly until the
    ArrayList is sorted.
     */
    public void sortByLargestDepth(ArrayList<QuakeEntry> in) {
        //  this for loop will sort list from highest to smallest depth

        int pass = 1;
        for(int i = in.size() - 1; i >= 0; i--) {
            //find the index of the smallest quake
            //int minIdx = getSmallestMagnitude(in, i);
            int maxIdx = getLargestDepth(in, i);
            //swap the ith quake with the maxIdxth quake
            QuakeEntry qi = in.get(i);
            QuakeEntry qMax = in.get(maxIdx);

            in.set(i, qMax);
            in.set(maxIdx, qi);

            System.out.println("Printing Quakes after pass "+pass);
            for (QuakeEntry quakeEntry : in) {
                System.out.println(quakeEntry);
            }
            System.out.println("last earthquake depth = "+in.get(in.size()-1).getDepth());

            pass++;
        }

        /*
        //  this for loop will sort list from smallest to highest depth
        int pass = 1;
        for(int i = 0; i < in.size(); i++) {
            //find the index of the smallest quake
            //int minIdx = getSmallestMagnitude(in, i);
            int maxIdx = getLargestDepth(in, i);
            //swap the ith quake with the maxIdxth quake
            QuakeEntry qi = in.get(i);
            QuakeEntry qMax = in.get(maxIdx);

            in.set(i, qMax);
            in.set(maxIdx, qi);

            System.out.println("Printing Quakes after pass "+pass);
            for (QuakeEntry quakeEntry : in) {
                System.out.println(quakeEntry);
            }

            pass++;
        }*/
    }

    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i = from +1; i < quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
        //count from 0 to < in.size()
        int i;
        for(i = 0; i < in.size(); i++) {
            /* find the index of the smallest quake*/
            int minIdx = getSmallestMagnitude(in, i);
            /* swap the ith quake with the minIdxth quake */
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i, qmin);
            in.set(minIdx, qi);

            System.out.println("Printing Quakes after pass "+(i+1));
            for (QuakeEntry quakeEntry : in) {
                System.out.println(quakeEntry);
            }

            if (checkInSortedOrder(in)) {
                break;
            }
        }
    }

    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted) {
        for (int i = 0; i < numSorted; i++) {
            //  not in order
            if (quakeData.get(i).getMagnitude() > quakeData.get(i+1).getMagnitude()) {

                QuakeEntry q1 = quakeData.get(i);
                QuakeEntry q2 = quakeData.get(i+1);

                quakeData.set(i, q2);
                quakeData.set(i+1, q1);
            }
        }
    }

    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> in) {
        for(int i = 0; i < in.size(); i++) {
            onePassBubbleSort(in, in.size() - 1);

            System.out.println("Printing Quakes after pass "+i);
            for (QuakeEntry quakeEntry : in) {
                System.out.println(quakeEntry);
            }
            System.out.println();
        }
    }

    public boolean checkInSortedOrder(ArrayList<QuakeEntry> quakes) {
        for (int i = 0; i < quakes.size(); i++) {
            for (int k = i+1; k < quakes.size(); k++) {

                //System.out.println(quakes.get(i).getMagnitude()+" > "+quakes.get(k).getMagnitude()
                        //+" = "+(quakes.get(i).getMagnitude() > quakes.get(k).getMagnitude()));

                if (quakes.get(i).getMagnitude() > quakes.get(k).getMagnitude()) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean checkInSortedOrder2(ArrayList<QuakeEntry> quakes) {
        for (int i = 0; i < quakes.size(); i++) {
            if ((i+1) < quakes.size()) {
                if (quakes.get(i).getMagnitude() > quakes.get(i + 1).getMagnitude()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> in) {
        /*
        int pass = 0;
        while (!checkInSortedOrder(in)) {
            onePassBubbleSort(in, in.size() - 1);

            System.out.println("Printing Quakes after pass "+pass);
            for (QuakeEntry quakeEntry : in) {
                System.out.println(quakeEntry);
            }

            System.out.println();
            pass++;
        }*/
        System.out.println("\nsize = "+in.size()+"\n");

        int pass = 1;
        while (!checkInSortedOrder2(in)) {
            onePassBubbleSort(in, in.size() - 1);

            System.out.println("Printing Quakes after pass "+pass);
            for (QuakeEntry quakeEntry : in) {
                System.out.println(quakeEntry);
            }

            System.out.println();
            pass++;
        }
    }

    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {
        sortByMagnitude(in);
    }

    /* tester method to use in BlueJ */
    public void testSort(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = url+"nov20quakedatasmall.atom";
        //String source = url+"nov20quakedata.atom";
        //String source = url+"earthquakeDataSampleSix1.atom";
        //String source = url+"earthquakeDataSampleSix2.atom";
        String source = url+"earthQuakeDataDec6sample1.atom";
        //String source = url+"earthQuakeDataDec6sample2.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);

        //sortByMagnitude(list);
        //sortByLargestDepth(list);
        /*
        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }

        System.out.println(checkInSortedOrder(list));*/

        System.out.println("read data for quakes ");
        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }

        //sortByMagnitudeWithBubbleSort(list);
        //sortByMagnitudeWithBubbleSortWithCheck(list);
        sortByMagnitudeWithCheck(list);
        //sortByLargestDepth(list);

        System.out.println("Earthquakes in sorted order: ");
        for(QuakeEntry qe: list) {
            System.out.println(qe);
        }

        //System.out.println(checkInSortedOrder(list));
    }

    public static void main(String[] args) {
        QuakeSort quakeSort = new QuakeSort();
        quakeSort.testSort();
    }
}
