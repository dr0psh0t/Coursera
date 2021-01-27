package com.coursera.recommendation;

import java.util.ArrayList;

public class DirectorsFilter implements Filter {
    private ArrayList<String> myDirectors;

    public DirectorsFilter(String directors) {
        myDirectors = new ArrayList<>();

        for (String dirStr : directors.split(",")) {
            myDirectors.add(dirStr.trim());
        }
    }

    @Override
    public boolean satisfies(String id) {
        String[] dirs = MovieDatabase.getDirector(id).split(",");

        for (String myDirector : myDirectors) {
            for (int k = 0; k < dirs.length; k++) {
                if (myDirector.equals(dirs[k].trim())) {
                    return true;
                }
            }
        }

        return false;
    }
}
