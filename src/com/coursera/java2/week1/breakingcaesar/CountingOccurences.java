package com.coursera.java2.week1.breakingcaesar;

import com.coursera.java2.week1.impcaesarcipher.CaesarCipher;

public class CountingOccurences {

    public static int[] countLetters(String message) {

        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];

        for (int k = 0; k < message.length(); k++) {

            char ch = Character.toLowerCase(message.charAt(k));
            int index = alph.indexOf(ch);

            if (index != -1) {
                counts[index] = counts[index] + 1;
            }
        }

        return counts;
    }

    public static int maxIndex(int[] vals) {
        int maxIndex = 0;

        for (int index = 0; index < vals.length; index++) {

            if (vals[index] > vals[maxIndex]) {
                maxIndex = index;
            }
        }

        return maxIndex;
    }

    public static String decrypt(String encrypted) {

        /*
        //  26-length
        int[] freqs = countLetters(encrypted);

        //for (int freq : freqs) { System.out.print(freq+" "); }

        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;

        System.out.println("\nmaxIndex = "+maxDex);
        //System.out.println(dkey);

        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }

        return CaesarCipher.encrypt(encrypted, 26 - dkey);
         */

        int[] freqs = countLetters(encrypted);

        int maxDex = maxIndex(freqs);
        int decryptingKey = maxDex - 5;

        System.out.println("\nmaxIndex = "+maxDex);
        System.out.println("decryptingKey = "+decryptingKey);

        if (maxDex < 5) {
            decryptingKey = 26 - (5 - maxDex);
        }

        return CaesarCipher.encrypt(encrypted, 26 - decryptingKey);
    }

    public static void main(String[] args) {

        System.out.println("\n"+decrypt("CFOPQ"));  // prints "FIRST"
    }
}
