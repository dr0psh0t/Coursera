package com.coursera.recommendation;

import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerAverage {

    public void printAverageRatings() {
        SecondRatings secondRatings = new SecondRatings();

        //System.out.println("There are "+secondRatings.getMovieSize()+" movies");
        //System.out.println("There are "+secondRatings.getRaterSize()+" raters");

        ArrayList<Rating> averageRatings = secondRatings.getAverageRatings(3);
        Collections.sort(averageRatings);

        for (Rating rating : averageRatings) {
            System.out.println(rating.getValue()+" "+rating.getItem());
        }

        System.out.println(averageRatings.size());
    }

    public static void main(String[] args) {
        MovieRunnerAverage movieRunnerAverage = new MovieRunnerAverage();
        movieRunnerAverage.printAverageRatings();
    }
}