package com.coursera.recommendation;

import java.util.ArrayList;
import java.util.HashMap;

public class EfficientRater implements Rater {

    private String myID;
    //private ArrayList<Rating> myRatings;
    private HashMap<String, Rating> myRatings;

    public EfficientRater(String id) {
        myID = id;
        //myRatings = new ArrayList<>();
        myRatings = new HashMap<>();
    }

    public HashMap<String, Rating> getRatings() {
        return myRatings;
    }

    public void addRating(String item, double rating) {
        //myRatings.add(new Rating(item,rating));
        myRatings.put(item, new Rating(item, rating));
    }

    public boolean hasRating(String item) {
        if (myRatings.containsKey(item)) {
            return true;
        }

        /*
        for(int k=0; k < myRatings.size(); k++){
            if (myRatings.get(k).getItem().equals(item)){
                return true;
            }
        }*/

        return false;
    }

    public String getID() {
        return myID;
    }

    public double getRating(String item) {
        /*
        for(int k=0; k < myRatings.size(); k++) {

            System.out.println(myRatings.get);

            if (myRatings.get(k).getItem().equals(item)){
                return myRatings.get(k).getValue();
            }
        }*/

        for (String key : myRatings.keySet()) {
            if (myRatings.get(key).getItem().equals(item)) {
                return myRatings.get(key).getValue();
            }
        }

        return -1;
    }

    public int numRatings() {
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<>();
        for(int k=0; k < myRatings.size(); k++){
            list.add(myRatings.get(k).getItem());
        }

        return list;
    }
}
