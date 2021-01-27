package com.coursera.principlessoftwaredesign.week3.generatingrandomtext.trash;

import java.util.ArrayList;
import java.util.Random;

public class MarkovOne {

    private String trainingText;
    private Random random;

    public MarkovOne() {
        random = new Random();
    }

    public void setRandom(int seed) {
        random = new Random(seed);
    }

    public void setTraining(String s) {
        trainingText = s.trim();
        //trainingText = s;
    }

    public String getRandomText(int charLength) {
        StringBuffer sb = new StringBuffer();

        int randomIndex = random.nextInt(trainingText.length() - 1);
        String key = trainingText.substring(randomIndex, randomIndex + 1);
        sb.append(key);

        for (int k = 0; k < charLength - 1; k++) {
            ArrayList<String> follows = getFollows(key);

            if (follows.size() == 0) {
                break;
            }

            randomIndex = random.nextInt(follows.size());
            String next = follows.get(randomIndex);
            sb.append(next);
            key = key.substring(1) + next;
        }

        return sb.toString();
    }

    /*
    public ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<>();
        int keyIndex = trainingText.indexOf(key);

        while ((keyIndex != -1)) {
            if ((keyIndex+key.length()+1) < trainingText.length()) {
                follows.add(trainingText.substring(keyIndex+key.length(), keyIndex+key.length()+1));
                keyIndex = trainingText.indexOf(key, ++keyIndex);
            } else {
                break;
            }
        }

        return follows;
    }*/

    public ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<>();
        int pos = 0;

        while (pos < trainingText.length()) {
            int start = trainingText.indexOf(key, pos);

            if (start == -1) {
                break;
            }

            if (start + key.length() >= trainingText.length()-1) {
                break;
            }

            String next = trainingText.substring(start+key.length(), start+key.length()+1);
            follows.add(next);
            pos = start+key.length();
        }

        return follows;
    }
}
