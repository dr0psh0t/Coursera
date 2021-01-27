package com.coursera.principlessoftwaredesign.week1.interfaces;

import com.coursera.principlessoftwaredesign.week1.QuakeEntry;

public interface Filter {
    public boolean satisfies(QuakeEntry qe);

    /*
    Modify the Filter interface to include a new method named getName that returns the
    name of the filter. The line added to the Filter interface should be:

    public String getName();

    What changes need to be made to all the Filter classes? The user should be able to
    specify what they want the name of the filter to be when they create a specific filter. For
    the MatchAllFilter class, a getName method should return a String of all the Filter
    names in its ArrayList.
     */
    public String getName();
}
