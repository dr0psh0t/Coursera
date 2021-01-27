package com.coursera.arraylistdata.week2.usingimprovinggladlibs;

import edu.duke.*;
import java.util.*;

public class WordFrequenciesMap {

    public void countWords() {
        FileResource fr = new FileResource();
        HashMap<String, Integer> map = new HashMap<>();

        int total = 0;
        for (String w : fr.words()) {
            w = w.toLowerCase();

            if (map.keySet().contains(w)) {
                map.put(w, map.get(w) + 1);
            } else {
                map.put(w, 1);
            }
        }

        for (String w : map.keySet()) {
            int occurrences = map.get(w);

            //if (occurrences > 200) {
                System.out.println(occurrences+"\t"+w);
            //}
        }
    }

    public static void main(String[] args) {
        WordFrequenciesMap wordFrequenciesMap = new WordFrequenciesMap();
        wordFrequenciesMap.countWords();
    }
}
