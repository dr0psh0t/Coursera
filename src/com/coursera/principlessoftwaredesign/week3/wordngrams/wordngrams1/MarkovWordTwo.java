package com.coursera.principlessoftwaredesign.week3.wordngrams.wordngrams1;

import java.util.ArrayList;
import java.util.Random;

public class MarkovWordTwo implements IMarkovModel{

    private String[] myText;
    private Random myRandom;

    public MarkovWordTwo() {
        myRandom = new Random();
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text){
        myText = text.split("\\s+");
    }

    public String getRandomText(int numWords) {

        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-2);  // random word to start with

        String key = myText[index];
        String key2 = myText[index+1];

        sb.append(key);
        sb.append(" ");
        sb.append(key2);
        sb.append(" ");

        for(int k=0; k < numWords-2; k++) {
            ArrayList<String> follows = getFollows(key, key2);

            if (follows.size() == 0) {
                break;
            }

            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");

            key = key2;
            key2 = next;
        }

        return sb.toString().trim();
    }

    public int indexOf(String[] words, String target1, String target2, int start) {
        for (int k = start; k < words.length; k++) {
            if (words[k].equals(target1)) {
                if (words[k+1].equals(target2)) {
                    return k;
                }
            }
        }
        return -1;
    }

    private ArrayList<String> getFollows(String key1, String key2) {
        ArrayList<String> follows = new ArrayList<>();

        for (int k = 0; k < myText.length; k++) {
            if (myText[k].equals(key1)) {
                if ((k+2) < myText.length) {
                    if (myText[k + 1].equals(key2)) {
                        follows.add(myText[k + 2]);
                    }
                }
            }
        }

        return follows;
    }
}
