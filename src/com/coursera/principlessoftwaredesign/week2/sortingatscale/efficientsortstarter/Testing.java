package com.coursera.principlessoftwaredesign.week2.sortingatscale.efficientsortstarter;

public class Testing {

    public static void main(String[] args) {
        DifferentSorters differentSorters = new DifferentSorters();
        //differentSorters.sortWithCompareTo();
        //differentSorters.sortByTitleAndDepth();
        differentSorters.sortByLastWordInTitleThenByMagnitude();
    }
}
