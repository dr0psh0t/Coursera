package com.coursera.arraylistdata.week2;

import edu.duke.FileResource;

import java.util.ArrayList;

public class WordFrequencies {

    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    public WordFrequencies() {
        myWords = new ArrayList<>();
        myFreqs = new ArrayList<>();
    }

    public void findUnique() {
        FileResource resource = new FileResource();

        for (String s : resource.words()) {
            s = s.toLowerCase();
            int index = myWords.indexOf(s);

            if (index == -1) {
                myWords.add(s);
                myFreqs.add(1);
            } else {
                /*
                if myWords array contains the word s, get the element in myFreqs by the index of the word s.
                set the element myFreqs array at index of word s.

                increment the element in myFreqs array by 1
                 */
                int value = myFreqs.get(index);
                myFreqs.set(index, value+1);
            }
        }
    }

    public int findIndexOfMax() {
        int largestValue = 0;
        int indexOfMax = 0;

        for (int i = 0; i < myFreqs.size(); i++) {
            if (largestValue < myFreqs.get(i)) {
                largestValue = myFreqs.get(i);
                indexOfMax = i;
            }
        }

        return indexOfMax;
    }

    public void tester() {
        findUnique();
        System.out.println("# unique words: "+myWords.size());

        for (int k = 0; k < myWords.size(); k++) {
            System.out.println(myFreqs.get(k)+"\t"+myWords.get(k));
        }

        int maxIndex = findIndexOfMax();
        System.out.println("The word that occurs most often and its count are: "
                +myWords.get(maxIndex)+" "+myFreqs.get(maxIndex));
    }

    public static void main(String[] args) {
        WordFrequencies wordFrequencies = new WordFrequencies();

        wordFrequencies.tester();
    }
}
