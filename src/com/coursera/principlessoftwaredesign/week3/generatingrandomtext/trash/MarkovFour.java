package com.coursera.principlessoftwaredesign.week3.generatingrandomtext.trash;

import java.util.ArrayList;
import java.util.Random;

public class MarkovFour {

    private String trainingText;
    private Random random;

    public MarkovFour() {
        random = new Random();
    }

    public void setRandom(int seed) {
        random = new Random(seed);
    }

    public void setTraining(String s) {
        trainingText = s;
    }

    public String getRandomText(int charLength) {
        StringBuffer sb = new StringBuffer();

        int randomIndex = random.nextInt(trainingText.length() - 4);
        String key = trainingText.substring(randomIndex, randomIndex + 4);
        sb.append(key);

        for (int k = 0; k < charLength - 4; k++) {
            ArrayList<String> follows = getFollows(key);

            if (follows.isEmpty()) {
                break;
            }

            randomIndex = random.nextInt(follows.size());
            String next = follows.get(randomIndex);
            sb.append(next);
            key = key.substring(1) + next;
        }

        return sb.toString();
    }

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
    }
}
