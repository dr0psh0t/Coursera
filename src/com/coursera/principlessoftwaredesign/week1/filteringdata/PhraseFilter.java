package com.coursera.principlessoftwaredesign.week1.filteringdata;

import com.coursera.principlessoftwaredesign.week1.QuakeEntry;
import com.coursera.principlessoftwaredesign.week1.interfaces.Filter;

public class PhraseFilter implements Filter {
    private String where;
    private String phrase;

    public PhraseFilter(String wh, String ph) {
        where = wh;
        phrase = ph;
    }

    public boolean satisfies(QuakeEntry qe) {
        if (where.equals("start")) {
            return qe.getInfo().startsWith(phrase);
        } else if (where.equals("end")) {
            return qe.getInfo().lastIndexOf(phrase) != -1;
        } else if (where.equals("any")) {
            return qe.getInfo().contains(phrase);
        }
        return false;
    }

    public String getName() {
        return "PhraseFilter";
    }
}
