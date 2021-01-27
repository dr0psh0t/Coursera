package com.coursera.principlessoftwaredesign.week3.generatingrandomtext.interfacesabstracts;

import java.util.Random;

public class MarkovZero extends AbstractMarkovModel {

    public MarkovZero() {
        random = new Random();
    }

    public void setTraining(String s) {
        trainingText = s.trim();
    }

    public String getRandomText(int numChars) {
        if (trainingText == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        for (int k = 0; k < numChars; k++) {
            int index = random.nextInt(trainingText.length());
            sb.append(trainingText.charAt(index));
        }

        return sb.toString();
    }

    public String toString() {
        return "MarkovModel of order 0";
    }
}
