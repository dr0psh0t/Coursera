package com.coursera.recommendation;

import edu.duke.*;

import java.io.File;
import java.util.*;
import org.apache.commons.csv.*;

//  Step One graded quiz 100%. all methods have proven correct.
public class FirstRatings {

    public ArrayList<Movie> loadMovies(String filename) {
        ArrayList<Movie> movies = new ArrayList<>();

        System.out.println(filename);

        FileResource fr = new FileResource(filename);
        CSVParser csvParser = fr.getCSVParser();
        Movie movie;

        for (CSVRecord record : csvParser) {
            movie = new Movie(
                    String.valueOf(Integer.parseInt(record.get("id"))), record.get("title"), record.get("year"), record.get("genre"),
                    record.get("director"), record.get("country"), record.get("poster"),
                    Integer.parseInt(record.get("minutes")));

            movies.add(movie);
        }

        return movies;
    }

    public void testLoadMovies() {
        DirectoryResource dr = new DirectoryResource();
        ArrayList<Movie> movies = new ArrayList<>();

        for (File file : dr.selectedFiles()) {
            movies.clear();
            movies.addAll(loadMovies(file.getAbsolutePath()));
        }

        int genresIncluded = 0;
        int minGreater150 = 0;
        int maxMoviesDirected = 0;
        String directorWithMostMovies = "";
        HashMap<String, Integer> directorMovies = new HashMap<>();

        for (Movie movie : movies) {
            System.out.println(movie);

            if (movie.getGenres().contains("Comedy")) {
                genresIncluded++;
            }

            if (movie.getMinutes() > 150) {
                minGreater150++;
            }

            String director = movie.getDirector();

            if (director.contains(",")) {
                for (String eachDirector : director.split(",")) {
                    eachDirector = eachDirector.trim();
                    storeDirectorMovies(directorMovies, eachDirector);
                }
            } else {
                storeDirectorMovies(directorMovies, director);
            }

            for (String key : directorMovies.keySet()) {
                int val = directorMovies.get(key);

                if (val >= maxMoviesDirected) {
                    maxMoviesDirected = val;
                    directorWithMostMovies = key;
                }
            }
        }

        System.out.println("\nthere are "+movies.size()+" movies.");
        System.out.println("there are "+genresIncluded+" movies that has comedy.");
        System.out.println("there are "+minGreater150+" movies which are more than 150 minutes.");
        System.out.println("there are "+maxMoviesDirected+" movies directed by "+directorWithMostMovies+".");
    }

    public ArrayList<EfficientRater> loadRaters(String filename) {
        ArrayList<EfficientRater> raters = new ArrayList<>();

        FileResource fr = new FileResource(filename);
        CSVParser csvParser = fr.getCSVParser();
        EfficientRater rater;

        for (CSVRecord record : csvParser) {
            String raterId = record.get("rater_id");

            String movieid = String.valueOf(Integer.parseInt(record.get("movie_id")));
            //String movieid = record.get("movie_id");
            double rating = Double.parseDouble(record.get("rating"));

            rater = new EfficientRater(raterId);
            rater.addRating(movieid, rating);
            raters.add(rater);

            /*
            EfficientRater rater1 = getEfficientRaterThatHasId(raterId, raters);
            if (rater1 == null) {
                rater = new EfficientRater(raterId);
                rater.addRating(movieid, rating);
                raters.add(rater);
            } else {
                rater1.addRating(movieid, rating);
            }*/
        }

        return raters;
    }

    public EfficientRater getEfficientRaterThatHasId(String id, ArrayList<EfficientRater> raters) {
        for (EfficientRater rater : raters) {
            if (rater.getID().equals(id)) {
                return rater;
            }
        }
        return null;
    }

    public void testLoadRaters() {
        DirectoryResource dr = new DirectoryResource();
        ArrayList<EfficientRater> raters = new ArrayList<>();

        for (File file : dr.selectedFiles()) {
            raters.clear();
            raters = loadRaters(file.getAbsolutePath());
        }

        HashMap<String, Integer> raterRatings = new HashMap<>();

        for (EfficientRater rater : raters) {
            if (raterRatings.containsKey(rater.getID())) {
                int val = raterRatings.get(rater.getID());
                raterRatings.put(rater.getID(), val +1);
            } else {
                raterRatings.put(rater.getID(), 1);
            }
        }

        int maximumRatings = 0;
        HashMap<String, Integer> highestRaters = new HashMap<>();

        for (String str : raterRatings.keySet()) {

            if (raterRatings.get(str) == maximumRatings) {
                if (highestRaters.containsKey(str)) {
                    int val = highestRaters.get(str);
                    highestRaters.put(str, 1 + val);
                } else {
                    highestRaters.put(str, maximumRatings);
                }
            }

            if (raterRatings.get(str) > maximumRatings) {
                maximumRatings = raterRatings.get(str);
                highestRaters.clear();
                highestRaters.put(str, maximumRatings);
            }
        }

        System.out.println("\n"+highestRaters.size()+" raters with most ratings.");
        System.out.println("ID\t#Ratings");
        for (String str : highestRaters.keySet()) {
            System.out.println(str+"\t"+highestRaters.get(str));
        }

        String movieId = "1798709";
        HashMap<String, String> numRatingMovies = new HashMap<>();

        for (EfficientRater rater : raters) {

            for (String key : rater.getRatings().keySet()) {
                if (movieId.equals(key)) {
                    numRatingMovies.put(rater.getID(), movieId);
                    break;
                }
            }

            /*
            for (Rating rating : rater.getRatings()) {
                if (movieId.equals(rating.getItem())) {
                    numRatingMovies.put(rater.getID(), movieId);
                    break;
                }
            }*/
        }

        System.out.println("\n"+numRatingMovies.size()+" raters rating a movie "+movieId);

        for (String str : numRatingMovies.keySet()) {
            System.out.println(str+"\t"+numRatingMovies.get(str));
        }

        ArrayList<String> uniqueMovies = new ArrayList<>();
        for (EfficientRater rater : raters) {
            for (String key : rater.getRatings().keySet()) {
                if (!uniqueMovies.contains(key)) {
                    uniqueMovies.add(key);
                }
            }
            /*
            for (Rating rating : rater.getRatings()) {
                if (!uniqueMovies.contains(rating.getItem())) {
                    uniqueMovies.add(rating.getItem());
                }
            }*/
        }

        System.out.println("\n"+uniqueMovies.size()+" unique movies rated.");
    }

    public void storeDirectorMovies(HashMap<String, Integer> directorMap, String director) {
        if (directorMap.containsKey(director)) {
            int value = directorMap.get(director);
            directorMap.put(director, value + 1);
        } else {
            directorMap.put(director, 1);
        }
    }

    public static void main(String[] args) {
        FirstRatings fr = new FirstRatings();
        //fr.testLoadMovies();
        fr.testLoadRaters();
    }
}
