package com.coursera.recommendation;

import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerWithFilters {

    public void printAverageRatings() {

        ThirdRatings thirdRatings = new ThirdRatings();
        ArrayList<Rating> averageRatings = thirdRatings.getAverageRatings(35);

        System.out.println(thirdRatings.getRaterSize()+" raters");
        System.out.println(MovieDatabase.size()+" movies");
        System.out.println("found "+averageRatings.size()+" movies");

        Collections.sort(averageRatings);
        for (Rating rating : averageRatings) {
            System.out.println(rating.getValue()+" "+rating.getItem());
        }
    }

    public void printAverageRatingsByYear() {

        ThirdRatings thirdRatings = new ThirdRatings();
        ArrayList<Rating> averageRatingsByYear =
                thirdRatings.getAverageRatingsByFilter(20, new YearAfterFilter(2000));

        System.out.println(thirdRatings.getRaterSize()+" raters");
        System.out.println(MovieDatabase.size()+" movies");
        System.out.println("found "+averageRatingsByYear.size()+" movies");

        Collections.sort(averageRatingsByYear);
        for (Rating rating : averageRatingsByYear) {
            System.out.println(rating.getValue()+" "+MovieDatabase.getYear(rating.getItem())+" "+MovieDatabase.getTitle(rating.getItem()));
        }
    }

    public void printAverageRatingsByGenre() {

        ThirdRatings thirdRatings = new ThirdRatings();
        ArrayList<Rating> averageRatingsByGenre =
                thirdRatings.getAverageRatingsByFilter(20, new GenreFilter("Comedy"));

        System.out.println(thirdRatings.getRaterSize()+" raters");
        System.out.println(MovieDatabase.size()+" movies");
        System.out.println("found "+averageRatingsByGenre.size()+" movies");

        Collections.sort(averageRatingsByGenre);
        for (Rating rating : averageRatingsByGenre) {
            System.out.println(rating.getValue()+" "+MovieDatabase.getTitle(rating.getItem()));
            System.out.println("\t"+MovieDatabase.getGenres(rating.getItem()));
        }
    }

    public void printAverageRatingsByMinutes() {

        ThirdRatings thirdRatings = new ThirdRatings();
        ArrayList<Rating> averageRatingsByGenre =
                thirdRatings.getAverageRatingsByFilter(5, new MinutesFilter(105, 135));

        System.out.println(thirdRatings.getRaterSize()+" raters");
        System.out.println(MovieDatabase.size()+" movies");
        System.out.println("found "+averageRatingsByGenre.size()+" movies");

        Collections.sort(averageRatingsByGenre);
        for (Rating rating : averageRatingsByGenre) {
            System.out.println(rating.getValue()+" Time: "+MovieDatabase.getMinutes(rating.getItem())+" "
                    +MovieDatabase.getTitle(rating.getItem()));
        }
    }

    public void printAverageRatingsByDirectors() {

        ThirdRatings thirdRatings = new ThirdRatings();
        ArrayList<Rating> averageRatingsByGenre = thirdRatings.getAverageRatingsByFilter(4, new DirectorsFilter(
                "Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack"));

        System.out.println(thirdRatings.getRaterSize()+" raters");
        System.out.println(MovieDatabase.size()+" movies");
        System.out.println("found "+averageRatingsByGenre.size()+" movies");

        Collections.sort(averageRatingsByGenre);
        for (Rating rating : averageRatingsByGenre) {
            System.out.println(rating.getValue()+" "+MovieDatabase.getTitle(rating.getItem()));
            System.out.println("\t"+MovieDatabase.getDirector(rating.getItem()));
        }
    }

    public void printAverageRatingsByYearAfterAndGenre() {

        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new YearAfterFilter(1990));
        allFilters.addFilter(new GenreFilter("Drama"));

        ThirdRatings thirdRatings = new ThirdRatings();
        ArrayList<Rating> averageRatings = thirdRatings.getAverageRatingsByFilter(8, allFilters);

        System.out.println(thirdRatings.getRaterSize()+" raters");
        System.out.println(MovieDatabase.size()+" movies");
        System.out.println("found "+averageRatings.size()+" movies");

        Collections.sort(averageRatings);
        for (Rating rating : averageRatings) {
            System.out.println(rating.getValue()+" "+MovieDatabase.getYear(rating.getItem())+" "
                    +MovieDatabase.getTitle(rating.getItem()));
            System.out.println("\t"+MovieDatabase.getGenres(rating.getItem()));
        }
    }

    public void printAverageRatingsByDirectorsAndMinutes() {

        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new DirectorsFilter(
                "Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack"));
        allFilters.addFilter(new MinutesFilter(90, 180));

        ThirdRatings thirdRatings = new ThirdRatings();
        ArrayList<Rating> averageRatings = thirdRatings.getAverageRatingsByFilter(3, allFilters);

        System.out.println(thirdRatings.getRaterSize()+" raters");
        System.out.println(MovieDatabase.size()+" movies");
        System.out.println("found "+averageRatings.size()+" movies");

        Collections.sort(averageRatings);
        for (Rating rating : averageRatings) {
            System.out.println(rating.getValue()+" Time: "+MovieDatabase.getMinutes(rating.getItem())
                    +" "+MovieDatabase.getTitle(rating.getItem()));
            System.out.println("\t"+MovieDatabase.getDirector(rating.getItem()));
        }
    }

    public static void main(String[] args) {
        MovieRunnerWithFilters movieRunnerWithFilters = new MovieRunnerWithFilters();
        //movieRunnerWithFilters.printAverageRatings();
        //movieRunnerWithFilters.printAverageRatingsByYear();
        //movieRunnerWithFilters.printAverageRatingsByGenre();
        //movieRunnerWithFilters.printAverageRatingsByMinutes();
        //movieRunnerWithFilters.printAverageRatingsByDirectors();
        //movieRunnerWithFilters.printAverageRatingsByYearAfterAndGenre();
        movieRunnerWithFilters.printAverageRatingsByDirectorsAndMinutes();
    }
}
