package com.coursera.arraylistdata.week2.usingimprovinggladlibs.improvinggladlibsexercise;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class WordsInFiles {
    public static String url = "D:\\daryll\\coursera\\src\\com\\coursera\\arraylistdata\\week2\\usingimprovinggladlibspracticequiz\\";

    private HashMap<String, ArrayList<String>> mapOfWords;

    public WordsInFiles() {
        mapOfWords = new HashMap<>();
    }

    public void addWordsFromFile(File f) {
        FileResource fr = new FileResource(f);

        for (String word : fr.words()) {
            if (mapOfWords.containsKey(word)) {
                if (!mapOfWords.get(word).contains(f.getName())) {
                    mapOfWords.get(word).add(f.getName());
                }
            } else {
                ArrayList<String> list = new ArrayList<>();
                list.add(f.getName());
                mapOfWords.put(word, list);
            }
        }
    }

    public void buildWordFileMap() {
        mapOfWords.clear();

        DirectoryResource dr = new DirectoryResource();

        for (File file : dr.selectedFiles()) {
            addWordsFromFile(file);
        }
    }

    public int maxNumber() {
        int maxLen = 0;
        for (String word : mapOfWords.keySet()) {
            if (mapOfWords.get(word).size() >= maxLen) {
                maxLen = mapOfWords.get(word).size();
            }
        }
        return maxLen;
    }

    public ArrayList<String> wordsInNumFiles(int number) {
        ArrayList<String> words = new ArrayList<>();

        for (String word : mapOfWords.keySet()) {
            if (mapOfWords.get(word).size() == number) {
                words.add(word);
            }
        }

        return words;
    }

    public void printFilesIn(String word) {
        for (String w : mapOfWords.keySet()) {
            if (w.equals(word)) {
                for (String filename : mapOfWords.get(word)) {
                    System.out.print(filename+" ");
                }
            }
        }
    }

    public void print() {
        for (String w : mapOfWords.keySet()) {
            ArrayList<String> fileList = mapOfWords.get(w);

            //System.out.print(w+" is in the file: ");
            for (String filename : fileList) {
                System.out.print(filename+" ");
            }
            System.out.println();
        }
    }

    public int wordsInNumberOfFiles(int number) {
        int totalWordsInFiveFiles = 0;

        for (String words : mapOfWords.keySet()) {
            if (mapOfWords.get(words).size() == number) {
                totalWordsInFiveFiles++;
            }
        }

        return totalWordsInFiveFiles;
    }

    public void whichFilesDoesWordAppear(String word) {
        /*
        ArrayList<String> files = new ArrayList<>();

        for (String words : mapOfWords.keySet()) {
            mapOfWords.
        }*/

        ArrayList<String> files = mapOfWords.get(word);
        System.out.println(word+" is in ");
        for (String file : files) {
            System.out.print(file+" ");
        }
    }

    public static void main(String[] args) {
        WordsInFiles words = new WordsInFiles();

        words.buildWordFileMap();
        //System.out.println(words.wordsInNumberOfFiles(4));
        words.whichFilesDoesWordAppear("tree");



        //words.print();
        //System.out.println("\nmaxNumber = "+words.maxNumber());
        /*
        for (String word : words.wordsInNumFiles(words.maxNumber())) {
            System.out.println(word);
        }*/
        /*
        System.out.println();
        words.printFilesIn("love");
        System.out.println();
        words.printFilesIn("are");
        System.out.println();
        words.printFilesIn("silly");
        System.out.println();
        words.printFilesIn("cute");*/
    }
}