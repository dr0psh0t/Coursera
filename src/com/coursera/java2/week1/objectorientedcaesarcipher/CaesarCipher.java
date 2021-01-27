package com.coursera.java2.week1.objectorientedcaesarcipher;

import edu.duke.FileResource;

public class CaesarCipher {
    public static String preUrl = "D:\\daryll\\coursera\\src\\com\\coursera\\java2\\week1\\objectorientedcaesarcipher\\";
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;

    public CaesarCipher(int key) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        mainKey = key;
    }

    public String encrypt(String input) {
        StringBuilder sb = new StringBuilder(input);

        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            int idx = alphabet.indexOf(c);

            if (idx != -1) {
                c = shiftedAlphabet.charAt(idx);
                sb.setCharAt(i, c);
            }
        }

        return sb.toString();
    }

    public String encryptCI(String input) {
        StringBuilder encrypted = new StringBuilder(input);

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

    public String decrypt(String input) {
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        return cc.encrypt(input);
    }

    public String decryptCI(String input) {
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        return cc.encryptCI(input);
    }

    public static void simpleTests() {
        CaesarCipher cc = new CaesarCipher(18);
        FileResource fileResource = new FileResource(preUrl+"wordsLotsOfEs.txt");

        String enc = cc.encryptCI(fileResource.asString());
        System.out.println(enc);

        String dec = cc.decryptCI(enc);
        System.out.println(dec);
    }

    public static void main(String[] args) {

        simpleTests();

        /*
        CaesarCipher cc = new CaesarCipher(3);

        String encrypt = cc.encrypt("FIRST LEGION ATTACK EAST FLANK");
        System.out.println(encrypt);

        String decrypt = cc.decrypt(encrypt);
        System.out.println(decrypt);*/
    }
}