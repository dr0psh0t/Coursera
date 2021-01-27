package com.coursera.arraylistdata.week2.usingimprovinggladlibs.improvinggladlibsexercise;

import edu.duke.*;
import java.util.*;

public class GladLibMap {

    private HashMap<String, ArrayList<String>> myMap;
    private HashMap<String, Integer> wordsConsideredMap;
    private Random myRandom;

    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "D:\\daryll\\coursera\\src\\com\\coursera\\arraylistdata\\week2\\usingimprovinggladlibs\\data\\";

    public GladLibMap() {
        myRandom = new Random();
        myMap = new HashMap<>();
        wordsConsideredMap = new HashMap<>();
        initializeFromSource(dataSourceDirectory);
    }

    public GladLibMap(String source) {
        myRandom = new Random();
        myMap = new HashMap<>();
        wordsConsideredMap = new HashMap<>();
        initializeFromSource(source);
    }

	/*
	private void initializeFromSource() {
		for (String s : myLabelSource.keySet()) {
			ArrayList<String> list = readIt(myLabelSource.get(s));
			myMap.put(s, list);
		}
	}*/
    private void initializeFromSource(String source) {
        String[] labels = {"country", "noun", "animal", "adjective", "name",
                "color", "timeframe", "verb", "fruit"};

        for (String s : labels) {
            ArrayList<String> list = readIt(source+s+".txt");
            myMap.put(s, list);
        }
    }

    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }

    private String getSubstitute(String label) {

        if (wordsConsideredMap.containsKey(label)) {
            wordsConsideredMap.put(label, wordsConsideredMap.get(label) + 1);
        } else {
            wordsConsideredMap.put(label, 1);
        }

        if (label.equals("number")) {
            return ""+myRandom.nextInt(50)+5;
        }
        return randomFrom(myMap.get(label));
    }

    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);

        if (first == -1 || last == -1) {
            return w;
        }

        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        return prefix+sub+suffix;
    }

    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }

    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }

    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }

    public int totalWordsInMap() {
        System.out.println("\n");
        int totalWords = 0;

        for (String everykey : myMap.keySet()) {
            totalWords += myMap.get(everykey).size();
        }

        return totalWords;
    }

    public int totalWordsConsidered() {
        int totalWords = 0;
        for (String key : wordsConsideredMap.keySet()) {
            totalWords += wordsConsideredMap.get(key);
            System.out.println(key+","+wordsConsideredMap.get(key));
        }
        return totalWords;
    }


    public void makeStory(){
        System.out.println();
        String story = fromTemplate(dataSourceDirectory+"madtemplate.txt");
        printOut(story, 60);
    }

    public static void main(String[] args) {
        GladLibMap gladLib = new GladLibMap();

        gladLib.makeStory();
        System.out.println("totalWordsInMap = "+gladLib.totalWordsInMap());
        System.out.println("totalWordsConsidered = "+gladLib.totalWordsConsidered());
    }
}
