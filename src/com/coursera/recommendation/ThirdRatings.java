package com.coursera.recommendation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ThirdRatings {

    private ArrayList<EfficientRater> myRaters;

    public ThirdRatings() {
        //this(MovieDatabase.PATH+"ratings_short.csv");
        this(MovieDatabase.PATH+"ratings.csv");
    }

    public ThirdRatings(String ratingsFile) {
        FirstRatings firstRatings = new FirstRatings();
        myRaters = firstRatings.loadRaters(ratingsFile);
    }

    public int getRaterSize() {
        return myRaters.size();
    }

    public void howManyMoviesByItsRating(int ratings) {
        HashMap<String, Integer> hashMap = new HashMap<>();

        for (EfficientRater rater : myRaters) {
            for (String key : rater.getRatings().keySet()) {
                if (!hashMap.containsKey(key)) {
                    hashMap.put(key, 1);
                } else {
                    hashMap.put(key, hashMap.get(key)+1);
                }
            }
        }

        int total = 0;
        for (String key : hashMap.keySet()) {
            if (hashMap.get(key) >= ratings) {
                total++;
                System.out.println(key);
            }
        }

        System.out.println("total="+total);
    }

    private double getAverageByID(String id, int minimalRaters) {
        int howManyRater = 0;

        for (EfficientRater rater : myRaters) {
            for (String key : rater.getRatings().keySet()) {
                if (key.equals(id)) {
                    howManyRater++;
                }
            }
        }

        if (howManyRater >= minimalRaters) {
            double total = 0;
            int len = 0;

            for (EfficientRater rater : myRaters) {
                for (String key : rater.getRatings().keySet()) {
                    if (key.equals(id)) {
                        len++;
                        total += rater.getRatings().get(key).getValue();
                    }
                }
            }

            if (len > 0 && total > 0) {
                return total / (double) len;
            } else {
                return 0.0;
            }

        } else {
            return 0.0;
        }
    }

    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> averageRatings = new ArrayList<>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());


        for (String movieid : movies) {
            double averageRating = getAverageByID(movieid, minimalRaters);

            if (averageRating > 0) {
                averageRatings.add(new Rating(MovieDatabase.getTitle(movieid), averageRating));
            }
        }

        return averageRatings;
    }

    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria) {

        ArrayList<Rating> aveRatings = new ArrayList<>();
        ArrayList<String> movieIds = MovieDatabase.filterBy(filterCriteria);

        for (String movieid : movieIds) {
            double aveRating = getAverageByID(movieid, minimalRaters);

            if (aveRating > 0) {
                aveRatings.add(new Rating(movieid, aveRating));
            }
        }

        return aveRatings;
    }

    /*
    public void getAverageRatingOneMovie() {
        ThirdRatings secondRatings = new ThirdRatings();

        String movieTitle = "The Maze Runner";
        double averageRating = secondRatings.getAverageByID(getID(movieTitle), 1);

        System.out.println(movieTitle+"\t"+averageRating);
    }*/

    public static void main(String[] args) {
        ThirdRatings thirdRatings = new ThirdRatings();
        thirdRatings.howManyMoviesByItsRating(35);

        //secondRatings.getAverageRatingOneMovie();
        //ArrayList<Rating> ratings = secondRatings.getAverageRatings(50);
        //Collections.sort(ratings);
        //System.out.println(ratings);

        //secondRatings.getAverageRatingOneMovie();
        //System.out.println(secondRatings.getAverageByID("1798709", 7));
        //System.out.println(secondRatings.getAverageByID("790636", 3));
        //System.out.println(secondRatings.getTitle("790636"));
    }
}
