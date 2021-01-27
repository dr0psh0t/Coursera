package com.coursera.java2.week1.breakingcaesar;

import edu.duke.FileResource;

public class CommonWords {

    public static String preUrl = "D:\\daryll\\coursera\\src\\com\\coursera\\java2\\week1\\breakingcaesar\\";

    public static String[] getCommonWords() {
        FileResource resource = new FileResource(preUrl+"common.txt");

        //  20 common words in common.txt. so common string array length is 20
        String[] commonWords = new String[20];
        int index = 0;

        for (String words : resource.words()) {
            commonWords[index] = words;
            index++;
        }
        return commonWords;
    }

    //  get the index position of a word if found in string array
    public static int indexOfCommonWord(String[] list, String word) {
        for (int k = 0; k < list.length; k++) {
            if (list[k].equals(word)) {
                return k;
            }
        }
        return - 1;
    }

    public static void countCommonWords(FileResource resource, String[] commonWords, int[] counts) {
        for (String word : resource.words()) {
            word = word.toLowerCase();
            int index = indexOfCommonWord(commonWords, word);

            if (index != -1) {
                counts[index] += 1;
            }
        }
    }

    public static void countShakespeare() {
        //String[] plays = {"caesar.txt", "errors.txt", "hamlet.txt", "likeit.txt",
                            //"macbeth.txt", "romeo.txt"};

        String[] plays = {"small.txt"};
        String[] commonWords = getCommonWords();
        int[] counts = new int[commonWords.length];

        for (String play : plays) {
            FileResource resource = new FileResource(preUrl+play);

            countCommonWords(resource, commonWords, counts);
            System.out.println("done with "+play);
        }

        for (int k = 0; k < commonWords.length; k++) {
            System.out.println(commonWords[k] + "  ----  " + counts[k]);
        }
    }
    public static void main(String[] args) {
        countShakespeare();
    }
}