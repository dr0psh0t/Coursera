package com.coursera.principlessoftwaredesign.week3.wordngrams.wordgramclass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class EfficientMarkovWord implements IMarkovModel {

    private String[] myText;
    private Random myRandom;
    private int myOrder;

    private HashMap<WordGram, ArrayList<String>> mapOfKeys;

    public EfficientMarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
        mapOfKeys = new HashMap<>();
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

        WordGram kGram = new WordGram(myText, index, myOrder);
        //WordGram kGram = new WordGram(myText, index, numWords);
        sb.append(kGram);
        sb.append(" ");

        for(int k=0; k < numWords-myOrder; k++) {
            //ArrayList<String> follows = getFollows(kGram);
            ArrayList<String> follows = buildMap(kGram);

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

    public ArrayList<String> buildMap(WordGram wordGram) {

        for (WordGram eachWordGram : mapOfKeys.keySet()) {
            //  if exist in hashmap
            if (eachWordGram.equals(wordGram)) {
                //return mapOfKeys.get(eachWordGram);
                return new ArrayList<>();
            }
        }

        //  if wordgram is not in hashmap
        mapOfKeys.put(wordGram, getFollows(wordGram));
        return mapOfKeys.get(wordGram);

        /*
        if (!mapOfKeys.containsKey(wordGram)) {
            mapOfKeys.put(wordGram, getFollows(wordGram));
        }
        return mapOfKeys.get(wordGram);*/
    }

    public void printHashMapInfo() {
        /*
        for (WordGram keyWordGram : mapOfKeys.keySet()) {
            System.out.println(keyWordGram.toString()+" - "+mapOfKeys.get(keyWordGram));
        }*/


        int largestSize = 0;
        System.out.println(mapOfKeys);

        for (WordGram keyWordGram : mapOfKeys.keySet()) {
            if (mapOfKeys.get(keyWordGram).size() > largestSize) {
                largestSize = mapOfKeys.get(keyWordGram).size();
            }
        }

        System.out.println("number of keys: "+mapOfKeys.size());
        System.out.println("size of largest value: "+largestSize);
        System.out.println("max keys: ");

        for (WordGram keyWordGram : mapOfKeys.keySet()) {
            if (mapOfKeys.get(keyWordGram).size() == largestSize) {
                System.out.print(keyWordGram+", ");
            }
        }
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
