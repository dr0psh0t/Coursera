package com.coursera.principlessoftwaredesign.week3.generatingrandomtext.interfacesabstracts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class EfficientMarkovModel extends AbstractMarkovModel {

    private int n;
    private HashMap<String, ArrayList<String>> mapOfKeys;

    public EfficientMarkovModel(int n) {
        random = new Random();
        this.n = n;
        mapOfKeys = new HashMap<>();
    }

    public void setRandom(int seed) {
        random = new Random(seed);
    }

    public void setTraining(String s) {
        //trainingText = s.trim();
        trainingText = s;
    }

    int keysNumber = 0;

    public String getRandomText(int charsLength) {
        StringBuilder sb = new StringBuilder();

        int randomIndex = random.nextInt(trainingText.length() - n);
        String key = trainingText.substring(randomIndex, randomIndex + n);
        sb.append(key);

        keysNumber++;

        for (int k = 0; k < charsLength - n; k++) {
            ArrayList<String> follows = buildMap(key);

            if (follows.size() == 0) {
                break;
            }

            //  get random index from follows ArrayList
            randomIndex = random.nextInt(follows.size());
            //  get the element called next using the random index from follows ArrayList
            String next = follows.get(randomIndex);
            //  append next to stringbuffer
            sb.append(next);

            //System.out.println(next+" = "+follows);
            key = key.substring(1) + next;
            //System.out.println("key="+key);

            keysNumber++;
        }

        return sb.toString();
    }

    public ArrayList<String> buildMap(String key) {
        if (!mapOfKeys.containsKey(key)) {
            mapOfKeys.put(key, getFollows(key));
        }

        return mapOfKeys.get(key);
    }

    public void printHashMapInfo() {
        int keys = 0;
        int largestSize = 0;

        System.out.println(mapOfKeys);

        for (String key : mapOfKeys.keySet()) {
            keys++;

            if (mapOfKeys.get(key).size() > largestSize) {
                largestSize = mapOfKeys.get(key).size();
            }
        }

        //System.out.println("number of keys: "+keys);
        System.out.println("number of keys: "+mapOfKeys.size());
        System.out.println("size of largest value: "+largestSize);
        System.out.println("max keys: ");

        for (String key : mapOfKeys.keySet()) {
            if (mapOfKeys.get(key).size() == largestSize) {
                System.out.print(key+", ");
            }
        }
    }

    public String toString() {
        return "EfficientMarkovModel of order "+n;
    }
}