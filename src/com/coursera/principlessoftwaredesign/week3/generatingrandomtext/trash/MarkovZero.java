package com.coursera.principlessoftwaredesign.week3.generatingrandomtext.trash;

import java.util.Random;

public class MarkovZero {

    private String trainingText;
    private Random random;

    public MarkovZero() {
        random = new Random();
    }

    public void setRandom(int seed) {
        random = new Random(seed);
    }

    public void setTraining(String s) {
        trainingText = s.trim();
    }

    public String getRandomText(int charLength) {

        if (trainingText == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        for (int k = 0; k < charLength; k++) {

            int randomIndex = random.nextInt(trainingText.length());
            sb.append(trainingText.charAt(randomIndex));
        }

        return sb.toString();
    }
}