package com.coursera.recommendation;

import jdk.nashorn.internal.ir.LiteralNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class FourthRatings {

    public FourthRatings() {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
    }

    private double getAverageByID(String id, int minimalRaters) {
        int howManyRater = 0;

        for (Rater rater : RaterDatabase.getRaters()) {
            for (String key : rater.getRatings().keySet()) {
                if (key.equals(id)) {
                    howManyRater++;
                }
            }
        }

        if (howManyRater >= minimalRaters) {
            double total = 0;
            int len = 0;

            for (Rater rater : RaterDatabase.getRaters()) {
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

    private static void dotProductTest() {
        Rater me = new EfficientRater("15");
        me.addRating("2354", 10);
        me.addRating("3285", 6);
        me.addRating("1297", 2);
        me.addRating("5804", 8);

        Rater r = new EfficientRater("20");
        r.addRating("3285", 4);
        r.addRating("1297", 7);
        r.addRating("6574", 10);
        r.addRating("2354", 9);

        double dotProduct = 0;

        HashMap<String, Rating> myRatings = me.getRatings();
        HashMap<String, Rating> hisRatings = r.getRatings();

        for (String myRating : myRatings.keySet()) {
            if (r.hasRating(myRating)) {
                double myVal = myRatings.get(myRating).getValue();
                double hisVal = hisRatings.get(myRating).getValue();

                /*
                if (myVal > 0 && hisVal > 0) {
                    dotProduct += (myVal - 5) * (hisVal - 5);
                }*/

                dotProduct += (myVal - 5) * (hisVal - 5);
            }
        }

        System.out.println("dotProd="+dotProduct);
    }

    private double dotProduct(Rater me, Rater r) {
        double dotProduct = 0;

        HashMap<String, Rating> myRatings = me.getRatings();
        HashMap<String, Rating> hisRatings = r.getRatings();

        for (String myRating : myRatings.keySet()) {
            if (r.hasRating(myRating)) {
                double myVal = myRatings.get(myRating).getValue();
                double hisVal = hisRatings.get(myRating).getValue();

                if (myVal > 0 && hisVal > 0) {
                    dotProduct += (myVal - 5) * (hisVal - 5);
                }
            }
        }

        return dotProduct;
    }

    private ArrayList<Rating> getSimilarities(String id) {
        ArrayList<Rating> list = new ArrayList<>();
        Rater me = RaterDatabase.getRater(id);

        for (Rater r : RaterDatabase.getRaters()) {
            //  add dot_product(r,me) to list if r != me
            double dotProd = dotProduct(me, r);

            if (!me.getID().equals(r.getID())) {
                if (dotProd >= 0) {
                    list.add(new Rating(r.getID(), dotProd));
                }
            }
        }

        Collections.sort(list, Collections.reverseOrder());
        return list;
    }

    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters) {

        ArrayList<Rating> list = getSimilarities(id);
        ArrayList<Rating> ret = new ArrayList<>();

        for (String eachMovie : MovieDatabase.filterBy(new TrueFilter())) {
            int count = 0;
            double sum = 0;

            //for (int k = 0; k < list.size(); k++) {
            for (int k = 0; k < numSimilarRaters; k++) {
                Rating r = list.get(k);
                //  use Rater id and weight in r
                //  to update running totals
                Rater eachRater = RaterDatabase.getRater(r.getItem());

                if (eachRater.hasRating(eachMovie)) {
                    count++;

                    double eachRaterVal = eachRater.getRating(eachMovie);
                    double weight = r.getValue();
                    sum += eachRaterVal * weight;
                }
            }

            //  add Rating for movieID to ret
            if (count >= minimalRaters) {
                double ave = sum / count;

                if (ave != 0) {
                    ret.add(new Rating(eachMovie, ave));
                }
            }
        }

        Collections.sort(ret, Collections.reverseOrder());
        return ret;
    }

    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters,
                                                       Filter filter) {

        ArrayList<Rating> list = getSimilarities(id);
        ArrayList<Rating> ret = new ArrayList<>();

        for (String eachMovie : MovieDatabase.filterBy(filter)) {

            int count = 0;
            double sum = 0;

            //for (int k = 0; k < list.size(); k++) {
            for (int k = 0; k < numSimilarRaters; k++) {
                Rating r = list.get(k);
                //  use Rater id and weight in r
                //  to update running totals
                Rater eachRater = RaterDatabase.getRater(r.getItem());

                if (eachRater.hasRating(eachMovie)) {
                    count++;

                    double eachRaterVal = eachRater.getRating(eachMovie);
                    double weight = r.getValue();
                    sum += eachRaterVal * weight;
                }
            }

            //  add Rating for movieID to ret
            if (count >= minimalRaters) {
                double ave = sum / count;

                if (ave != 0) {
                    ret.add(new Rating(eachMovie, ave));
                }
            }
        }

        Collections.sort(ret, Collections.reverseOrder());
        return ret;
    }

    public static void main(String[] args) {
        //FourthRatings fr = new FourthRatings();
        //fr.getSimilarRatings("2", 0, 0);

        dotProductTest();
    }
}
