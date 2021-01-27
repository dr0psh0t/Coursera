package com.coursera.principlessoftwaredesign.week3.wordngrams.wordgramclass;

import java.util.ArrayList;
import java.util.Random;

public class MarkovWord implements IMarkovModel {

    private String[] myText;
    private Random myRandom;
    private int myOrder;

    public MarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
    }

    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }

    public void setTraining(String text){
        myText = text.split("\\s+");
    }

    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with

        //WordGram kGram = new WordGram(myText, index, myOrder);
        WordGram kGram = new WordGram(myText, index, numWords);
        sb.append(kGram);
        sb.append(" ");

        for(int k=0; k < numWords-myOrder; k++){
            ArrayList<String> follows = getFollows(kGram);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            kGram = kGram.shiftAdd(next);
        }
        return sb.toString().trim();
    }

    public int indexOf(String[] words, WordGram target, int start) {
        for (int k = start; k < words.length; k++) {
            for (int i = 0; i < target.length(); i++) {
                if (words[k].equals(target.wordAt(i))) {
                    return k;
                }
            }
        }
        return -1;
    }

    private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<>();
        int pos = 0;
        while(pos < myText.length){
            int start = indexOf(myText, kGram, pos);
            if(start == -1){
                break;
            }
            if(start  >= myText.length - myOrder){
                break;
            }
            String next = myText[start + myOrder];
            follows.add(next);
            pos = start + myOrder;
        }
        return follows;
    }
}
