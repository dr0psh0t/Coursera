package com.coursera.java2.week1.breakingcaesar;

import edu.duke.FileResource;

import java.io.File;

public class WordLengths {

    public static String preUrl = "D:\\daryll\\coursera\\src\\com\\coursera\\java2\\week1\\breakingcaesar\\";

    /*
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
    }*/

// ####################################################################################################

    /*
    public static String[] getWords(FileResource resource, String[] commonWords) {

        int index = 0;

        for (String words : resource.words()) {
            commonWords[index] = words;
            index++;
        }
        return commonWords;
    }

    public static boolean wordArrayHasWord(String[] words, String word) {
        for (String everyWord : words) {
            if (everyWord != null) {
                if (everyWord.equals(word)) {
                    return true;
                }

            } else {
                return false;
            }
        }
        return false;
    }*/

    /*
    public static void countWordLengths(FileResource resource, int[] counts) {

        String[] words = new String[counts.length];
        int index = 0;
        String largestWord = "";

        for (String word : resource.words()) {

            char firstChar = word.charAt(0);
            char lastChar = word.charAt(word.length() - 1);
            String everyWord = word;

            if (!Character.isLetter(firstChar)) { everyWord = word.substring(1); }
            if (!Character.isLetter(lastChar)) { everyWord = word.substring(0, word.length() - 1); }

            if (index == 0) {
                words[index] = everyWord;
                largestWord = words[index];
                counts[index] = words[index].length();

                index++;
            } else {
                if (!wordArrayHasWord(words, everyWord)) {
                    words[index] = everyWord;
                    counts[index] = words[index].length();

                    if (words[index].length() >= largestWord.length()) {
                        largestWord = words[index];
                    }

                    index++;
                }
            }
        }

        for (index = 0; index < words.length; index++) {
            System.out.println(words[index]+"  ---  "+counts[index]);
        }

        System.out.println("Largest word is "+largestWord);
    }*/

    public static int indexOfMax(int[] values) {
        int largestIndex = 0;

        for (int index = 0; index < values.length; index++) {
            if (values[index] >= values[largestIndex]) {
                largestIndex = index;
            }
        }
        return largestIndex;
    }

    public static void countWordLengths(FileResource resource, int[] counts) {
        for (String word : resource.words()) {
            String everyWord = word;

            if (!Character.isLetter(word.charAt(0))) { everyWord = word.substring(1); }
            if (!Character.isLetter(word.charAt(word.length() - 1))) { everyWord = word.substring(0, word.length() - 1); }

            counts[everyWord.length()] = counts[everyWord.length()] + 1;
        }
        for (int index = 0; index < counts.length; index++) {
            System.out.println(counts[index]+" words of length "+index);
        }
    }

    public static void testCountWordLengths() {
        FileResource resource = new FileResource(preUrl+"manywords.txt");
        int[] counts = new int[31];

        countWordLengths(resource, counts);
        System.out.println("indexOfMax = "+indexOfMax(counts));
    }

    public static void main(String[] args) {

        testCountWordLengths();

        //countWordLengths(resource, counts);
    }


}
