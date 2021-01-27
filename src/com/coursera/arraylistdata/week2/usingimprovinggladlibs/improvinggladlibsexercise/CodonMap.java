package com.coursera.arraylistdata.week2.usingimprovinggladlibs.improvinggladlibsexercise;

import edu.duke.FileResource;
import java.util.HashMap;

public class CodonMap {

    private HashMap<String, Integer> codonsCountMap;

    public CodonMap() {
        codonsCountMap = new HashMap<>();
    }

    public void buildCodonMap(int start, String dna) {
        for (int i = start; i < dna.length(); i = i + 3) {
            if (i < dna.length() - 2) {
                String codonStr = dna.substring(i, i+3);

                if (codonsCountMap.containsKey(codonStr)) {
                    codonsCountMap.put(codonStr, codonsCountMap.get(codonStr) + 1);
                } else {
                    codonsCountMap.put(codonStr, 1);
                }
            }
        }


        String mostCommonCodon = getMostCommonCodon();
        System.out.println("Reading frame starting with "+start+" results in "
                +codonsCountMap.size()+" unique codons and most common codon is "
                +mostCommonCodon+" with count "+codonsCountMap.get(mostCommonCodon));
    }

    public String getMostCommonCodon() {
        int largestValue = 0;
        String keyWithLargestValue = "";

        for (String key : codonsCountMap.keySet()) {
            if (codonsCountMap.get(key) > largestValue) {
                largestValue = codonsCountMap.get(key);
                keyWithLargestValue = key;
            }
        }

        return keyWithLargestValue;
    }

    public void printCodonCounts(int start, int end) {
        for (String key : codonsCountMap.keySet()) {
            int count = codonsCountMap.get(key);

            if (count >= start && count <= end) {
                System.out.println(key+"\t\t"+count);
            }
        }
    }

    public void tester() {
        FileResource fileResource = new FileResource();
        String codons = fileResource.asString().toUpperCase().trim();

        buildCodonMap(0, codons);

        int start = 1;
        int end = 5;
        //System.out.println("Counts of codons between "+start+" and "+end+" inclusive are:");
        //printCodonCounts(1, 5);
        printAllCodon();
    }

    public void printAllCodon() {
        for (String key : codonsCountMap.keySet()) {
            int value = codonsCountMap.get(key);

            System.out.println(key+" - "+value);
        }
    }

    public static void main(String[] args) {
        CodonMap codonMap = new CodonMap();
        codonMap.tester();
    }
}
