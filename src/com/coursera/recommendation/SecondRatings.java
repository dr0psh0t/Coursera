package com.coursera.recommendation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class SecondRatings {
    public static String PATH = "D:\\daryll\\coursera\\src\\com\\coursera\\recommendation\\data\\";

    private ArrayList<Movie> myMovies;
    private ArrayList<EfficientRater> myRaters;

    public SecondRatings() {
        this(PATH+"ratedmovies_short.csv", PATH+"ratings_short.csv");
        //this(PATH+"test_ratedmovies.csv", PATH+"test_ratings_short.csv");
        //this(PATH+"ratedmoviesfull.csv", PATH+"ratings.csv");
    }

    public SecondRatings(String moviefile, String ratingsfile) {
        FirstRatings firstRatings = new FirstRatings();

        myMovies = firstRatings.loadMovies(moviefile);
        myRaters = firstRatings.loadRaters(ratingsfile);

        System.out.println(myMovies.size()+","+myRaters.size());
    }

    public int getMovieSize() {
        return myMovies.size();
    }

    public int getRaterSize() {
        return myRaters.size();
    }

    private void howManyMoviesByItsRating(int ratings) {
        HashMap<String, Integer> hashMap = new HashMap<>();

        for (EfficientRater rater : myRaters) {
            for (String key : rater.getRatings().keySet()) {
                if (!hashMap.containsKey(key)) {
                    hashMap.put(key, 1);
                } else {
                    hashMap.put(key, hashMap.get(key)+1);
                }
            }
            /*
            for (Rating rating : rater.getRatings()) {
                if (!hashMap.containsKey(rating.getItem())) {
                    hashMap.put(rating.getItem(), 1);
                } else {
                    hashMap.put(rating.getItem(), hashMap.get(rating.getItem())+1);
                }
            }*/
        }

        int total = 0;
        for (String key : hashMap.keySet()) {
            if (hashMap.get(key) >= ratings) {
                total++;
                System.out.println(key);
            }
        }
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

        /*
        for (PlainRater rater : myRaters) {
            for (Rating rating : rater.getRatings()) {
                if (rating.getItem().equals(id)) {
                    howManyRater++;
                }
            }
        }*/

        if (howManyRater >= minimalRaters) {
            System.out.println("howManyRaters="+howManyRater+",minimalRater="+minimalRaters);

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

            /*
            for (PlainRater rater : myRaters) {
                for (Rating rating : rater.getRatings()) {
                    if (rating.getItem().equals(id)) {
                        len++;
                        total += rating.getValue();
                    }
                }
            }*/

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

        for (Movie movie : myMovies) {
            double averageRating = getAverageByID(movie.getID(), minimalRaters);

            if (averageRating > 0) {
                averageRatings.add(new Rating(getTitle(movie.getID()), averageRating));
            }
        }

        return averageRatings;
    }

    public String getTitle(String id) {
        for (Movie movie : myMovies) {
            if (movie.getID().equals(id)) {
                return movie.getTitle();
            }
        }

        return "Movie id was not found";
    }

    public String getID(String title) {
        for (Movie movie : myMovies) {
            if (movie.getTitle().equals(title)) {
                return movie.getID();
            }
        }

        return "NO SUCH TITLE";
    }

    public void getAverageRatingOneMovie() {
        SecondRatings secondRatings = new SecondRatings();

        String movieTitle = "The Maze Runner";
        double averageRating = secondRatings.getAverageByID(getID(movieTitle), 1);

        System.out.println(movieTitle+"\t"+averageRating);
    }

    public static void main(String[] args) {
        SecondRatings secondRatings = new SecondRatings();
        //secondRatings.getAverageRatingOneMovie();
        ArrayList<Rating> ratings = secondRatings.getAverageRatings(50);
        Collections.sort(ratings);
        System.out.println(ratings);

        //secondRatings.getAverageRatingOneMovie();
        //System.out.println(secondRatings.getAverageByID("1798709", 7));
        //System.out.println(secondRatings.getAverageByID("790636", 3));
        //System.out.println(secondRatings.getTitle("790636"));
    }
}
