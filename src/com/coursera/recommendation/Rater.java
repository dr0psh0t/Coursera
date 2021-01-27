package com.coursera.recommendation;

import java.util.ArrayList;
import java.util.HashMap;

public interface Rater {

    //ArrayList<Rating> getRatings();
    HashMap<String, Rating> getRatings();

    void addRating(String item, double rating);

    boolean hasRating(String item);

    String getID();

    double getRating(String item);

    int numRatings();

    ArrayList<String> getItemsRated();
}
