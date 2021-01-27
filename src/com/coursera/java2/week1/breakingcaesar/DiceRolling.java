package com.coursera.java2.week1.breakingcaesar;

import java.util.Random;

public class DiceRolling {

    public static void simulate(int rolls) {
        Random rand = new Random();
        int[] counts = new int[13];

        for (int k = 0; k < rolls; k++) {
            int d1 = rand.nextInt(6) + 1;
            int d2 = rand.nextInt(6) + 1;

            //  total is always assigned between 2-12
            int total = d1 + d2;
            //  increment by 1 for occurences in index [d1 + d2]

            System.out.println("roll is "+d1+"+"+d2+"="+total);

            counts[total] += 1;
        }

        for (int k = 2; k <= 12; k++) {
            System.out.println(k + "'s=\t" + counts[k] + "\t" + (100.0 * counts[k] / rolls));
        }
    }

    public static void simpleSimulate(int rolls) {
        Random rand = new Random();
        int twos = 0;
        int twelves = 0;

        for (int k = 0; k < rolls; k++) {
            int d1 = rand.nextInt(6) + 1;
            int d2 = rand.nextInt(6) + 1;

            if (d1 + d2 == 2) {
                twos += 1;
            } else if (d1 + d2 == 12) {
                twelves += 1;
            }
        }

        System.out.println("2's = "+twos+" "+(100.0 * twos / rolls));
        System.out.println("12's = "+twelves+" "+(100.0 * twelves / rolls));
    }

    public static void main(String[] args) {
        //simpleSimulate(10000);
        simulate(10);
    }
}