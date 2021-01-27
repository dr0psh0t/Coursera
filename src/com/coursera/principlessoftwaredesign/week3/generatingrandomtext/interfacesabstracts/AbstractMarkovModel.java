package com.coursera.principlessoftwaredesign.week3.generatingrandomtext.interfacesabstracts;
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String trainingText;
    protected Random random;
    
    public void setTraining(String s) {
        trainingText = s.trim();
    }

    public void setRandom(int seed) {
        random = new Random(seed);
    }
 
    abstract public String getRandomText(int numChars);

    protected ArrayList<String> getFollows(String key) {

        ArrayList<String> follows = new ArrayList<>();
        int keyIndex = trainingText.indexOf(key);

        while ((keyIndex != -1)) {
            if (keyIndex+key.length() < trainingText.length()) {
                follows.add(trainingText.substring(keyIndex+key.length(), keyIndex+key.length()+1));
                keyIndex = trainingText.indexOf(key, ++keyIndex);
            } else {
                break;
            }
        }

        return follows;
    }
}
