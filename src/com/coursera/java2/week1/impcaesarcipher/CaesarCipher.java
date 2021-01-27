package com.coursera.java2.week1.impcaesarcipher;

import edu.duke.FileResource;



//  exercise. its used for quiz. all correct and working as of lately
public class CaesarCipher {

    public static String preUrl = "D:\\daryll\\coursera\\src\\com\\coursera\\java2\\week1\\breakingcaesar\\";

    /*
    0   Make a StringBuilder with message (encrypted)
    1   Write down the alphabet
    2   Compute the shifted alphabet
    3   Count from 0 to < length or encrypted, (call it i)
        a   Look at the i'th character of encrypted (call it currChar)
        b   Find the index of currChar in the alphabet (call it idx)
        c   if currChar is in the alphabet
            i   Get the idx'th character of shiftedAlphabet (newChar)
            ii  Replace the i'th character of encrypted with newChar
        d   Otherwise: do nothing
    4   Your answer is the String inside of encrypted
     */
    public static String encrypt(String input, int key) {

        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);

        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            int idx = alphabet.indexOf(currChar);

            if (idx != -1) {
                char newChar = shiftedAlphabet.charAt(idx);
                encrypted.setCharAt(i, newChar);
            }
        }

        return encrypted.toString();
    }

    public static String encryptCI(String input, int key) {

        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);

        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            boolean isLower = Character.isLowerCase(currChar);
            int idx;

            if (isLower) {
                idx = alphabet.indexOf(Character.toUpperCase(currChar));
            } else {
                idx = alphabet.indexOf(currChar);
            }

            if (idx != -1) {
                char newChar = shiftedAlphabet.charAt(idx);

                if (isLower) {
                    encrypted.setCharAt(i, Character.toLowerCase(newChar));
                } else {
                    encrypted.setCharAt(i, newChar);
                }
            }
        }

        return encrypted.toString();
    }

    public static String encryptTwoKeysCI(String input, int key1, int key2) {
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        String shiftedAlphabet = alphabet.substring(key1) + alphabet.substring(0, key1);
        String shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);

        for (int i = 0; i < encrypted.length(); i++) {
            char currChar = encrypted.charAt(i);
            boolean isLower = Character.isLowerCase(currChar);
            int idx = alphabet.indexOf(isLower ? Character.toUpperCase(currChar) : currChar);

            if (idx != -1) {
                char newChar = (i % 2 == 0) ? shiftedAlphabet.charAt(idx) : shiftedAlphabet2.charAt(idx);
                encrypted.setCharAt(i, isLower ? Character.toLowerCase(newChar) : newChar);
            }
        }

        return encrypted.toString();
    }

    /*
    this method will store occurences of each letter in string message to array counts.
    ex: message "aabbbcccczzzzz"
        count[0] = 2;   count[0] is letter 'a'
        count[1] = 3;   count[1] is letter 'b'
        count[2] = 4;   count[2] is letter 'c'
        count[3] = 5;   count[25] is letter 'z'
     */
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

    /*
    this will get the index of the max element
     */
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
        /*int[] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;

        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }*/

        int dkey = getKey(encrypted);
        return encryptCI(encrypted, 26 - dkey);
    }

    public static String halfOfString(String message, int start) {
        StringBuilder newStringSb = new StringBuilder();

        for (int index = start; index < message.length(); index += 2) {
            newStringSb.append(message.charAt(index));
        }

        return newStringSb.toString();
    }

    public static int getKey(String s) {
        int[] frequencies = countLetters(s);
        int maxIndex = maxIndex(frequencies);
        int key = maxIndex - 4;

        if (maxIndex < 4) {
            key = 26 - (4 - maxIndex);
        }

        return key;
    }

    public static String decryptTwoKeys(String encrypted) {

        String first = halfOfString(encrypted, 0);
        String second = halfOfString(encrypted, 1);

        String firstDecrypted = decrypt(first);
        String secondDecrypted = decrypt(second);

        int firstKey = getKey(first);
        int secondKey = getKey(second);

        System.out.println(firstKey+","+secondKey);

        return encryptTwoKeysCI(encrypted, 26-firstKey, 26-secondKey);
        //return encryptTwoKeysCI(encrypted, 26-14, 26-24);
    }

    public static void main(String[] args) {

        FileResource resource = new FileResource(preUrl+"mysteryTwoKeysQuiz.txt");

        String dec = decryptTwoKeys(resource.asString());
        System.out.println(dec);
    }

    /*
    public static void testEncrypt() {
        int key = 17;
        FileResource fr = new FileResource();

        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println(encrypted);
    }

    public static void testEncrypt2() {
        String encrypted = encryptCI("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 15);
        System.out.println(encrypted);
    }

    public static void testCaesar() {
        int key = 23;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encryptCI(message, key);
        System.out.println(encrypted);
    }

    public static void testEncryptTwoKeys() {
        System.out.println(encryptTwoKeysCI("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21));
    }*/


}

//  D:\daryll\coursera\src\com\coursera\java2\week1\impcaesarcipher