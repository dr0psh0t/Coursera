package com.coursera.recommendation;

import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerSimilarRatings {

    public void printSimilarRatings() {
        FourthRatings fourthRatings = new FourthRatings();

        ArrayList<Rating> ratings = fourthRatings.getSimilarRatings("337", 10, 3);

        for (Rating rating : ratings) {
            System.out.println(rating.getValue()+"\t"+MovieDatabase.getTitle(rating.getItem()));
        }
    }

    public void printSimilarRatingsByGenre() {
        FourthRatings fourthRatings = new FourthRatings();

        ArrayList<Rating> ratings = fourthRatings.getSimilarRatingsByFilter("964", 20, 5, new GenreFilter("Mystery"));

        for (Rating rating : ratings) {
            System.out.println(rating.getValue()+"\t"+MovieDatabase.getTitle(rating.getItem()));
        }
    }

    public void printSimilarRatingsByDirector() {
        FourthRatings fourthRatings = new FourthRatings();

        ArrayList<Rating> ratings = fourthRatings.getSimilarRatingsByFilter("120", 10, 2,
                new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh"));

        for (Rating rating : ratings) {
            System.out.println(rating.getValue()+"\t"+MovieDatabase.getTitle(rating.getItem()));
        }
    }

    public void printSimilarRatingsByGenreAndMinutes() {
        FourthRatings fourthRatings = new FourthRatings();

        AllFilters filters = new AllFilters();
        filters.addFilter(new GenreFilter("Drama"));
        filters.addFilter(new MinutesFilter(80, 160));

        ArrayList<Rating> ratings = fourthRatings.getSimilarRatingsByFilter("168", 10, 3, filters);

        for (Rating rating : ratings) {
            System.out.println(rating.getValue()+"\t"+MovieDatabase.getTitle(rating.getItem()));
        }
    }

    public void printSimilarRatingsByYearAfterAndMinutes() {
        FourthRatings fourthRatings = new FourthRatings();

        AllFilters filters = new AllFilters();
        filters.addFilter(new YearAfterFilter(1975));
        filters.addFilter(new MinutesFilter(70, 200));

        ArrayList<Rating> ratings = fourthRatings.getSimilarRatingsByFilter("314", 10, 5, filters);

        for (Rating rating : ratings) {
            System.out.println(rating.getValue()+"\t"+MovieDatabase.getTitle(rating.getItem()));
        }
    }

    public static void main(String[] args) {
        MovieRunnerSimilarRatings mov = new MovieRunnerSimilarRatings();
        mov.printSimilarRatings();
        //mov.printSimilarRatingsByGenre();
        //mov.printSimilarRatingsByDirector();
        //mov.printSimilarRatingsByGenreAndMinutes();
        //mov.printSimilarRatingsByYearAfterAndMinutes();
    }
}
