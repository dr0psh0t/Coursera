package com.coursera.principlessoftwaredesign.week1.interfaces;

import com.coursera.principlessoftwaredesign.week1.QuakeEntry;

import java.util.ArrayList;

/*
Write the class MatchAllFilter that implements Filter. This class has a private ArrayList
of Filters that is created in the constructor that has no parameters. This class has two
methods, 1) a void method named addFilter with one Filter parameter that adds the
Filter to its private ArrayList, and 2) a method named satisfies that has one QuakeEntry
parameter and returns true if the QuakeEntry satisfies all the filter conditions, otherwise it
returns false.
 */
public class MatchAllFilter implements Filter {
    private ArrayList<Filter> filters;

    public MatchAllFilter() {
        filters = new ArrayList<>();
    }

    public void addFilter(Filter f) {
        filters.add(f);
    }

    public boolean satisfies(QuakeEntry qe) {
        for (Filter f : filters) {
            if (!f.satisfies(qe)) {
                return false;
            }
        }

        return true;
    }

    public String getName() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Filters usd are: ");

        for (Filter filter : filters) {
            stringBuilder.append(filter.getName()+" ");
        }

        return stringBuilder.toString();
    }
}